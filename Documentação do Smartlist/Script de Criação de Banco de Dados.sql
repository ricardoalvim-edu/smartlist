-- -----------------------------------------------------
-- SCRIPT DE CRIAÇÃO DO BANCO DE DADOS PARA O PROJETO SMART LIST
-- 2015-10-01
-- -----------------------------------------------------

CREATE DATABASE smartlistteste01;
\c smartlistteste01;

-- -----------------------------------------------------
-- Tabela Estado
-- -----------------------------------------------------
CREATE TABLE Estado (
  estado_id SERIAL PRIMARY KEY,
  estado_nome VARCHAR(45) NOT NULL,
  estado_sigla VARCHAR(3) NOT NULL
);

-- -----------------------------------------------------
-- Tabela Cidade
-- -----------------------------------------------------
CREATE TABLE Cidade (
  cidade_id SERIAL PRIMARY KEY,
  cidade_nome VARCHAR(100) NOT NULL,
  estado_id_fk INTEGER NOT NULL
);

-- -----------------------------------------------------
-- Tabela AdministradorSistema
-- -----------------------------------------------------
CREATE TABLE AdministradorSistema (
  admSis_id SERIAL PRIMARY KEY,
  admSis_usuario VARCHAR(100) NOT NULL  ,
  admSis_senha VARCHAR(100) NOT NULL  ,
  admSis_nome VARCHAR(100) NOT NULL  ,
  admSis_email VARCHAR(45) NOT NULL
);

-- -----------------------------------------------------
-- Tabela AdministradorEstabelecimento
-- -----------------------------------------------------
CREATE TABLE AdministradorEstabelecimento(
  admEst_id SERIAL PRIMARY KEY,
  admEst_nome VARCHAR(100) NOT NULL,
  admEst_usuario VARCHAR(100) NOT NULL,
  admEst_senha VARCHAR(100) NOT NULL,
  admEst_email VARCHAR(45) NOT NULL UNIQUE,
  est_id_fk INTEGER NOT NULL
);

-- -----------------------------------------------------
-- Tabela Estabelecimento
-- -----------------------------------------------------
CREATE TABLE Estabelecimento(
  est_id SERIAL PRIMARY KEY,
  est_nome VARCHAR(200) NOT NULL,
  est_razaosocial VARCHAR(200) NOT NULL,
  est_cnpj CHAR(14) NOT NULL,
  est_logradouro VARCHAR(200),
  est_numeroEndereco VARCHAR(10),
  est_bairro VARCHAR(100),
  est_CEP CHAR(8),
  admSis_id_fk INTEGER NOT NULL,
  cidade_id_fk INTEGER NOT NULL,
  categEst_id_fk INTEGER NOT NULL
);

-- -----------------------------------------------------
-- Tabela CategoriaEstabelecimento
-- -----------------------------------------------------
CREATE TABLE CategoriaEstabelecimento (
  categEst_id SERIAL PRIMARY KEY,
  categEst_nome VARCHAR(50) NOT NULL,
  categEsta_descricao VARCHAR(150) NOT NULL
);

-- -----------------------------------------------------
-- Tabela Consumidor 
-- -----------------------------------------------------
CREATE TABLE Consumidor(
  cons_id SERIAL PRIMARY KEY,
  cons_nome VARCHAR(100) NOT NULL  ,
  cons_cpf CHAR(11) NOT NULL  ,
  cons_usuario VARCHAR(100) NOT NULL,
  cons_senha VARCHAR(100)
);

-- -----------------------------------------------------
-- Tabela Produto
-- -----------------------------------------------------
CREATE TABLE Produto (
  pro_id SERIAL PRIMARY KEY,
  pro_nome VARCHAR(100),
  pro_preco FLOAT(2),
  pro_quantidade INT,
  admEst_id_fk INTEGER NOT NULL,
  est_id_fk INTEGER NOT NULL,
  categProd_id_fk INTEGER NOT NULL
);

