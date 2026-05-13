-- Script contendo as procedures solicitadas
-- Supõe-se o seguinte esquema de tabelas (ajuste conforme necessário):
-- CLIENTES(CODIGO NUMBER PRIMARY KEY, SALARIO NUMBER, SALARIO_CONJUGE NUMBER, ESTADO VARCHAR2(2))
-- PEDIDOS(NUMERO NUMBER PRIMARY KEY, CODIGO_CLIENTE NUMBER, DATA DATE, VALOR_TOTAL NUMBER, STATUS VARCHAR2(20))
-- ITEM_PEDIDO(NUMERO_PEDIDO NUMBER, QUANTIDADE NUMBER, PRECO_UNITARIO NUMBER)
-- FUNCIONARIOS(MATRICULA NUMBER PRIMARY KEY, SALARIO NUMBER, QTD_DEPENDENTES NUMBER)
-- PONTUACAO(MATRICULA NUMBER, MES NUMBER, ANO NUMBER, PONTUACAO NUMBER)
-- PARCELAS(NUMERO_PARCELA NUMBER PRIMARY KEY, NUMERO_PEDIDO NUMBER, NUMERO NUMBER, VALOR NUMBER, DATA_VENCIMENTO DATE)

SET SERVEROUTPUT ON;

-- 1. Procedure que recebe o código do cliente e um valor percentual como parâmetro
--    e aplica este percentual de desconto no último pedido que esse cliente fez.
CREATE OR REPLACE PROCEDURE aplicar_descliente_ultimo_pedido(
    p_codigo_cliente IN CLIENTES.CODIGO%TYPE,
    p_percentual     IN NUMBER
) IS
    v_numero_pedido PEDIDOS.NUMERO%TYPE;
    v_valor_atual   PEDIDOS.VALOR_TOTAL%TYPE;
    v_novo_valor    NUMBER;
BEGIN
    -- Busca o número do último pedido do cliente (maior data)
    SELECT NUMERO INTO v_numero_pedido
    FROM PEDIDOS
    WHERE CODIGO_CLIENTE = p_codigo_cliente
    ORDER BY DATA DESC
    FETCH FIRST 1 ROW ONLY;

    -- Obtém o valor atual desse pedido
    SELECT VALOR_TOTAL INTO v_valor_atual
    FROM PEDIDOS
    WHERE NUMERO = v_numero_pedido;

    -- Calcula o novo valor com desconto
    v_novo_valor := v_valor_atual * (1 - p_percentual/100);

    -- Atualiza o pedido
    UPDATE PEDIDOS
    SET VALOR_TOTAL = v_novo_valor
    WHERE NUMERO = v_numero_pedido;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Desconto de '||p_percentual||'% aplicado ao pedido #'||v_numero_pedido||'. Novo valor: '||v_novo_valor);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Cliente não possui pedidos.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Erro: '||SQLERRM);
END aplicar_descliente_ultimo_pedido;
/

-- 2. Procedure que recebe o código do cliente e um valor percentual como parâmetro
--    e aplica este percentual de desconto no último pedido que esse cliente fez
--    se o valor deste pedido representar menos de 10% da renda salarial deste cliente
--    (considerando o salário do cônjuge na renda).
CREATE OR REPLACE PROCEDURE aplicar_descliente_condicional(
    p_codigo_cliente IN CLIENTES.CODIGO%TYPE,
    p_percentual     IN NUMBER
) IS
    v_numero_pedido PEDIDOS.NUMERO%TYPE;
    v_valor_pedido  PEDIDOS.VALOR_TOTAL%TYPE;
    v_renda_familiar NUMBER;
    v_limite         NUMBER;
BEGIN
    -- Último pedido do cliente
    SELECT NUMERO, VALOR_TOTAL INTO v_numero_pedido, v_valor_pedido
    FROM PEDIDOS
    WHERE CODIGO_CLIENTE = p_codigo_cliente
    ORDER BY DATA DESC
    FETCH FIRST 1 ROW ONLY;

    -- Renda familiar = salário + salário do cônjuge (se nulo, considera 0)
    SELECT NVL(SALARIO,0) + NVL(SALARIO_CONJUGE,0) INTO v_renda_familiar
    FROM CLIENTES
    WHERE CODIGO = p_codigo_cliente;

    v_limite := v_renda_familiar * 0.10; -- 10% da renda

    IF v_valor_pedido < v_limite THEN
        UPDATE PEDIDOS
        SET VALOR_TOTAL = VALOR_TOTAL * (1 - p_percentual/100)
        WHERE NUMERO = v_numero_pedido;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Condição atendida: desconto de '||p_percentual||'% aplicado ao pedido #'||v_numero_pedido);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Condição não atendida: valor do pedido ( '||v_valor_pedido||' ) >= 10% da renda familiar ( '||v_limite||' ).');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Cliente não possui pedidos ou não encontrado.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Erro: '||SQLERRM);
