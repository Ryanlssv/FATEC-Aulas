INSERT INTO ESTADO (EST_STR_SIGLA, EST_STR_DES) VALUES
('SP', 'S�o Paulo'),
('RJ', 'Rio de Janeiro'),
('MG', 'Minas Gerais'),
('BA', 'Bahia'),
('RS', 'Rio Grande do Sul');

SELECT * FROM ESTADO

INSERT INTO CIDADE (CID_STR_DES, EST_INT_ID) VALUES
('S�o Paulo', 1),
('Campinas', 1),
('Rio de Janeiro', 2),
('Niter�i', 2),
('Belo Horizonte', 3),
('Uberl�ndia', 3),
('Salvador', 4),
('Feira de Santana', 4),
('Porto Alegre', 5),
('Caxias do Sul', 5);

SELECT * FROM CIDADE

INSERT INTO ENDERE�O (CID_INT_ID, END_STR_RUA, END_STR_BAIRO, END_INT_CEP) VALUES
(1, 'Rua A', 'Centro', 12345678), 
(2, 'Rua B', 'Jardins', 22345678),
(3, 'Rua C', 'Copacabana', 32345678), 
(4, 'Rua D', 'Icara�', 42345678),
(5, 'Rua E', 'Savassi', 52345678), 
(6, 'Rua F', 'Martins', 62345678),
(7, 'Rua G', 'Barra', 72345678), 
(8, 'Rua H', 'Centro', 82345678),
(9, 'Rua I', 'Moinhos', 92345678), 
(10, 'Rua J', 'S�o Pelegrino', 10234567);

SELECT * FROM PESSOA

INSERT INTO PESSOA (PES_STR_NOME, PES_STR_CPF, END_INT_ID) VALUES
('Jo�o Silva', '12345678901', 1),
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
('Dr. Jo�o Medeiros', 'CRM1234567'),
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
    P.PES_STR_NOME AS [Paciente],
    MD.MED_STR_DESCRICAO AS [Medicamento]
FROM CONSULTA C
INNER JOIN MEDICO M ON C.MEC_INT_ID = M.MEC_INT_ID
INNER JOIN PACIENTE PC ON C.PAC_INT_ID = PC.PAC_INT_ID
INNER JOIN PESSOA P ON PC.PES_INT_ID = P.PES_INT_ID
INNER JOIN RECEITA R ON C.CON_INT_ID = R.CON_INT_ID
INNER JOIN MEDICAMENTO MD ON R.MED_INT_ID = MD.MED_INT_ID;


SELECT 
    P.PES_STR_NOME AS [Paciente],
    E.END_STR_RUA AS [Rua],
    E.END_STR_BAIRO AS [Bairro],
    C.CID_STR_DES AS [Cidade],
    U.EST_STR_SIGLA AS [Estado]
FROM PESSOA P
INNER JOIN ENDEREÇO E ON P.END_INT_ID = E.END_INT_ID
INNER JOIN CIDADE C ON E.CID_INT_ID = C.CID_INT_ID
INNER JOIN ESTADO U ON C.EST_INT_ID = U.EST_INT_ID;