-- -----------------------------------------------------
-- Tabela CategoriaProduto 
-- -----------------------------------------------------
CREATE TABLE CategoriaProduto (
  categProd_id SERIAL PRIMARY KEY,
  categProd_nome VARCHAR(50) ,
  categProd_descricao VARCHAR(150)
);

-- -----------------------------------------------------
-- Tabela ListaConsumidor
-- -----------------------------------------------------
CREATE TABLE ListaConsumidor (
  lst_id SERIAL PRIMARY KEY,
  lst_nome VARCHAR(45) NOT NULL UNIQUE,
  lst_data DATE,
  cons_id_fk INTEGER NOT NULL
  );

-- -----------------------------------------------------
-- Tabela Item_Lista
-- -----------------------------------------------------
CREATE TABLE Item_Lista (
  pro_id_fk INTEGER NOT NULL,
  lst_id_fk INTEGER NOT NULL,
  PRIMARY KEY (pro_id_fk, lst_id_fk)
);

-- -----------------------------------------------------
-- Tabela AvaliacaoProduto 
-- -----------------------------------------------------
CREATE TABLE AvaliacaoProduto (
  avaPro_id SERIAL PRIMARY KEY,
  avaPro_preco INT,
  avaPro_qualidade INT,
  avaPro_moderacao BOOLEAN,
  avaPro_comentario VARCHAR(250),
  cons_id_fk INTEGER NOT NULL,
  admSis_id_fk INTEGER
);

-- -----------------------------------------------------
-- Tabela AvaliacaoEstabelecimento
-- -----------------------------------------------------
CREATE TABLE AvaliacaoEstabelecimento (
  avaEst_id SERIAL PRIMARY KEY,
  avaEst_preco INT,
  avaEst_atendimento INT,
  avaEst_organizacao INT,
  avaEst_localidade INT,
  avaEst_comentario VARCHAR(250),
  avaEst_moderacao BOOLEAN,
  cons_id_fk INTEGER NOT NULL,
  admSis_id_fk INTEGER
);

-- -----------------------------------------------------
-- Tabela LocalizacaoConsumidor
-- -----------------------------------------------------
CREATE TABLE LocalizacaoConsumidor (
  loc_id SERIAL PRIMARY KEY,
  loc_latitude FLOAT,
  loc_longitude FLOAT,
  loc_data DATE,
  cons_id_fk INTEGER NOT NULL
);

-- -----------------------------------------------------
-- Tabela Gerencia_Produto
-- -----------------------------------------------------
CREATE TABLE GerenciaProduto (
  gerenciaProduto_id SERIAL PRIMARY KEY,
  gerenciaProduto_data DATE,
  admEst_id_fk INTEGER NOT NULL,
  pro_id_fk INTEGER NOT NULL
 );

-- -----------------------------------------------------
-- Tabela Sugestoes_Consumidor
-- -----------------------------------------------------
CREATE TABLE Sugestoes_Consumidor (
  sgt_id SERIAL PRIMARY KEY,
  sgt_descricao VARCHAR(200) NOT NULL  ,
  sgt_titulo VARCHAR(45),
  sgt_data DATE,
  cons_id_fk INTEGER NOT NULL,
  est_id_fk INTEGER NOT NULL
);

-- -----------------------------------------------------
-- CRIAÇÃO DAS CHAVES ESTRANGEIRAS
-- -----------------------------------------------------

-- Tabela Cidade
ALTER TABLE Cidade ADD CONSTRAINT cidade_estado_fk FOREIGN KEY (estado_id_fk) REFERENCES Estado(estado_id);

-- Tabela AdministradorEstabelecimento
ALTER TABLE AdministradorEstabelecimento ADD CONSTRAINT administradorEstabelecimento_estabelecimento_fk FOREIGN KEY (est_id_fk) REFERENCES Estabelecimento(est_id);

