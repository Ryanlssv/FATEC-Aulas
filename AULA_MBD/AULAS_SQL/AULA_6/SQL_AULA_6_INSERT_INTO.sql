INSERT INTO ESTADO (EST_STR_SIGLA, EST_STR_DES) VALUES
('SP', 'São Paulo'),
('RJ', 'Rio de Janeiro'),
('MG', 'Minas Gerais'),
('BA', 'Bahia'),
('RS', 'Rio Grande do Sul');

SELECT * FROM ESTADO

INSERT INTO CIDADE (CID_STR_DES, EST_INT_ID) VALUES
('São Paulo', 1),
('Campinas', 1),
('Rio de Janeiro', 2),
('Niterói', 2),
('Belo Horizonte', 3),
('Uberlândia', 3),
('Salvador', 4),
('Feira de Santana', 4),
('Porto Alegre', 5),
('Caxias do Sul', 5);

SELECT * FROM CIDADE

INSERT INTO ENDEREÇO (CID_INT_ID, END_STR_RUA, END_STR_BAIRO, END_INT_CEP) VALUES
(1, 'Rua A', 'Centro', 12345678), 
(2, 'Rua B', 'Jardins', 22345678),
(3, 'Rua C', 'Copacabana', 32345678), 
(4, 'Rua D', 'Icaraí', 42345678),
(5, 'Rua E', 'Savassi', 52345678), 
(6, 'Rua F', 'Martins', 62345678),
(7, 'Rua G', 'Barra', 72345678), 
(8, 'Rua H', 'Centro', 82345678),
(9, 'Rua I', 'Moinhos', 92345678), 
(10, 'Rua J', 'São Pelegrino', 10234567);

SELECT * FROM PESSOA

INSERT INTO PESSOA (PES_STR_NOME, PES_STR_CPF, END_INT_ID) VALUES
('João Silva', '12345678901', 1),
('Maria Souza', '98765432100', 2),
('Carlos Lima', '45678912300', 3),
('Ana Paula', '32165498700', 4),
('Rafael Costa', '65498732100', 5),
('Juliana Martins', '74185296300', 6),
('Pedro Henrique', '85296374100', 7),
('Larissa Moura', '36925814700', 8),
('Lucas Rocha', '25814736900', 9),
('Beatriz Alves', '14736925800', 10);

SELECT * FROM PESSOA

INSERT INTO PACIENTE (PES_INT_ID) VALUES
(1),
(2),
(3),
(4),
(5);

SELECT * FROM PACIENTE

INSERT INTO MEDICO (MEC_STR_NOME, MEC_STR_CRM) VALUES
('Dr. João Medeiros', 'CRM1234567'),
('Dra. Fernanda Lima', 'CRM2345678'),
('Dr. Ricardo Tavares', 'CRM3456789');

SELECT * FROM MEDICO

INSERT INTO CONSULTA (MEC_INT_ID, PAC_INT_ID) VALUES
(1, 1),
(2, 2),
(3, 3),
(1, 4),
(2, 5);

SELECT * FROM CONSULTA

INSERT INTO MEDICAMENTO (MED_STR_DESCRICAO, MED_INT_CODIGOBARRAS) VALUES
('Paracetamol 500mg', 123456789),
('Amoxicilina 500mg', 987654321);

SELECT * FROM MEDICAMENTO

INSERT INTO RECEITA (CON_INT_ID, MED_INT_ID) VALUES
(1, 1),
(2, 2),
(3, 1),
(4, 2),
(5, 1);

SELECT * FROM RECEITA

SELECT
    M.MEC_STR_NOME AS [Medico],
    R.MED_INT_ID AS [Medicamento]
FROM 
    MEDICO AS M 
INNER JOIN 
    CONSULTA AS C ON M.MEC_INT_ID = C.MEC_INT_ID
INNER JOIN 
    RECEITA AS R ON C.CON_INT_ID = R.CON_INT_ID;

SELECT
    P.PES_STR_NOME AS [Paciente],
    E.END_STR_RUA AS [Rua],
    E.END_STR_BAIRO AS [Bairro],
    E.END_INT_CEP AS [CEP]
FROM 
    PESSOA AS P
INNER JOIN 
    PESSOA AS Pe ON P.PES_INT_ID = Pe.PES_INT_ID
INNER JOIN 
    ENDEREÇO AS E ON Pe.END_INT_ID = E.END_INT_ID;



