CREATE DATABASE EX_7

CREATE TABLE INSTRUTOR (
  INS_INT_ID INTEGER NOT NULL IDENTITY(1,1),
  INS_STR_NOME VARCHAR(70),
  INS_INT_TELEFONE INTEGER,
  CONSTRAINT PK_INT_ID PRIMARY KEY (INS_INT_ID),
  CONSTRAINT UK_INS_INT_TELEFONE UNIQUE (INS_INT_TELEFONE)
);

CREATE TABLE ALUNOS (
  ALU_INT_ID INTEGER NOT NULL IDENTITY(1,1),
  ALU_STR_NOME VARCHAR(70),
  ALU_STR_TELEFONE INTEGER,
  CONSTRAINT PK_ALU_INT_ID PRIMARY KEY (ALU_INT_ID),
  CONSTRAINT UK_ALU_STR_TELEFONE UNIQUE (ALU_STR_TELEFONE)
);

CREATE TABLE CURSOS (
  CUR_INT_ID INTEGER NOT NULL IDENTITY(1,1),
  INS_INT_ID INTEGER NOT NULL,
  CUR_INT_CODIGO INTEGER,
  CUR_STR_NOME VARCHAR(70),
  CUR_INT_PRECO INTEGER,
  CONSTRAINT PK_CURSOS_INT_ID PRIMARY KEY (CUR_INT_ID),
  CONSTRAINT FK_CURSOS_INSTRUTOR FOREIGN KEY (INS_INT_ID) REFERENCES INSTRUTOR(INS_INT_ID),
  CONSTRAINT UK_CURSOS_CUR_INT_CODIGO UNIQUE (CUR_INT_CODIGO)
);

CREATE TABLE ALUNOS_CURSOS (
  ALU_INT_ID INTEGER NOT NULL,
  CUR_INT_ID INTEGER NOT NULL,
  CONSTRAINT FK_ALUNOS_CURSOS_ALUNOS FOREIGN KEY (ALU_INT_ID) REFERENCES ALUNOS(ALU_INT_ID),
  CONSTRAINT FK_ALUNOS_CURSOS_CURSOS FOREIGN KEY (CUR_INT_ID) REFERENCES CURSOS(CUR_INT_ID)
);