END aplicar_descliente_condicional;
/

-- 3. Procedure que recebe o número de pedido como parâmetro e calcule o valor total
--    deste pedido, gravando esse total para o referido pedido.
CREATE OR REPLACE PROCEDURE calcular_total_pedido(
    p_numero_pedido IN PEDIDOS.NUMERO%TYPE
) IS
    v_total NUMBER := 0;
BEGIN
    SELECT NVL(SUM(QUANTIDADE * PRECO_UNITARIO),0) INTO v_total
    FROM ITEM_PEDIDO
    WHERE NUMERO_PEDIDO = p_numero_pedido;

    UPDATE PEDIDOS
    SET VALOR_TOTAL = v_total
    WHERE NUMERO = p_numero_pedido;

    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Pedido #'||p_numero_pedido||' não encontrado.');
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Total do pedido #'||p_numero_pedido||' recalculado e gravado: '||v_total);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Erro: '||SQLERRM);
END calcular_total_pedido;
/

-- 4. Procedure que aplique um aumento salarial (cujo valor deve ser recebido
--    como parâmetro) para os funcionários que tiverem menos de dois dependentes.
CREATE OR REPLACE PROCEDURE aumentar_salario_dependentes(
    p_aumento NUMBER -- pode ser percentual ou valor absoluto; aqui tratamos como valor absoluto
) IS
BEGIN
    UPDATE FUNCIONARIOS
    SET SALARIO = SALARIO + p_aumento
    WHERE QTD_DEPENDENTES < 2;

    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Nenhum funcionário com menos de dois dependentes encontrado.');
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Aumento de '||p_aumento||' aplicado a '||SQL%ROWCOUNT||' funcionário(s).');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Erro: '||SQLERRM);
END aumentar_salario_dependentes;
/

-- 5. Procedure que receba um valor percentual como parâmetro e aplique um desconto
--    no último pedido ainda não pago de cada cliente do estado de São Paulo.
CREATE OR REPLACE PROCEDURE desconto_sp_clientes(
    p_percentual IN NUMBER
) IS
    CURSOR c_clientes_sp IS
        SELECT CODIGO FROM CLIENTES WHERE ESTADO = 'SP';

    v_numero_pedido PEDIDOS.NUMERO%TYPE;
    v_valor_atual   PEDIDOS.VALOR_TOTAL%TYPE;
BEGIN
    FOR r_cli IN c_clientes_sp LOOP
        -- Último pedido ainda não pago (STATUS <> 'PAGO')
        SELECT NUMERO, VALOR_TOTAL INTO v_numero_pedido, v_valor_atual
        FROM PEDIDOS
        WHERE CODIGO_CLIENTE = r_cli.CODIGO
          AND STATUS <> 'PAGO'
        ORDER BY DATA DESC
        FETCH FIRST 1 ROW ONLY;

        UPDATE PEDIDOS
        SET VALOR_TOTAL = VALOR_TOTAL * (1 - p_percentual/100)
        WHERE NUMERO = v_numero_pedido;

        DBMS_OUTPUT.PUT_LINE('Cliente '||r_cli.CODIGO||': desconto de '||p_percentual||'% aplicado ao pedido #'||v_numero_pedido);
    END LOOP;

    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Cliente sem pedidos não pagos; apenas continua para o próximo
        NULL;
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Erro: '||SQLERRM);
END desconto_sp_clientes;
/

