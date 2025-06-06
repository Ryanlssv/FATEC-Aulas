-- COMANDOS DQL: SELEC FROM WHERE JOIN--
-- COMANDOS DDL: CREATE ALTER DROP --
-- COMANDOS DMl: SELEC UPDATE INSERT UPDATE DELETE -- 

SELECT * FROM MEDICO;

INSERT INTO MEDICO(MEC_STR_NOME,MEC_STR_CPF)
VALUES ('Z� antonio', '58975214567');

INSERT INTO MEDICO(MEC_STR_NOME,MEC_STR_CRM)
VALUES ('antonio medio', '987456321');

INSERT INTO MEDICO(MEC_STR_NOME,MEC_STR_CRM)
VALUES ('Gabriela amada ', '756721354');

UPDATE MEDICO
SET MEC_STR_CRM = '123456789'
WHERE MEC_INT_ID = 3;

UPDATE MEDICO
SET MEC_STR_CRM = '756721354'
WHERE MEC_INT_ID = 5;

SELECT * FROM MEDICO;

INSERT INTO PACIENTE(PAC_STR_NOME,PAC_STR_CPF)
VALUES ('Maria das desgra�as', '58975214567');

INSERT INTO PACIENTE(PAC_STR_NOME,PAC_STR_CPF)
VALUES ('J�o gomes ', '77921456799');

UPDATE PACIENTE
SET PAC_STR_CPF = '77921456799'
WHERE PAC_INT_ID = 2;


INSERT INTO PACIENTE(PAC_STR_NOME,PAC_STR_CPF)
VALUES ('Matheus serv', '38975216563');

INSERT INTO PACIENTE(PAC_STR_NOME,PAC_STR_CPF)
VALUES ('Jo�o m ', '17921456799');

SELECT * FROM PACIENTE;

INSERT INTO MEDICAMENTO(MED_STR_DESCRICAO,MED_INT_CODIGOBARRAS)
VALUES ('CoristinaD ',12345678);

INSERT INTO MEDICAMENTO(MED_STR_DESCRICAO,MED_INT_CODIGOBARRAS)
VALUES ('Doril ',87654321);

SELECT * FROM MEDICAMENTO


INSERT INTO CONSULTA(MEC_INT_ID,PAC_INT_ID)
VALUES (3,1);

INSERT INTO CONSULTA(MEC_INT_ID,PAC_INT_ID)
VALUES (4,2);

INSERT INTO RECEITA(CON_INT_ID,MED_INT_ID)
VALUES (1,1);

INSERT INTO RECEITA(CON_INT_ID,MED_INT_ID)
VALUES (2,3);

SELECT * FROM RECEITA;
SELECT * FROM CONSULTA;

SELECT
M.MEC_STR_NOME AS [Medico] ,
P.PAC_STR_NOME AS [Paciente]
FROM MEDICO AS M INNER JOIN  CONSULTA AS C ON M.MEC_INT_ID = C.MEC_INT_ID
INNER JOIN PACIENTE AS P ON P.PAC_INT_ID = C.PAC_INT_ID



SELECT
M.MEC_STR_NOME AS [Medico],
P.PAC_STR_NOME AS [Paciente],
MD.MED_STR_DESCRICAO AS [Remedio]
FROM MEDICO AS M 
INNER JOIN  CONSULTA AS C ON M.MEC_INT_ID = C.MEC_INT_ID INNER JOIN PACIENTE AS P ON P.PAC_INT_ID = C.PAC_INT_ID INNER JOIN RECEITA AS R ON R.CON_INT_ID = C.CON_INT_ID
INNER JOIN MEDICAMENTO AS MD ON MD.MED_INT_ID = R.MED_INT_ID;

SELECT * FROM CONSULTA;

UPDATE PACIENTE
SET PAC_STR_NOME = 'Maria das gra�as'
WHERE PAC_INT_ID = 1;

DELETE FROM MEDICO 
WHERE MEC_INT_ID = 5

SELECT * FROM CONSULTA;