-- Tabela Estabelecimento
ALTER TABLE Estabelecimento ADD CONSTRAINT estabelecimento_administradorSistema_fk FOREIGN KEY (admSis_id_fk) REFERENCES AdministradorSistema(admSis_id);

ALTER TABLE Estabelecimento ADD CONSTRAINT estabelecimento_categoria_fk FOREIGN KEY (categEst_id_fk) REFERENCES CategoriaEstabelecimento(categEst_id);

ALTER TABLE Estabelecimento ADD CONSTRAINT estabelecimento_cidade_fk FOREIGN KEY (cidade_id_fk) REFERENCES Cidade(cidade_id);

-- Tabela Produto   .......................................................................................................
ALTER TABLE Produto ADD CONSTRAINT produto_administradorEstabelecimento_fk FOREIGN KEY (admEst_id_fk) REFERENCES AdministradorEstabelecimento(admEst_id);

ALTER TABLE Produto ADD CONSTRAINT produto_estabelecimento FOREIGN KEY (est_id_fk) REFERENCES Estabelecimento(est_id);

ALTER TABLE Produto ADD CONSTRAINT produto_categoria  FOREIGN KEY (categProd_id_fk) REFERENCES CategoriaProduto(categProd_id);

-- Tabela ListaConsumidor
ALTER TABLE ListaConsumidor ADD CONSTRAINT listaConsumidor_consumidor FOREIGN KEY (cons_id_fk) REFERENCES Consumidor(cons_id);

-- Tabela Item_Lista
ALTER TABLE Item_Lista ADD CONSTRAINT lista_itens FOREIGN KEY (lst_id_fk) REFERENCES ListaConsumidor(lst_id);

ALTER TABLE Item_Lista ADD CONSTRAINT item_listas FOREIGN KEY (pro_id_fk) REFERENCES Produto(pro_id);

-- Tabela AvaliacaoProduto
ALTER TABLE AvaliacaoProduto ADD CONSTRAINT avaliacaoProduto_consumidor FOREIGN KEY (cons_id_fk) REFERENCES Consumidor(cons_id);

ALTER TABLE AvaliacaoProduto ADD CONSTRAINT avaliacaoProduto_AdministradorSistema FOREIGN KEY (admSis_id_fk) REFERENCES AdministradorSistema(admSis_id);

-- Tabela AvaliacaoEstabelecimento
ALTER TABLE AvaliacaoEstabelecimento ADD CONSTRAINT avaliacaoEstabelecimento_consumidor FOREIGN KEY (cons_id_fk) REFERENCES Consumidor(cons_id);

ALTER TABLE AvaliacaoEstabelecimento ADD CONSTRAINT avaliacaoEstabelecimento_AdministradorSistema FOREIGN KEY (admSis_id_fk) REFERENCES AdministradorSistema(admSis_id);

-- Tabela LocalizacaoConsumidor
ALTER TABLE LocalizacaoConsumidor ADD CONSTRAINT localizacao_consumidor FOREIGN KEY (cons_id_fk) REFERENCES Consumidor(cons_id);

-- Tabela GerenciaProduto
ALTER TABLE GerenciaProduto ADD CONSTRAINT gerenciaProduto_produto FOREIGN KEY (pro_id_fk) REFERENCES Produto(pro_id);

ALTER TABLE GerenciaProduto ADD CONSTRAINT gerenciaProduto_administradorEstabelecimento FOREIGN KEY (admEst_id_fk) REFERENCES AdministradorEstabelecimento(admEst_id);

-- Tabela Sugestoes_Consumidor
ALTER TABLE Sugestoes_Consumidor ADD CONSTRAINT sugestoes_consumidor FOREIGN KEY (cons_id_fk) REFERENCES Consumidor(cons_id);

ALTER TABLE Sugestoes_Consumidor ADD CONSTRAINT sugestoes_estabelecimento FOREIGN KEY (est_id_fk) REFERENCES Estabelecimento(est_id);