-- 6. Procedure que receba um valor percentual como parâmetro, e aplique este
--    valor percentual com relação ao salário de cada funcionário para cada um
--    que tenha tido uma pontuação acima de 8.0 no mês corrente.
CREATE OR REPLACE PROCEDURE bonus_pontuacao(
    p_percentual IN NUMBER -- percentual do salário a ser adicionado como bônus
) IS
    v_mes   NUMBER := TO_CHAR(SYSDATE, 'MM');
    v_ano   NUMBER := TO_CHAR(SYSDATE, 'YYYY');
    v_salario FUNCIONARIOS.SALARIO%TYPE;
    v_bonus NUMBER;
BEGIN
    FOR r_func IN (SELECT MATRICULA, SALARIO FROM FUNCIONARIOS) LOOP
        BEGIN
            SELECT PONTUACAO INTO v_salario
            FROM PONTUACAO
            WHERE MATRICULA = r_func.MATRICULA
              AND MES = v_mes
              AND ANO = v_ano;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                CONTINUE; -- funcionário sem pontuação no mês
        END;

        IF v_salario > 8.0 THEN
            v_bonus := r_func.SALARIO * (p_percentual/100);
            UPDATE FUNCIONARIOS
            SET SALARIO = SALARIO + v_bonus
            WHERE MATRICULA = r_func.MATRICULA;
            DBMS_OUTPUT.PUT_LINE('Funcionário '||r_func.MATRICULA||': bônus de '||v_bonus||' aplicado (pontuação '||v_salario||').');
        END IF;
    END LOOP;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Erro: '||SQLERRM);
END bonus_pontuacao;
/

-- 7. Procedure que receba como parâmetro o número do pedido e um número que
--    represente a quantidade de parcelas em que este pedido será dividido.
--    A procedure deve obter o valor total deste pedido, calcular o valor de
--    cada parcela e gravar cada parcela na tabela Parcelas.
--    Se a quantidade de parcelas ultrapassar 3, acrescente 10% ao valor total
--    do pedido, divida-o na quantidade de parcelas recebida como parâmetro
--    e grave-as na tabela Parcelas.
--    Se a quantidade de parcelas for 1, retorne a mensagem: pedido à vista
--    e interrompa o processamento.
--    Não deixe que o número de parcelas ultrapasse o 10. Se ultrapassar,
--    retorne a mensagem: Quantidade de parcelas inválida.
CREATE OR REPLACE PROCEDURE gerar_parcelas(
    p_numero_pedido IN PEDIDOS.NUMERO%TYPE,
    p_qtde_parcelas IN NUMBER
) IS
    v_total        PEDIDOS.VALOR_TOTAL%TYPE;
    v_valor_parcela NUMBER;
    v_data_venc    DATE := TRUNC(SYSDATE) + 30; -- primeira parcela daqui a 30 dias
    v_msg          VARCHAR2(100);
BEGIN
    -- Verifica limites de parcelas
    IF p_qtde_parcelas > 10 THEN
        v_msg := 'Quantidade de parcelas inválida.';
        DBMS_OUTPUT.PUT_LINE(v_msg);
        RETURN;
    END IF;

    IF p_qtde_parcelas = 1 THEN
        v_msg := 'Pedido à vista';
        DBMS_OUTPUT.PUT_LINE(v_msg);
        RETURN;
    END IF;

    -- Obtém o total do pedido
    SELECT VALOR_TOTAL INTO v_total
    FROM PEDIDOS
    WHERE NUMERO = p_numero_pedido;

    IF SQL%ROWCOUNT = 0 THEN
        v_msg := 'Pedido #'||p_numero_pedido||' não encontrado.';
        DBMS_OUTPUT.PUT_LINE(v_msg);
        RETURN;
    END IF;

    -- Se parcelas > 3, aumenta 10%
    IF p_qtde_parcelas > 3 THEN
        v_total := v_total * 1.10;
    END IF;

    v_valor_parcela := v_total / p_qtde_parcelas;

    -- Insere as parcelas
    FOR i IN 1..p_qtde_parcelas LOOP
        INSERT INTO PARCELAS (NUMERO_PEDIDO, NUMERO, VALOR, DATA_VENCIMENTO)
        VALUES (p_numero_pedido, i, v_valor_parcela, v_data_venc);
        v_data_venc := ADD_MONTHS(v_data_venc, 1); -- próximo mês
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Geradas '||p_qtde_parcelas||' parcelas de '||v_valor_parcela||' cada para o pedido #'||p_numero_pedido||'.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Erro: '||SQLERRM);
END gerar_parcelas;
/

EXIT