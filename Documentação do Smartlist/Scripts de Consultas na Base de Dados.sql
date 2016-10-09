-- -------------------------------------------
-- CONSULTAS PARA O PROJETO SMART LIST 
-- -------------------------------------------

-- VISÕES AUXILIARES

-- Criar uma visão para exibir os produtos com os respectivos nome das categorias de produtos
CREATE OR REPLACE VIEW produtoView AS
		SELECT p.pro_id, p.pro_nome, p.pro_preco, p.pro_quantidade, p.est_id_fk, c.categProd_nome, c.categProd_id
			 FROM Produto AS p INNER JOIN CategoriaProduto AS c 
				ON c.categProd_id = p.categProd_id_fk ;

-- criar uma visão para retornar todas as informações de um estabelecimento
CREATE OR REPLACE VIEW estabelecimentoView AS
	SELECT e.est_id, e.est_nome, e.est_razaosocial, e.est_cnpj,
			e.est_logradouro, e.est_numeroEndereco, e.est_bairro, e.est_cep, c.cidade_nome, c.estado_sigla
	FROM estabelecimento AS e INNER JOIN ( SELECT cidade_id, cidade_nome, estado_sigla
											FROM Cidade JOIN Estado ON cidade.estado_id_fk = estado.estado_id ) AS c
											ON c.cidade_id = e.cidade_id_fk ;

-- RECUPERAR AS LISTA DE UM CONSUMIDOR
SELECT lc.lst_nome, lc.lst_data FROM ListaConsumidor AS lc WHERE lc.cons_id_fk = :cons_id; -- parâmetro

-- RECUPERAR OS PRODUTOS DE DETERMINADA LISTA DE UM CONSUMIDOR
SELECT pro_nome, pro_preco, categProd_nome
FROM Item_Lista AS il 	NATURAL JOIN produtoView AS ProdutoECategoria
WHERE il.lst_id_fk = :lst_id; -- parâmetro

-- RECUPERAR OS PRODUTOS DE UM ESTABELECIMENTO
-- lembrando que na presente modelagem os estabelecimentos não compartilham a descrição de um mesmo produto
SELECT p.pro_nome, p.pro_preco, p.pro_quantidade, p.categProd_nome
FROM Estabelecimento AS e INNER JOIN produtoView AS p ON e.est_id = p.est_id_fk
WHERE e.est_id = :est_id; -- parâmetro
