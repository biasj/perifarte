create database perifarte;

use perifarte;

drop table administrador;
drop table artista;
drop table organizacao;
drop table doacao;
drop table doador;
drop table obra;

create table administrador (
  administrador_id SMALLINT AUTO_INCREMENT,
  administrador_nome VARCHAR(45) NOT NULL,
  administrador_email VARCHAR(45) NOT NULL,
  administrador_senha VARCHAR(255) NOT NULL,
  administrador_status VARCHAR(45) NOT NULL,
  PRIMARY KEY  (administrador_id)
); 

create table artista (
	artista_id SMALLINT AUTO_INCREMENT,
    artista_nome VARCHAR(45) NOT NULL,
	artista_email VARCHAR(45) NOT NULL,
	artista_senha VARCHAR(255) NOT NULL,
	artista_portifolio VARCHAR(45) NOT NULL,
    PRIMARY KEY  (artista_id)
);

create table organizacao (
	organizacao_id SMALLINT AUTO_INCREMENT,
    organizacao_cnpj VARCHAR(20),
	organizacao_nome VARCHAR(45) NOT NULL,
	organizacao_email VARCHAR(45) NOT NULL,
	organizacao_senha VARCHAR(255) NOT NULL,
    organizacao_telefone VARCHAR(15),
    organizacao_status VARCHAR(45) NOT NULL,
	organizacao_descricao VARCHAR(255),
	organizacao_justificativa VARCHAR(255),
	PRIMARY KEY  (organizacao_id)
);

create table doador (
	doador_id SMALLINT AUTO_INCREMENT,
	doador_nome VARCHAR(45) NOT NULL,
	doador_email VARCHAR(45) NOT NULL,
	doador_senha VARCHAR(255) NOT NULL,
    PRIMARY KEY  (doador_id)
);
    
create table obra (
	obra_id SMALLINT AUTO_INCREMENT,
	obra_titulo VARCHAR(255) NOT NULL,
    obra_descricao VARCHAR(255) NOT NULL, 
    obra_organizacao_id SMALLINT,
    obra_artista_id SMALLINT,
    obra_preco DOUBLE,
    obra_imagem mediumblob,
    PRIMARY KEY  (obra_id),
	FOREIGN KEY (obra_organizacao_id) REFERENCES organizacao (organizacao_id),
    FOREIGN KEY (obra_artista_id) REFERENCES artista (artista_id)
);

create table doacao (
	doacao_id SMALLINT AUTO_INCREMENT,
	doacao_doador_id SMALLINT,
	doacao_obra_id SMALLINT,
	doacao_data DATE NOT NULL,
	doacao_valor DOUBLE,   /*aqui deverá ficar registrado o valor que será pago pelo usuário doador ao adquirir uma obra*/
    doacao_status VARCHAR(45) NOT NULL,
	PRIMARY KEY  (doacao_id),
	FOREIGN KEY (doacao_doador_id) REFERENCES doador (doador_id),
	FOREIGN KEY (doacao_obra_id) REFERENCES obra (obra_id)
);


# aprova o 1º adm cadastrado para gerenciar o site
UPDATE administrador
SET administrador_status = 'aprovado'
WHERE administrador_id = 1; 


select o.obra_titulo, org.organizacao_nome, o.obra_preco, a.artista_nome from obra o
join artista a on o.obra_artista_id = a.artista_id
join organizacao org on org.organizacao_id = o.obra_organizacao_id;

SELECT ong.organizacao_id, ong.organizacao_nome, d.doacao_valor, o.obra_titulo
FROM obra o 
join doacao d on o.obra_id = d.doacao_obra_id
join organizacao ong on ong.organizacao_id = o.obra_organizacao_id;

# QUERIES DE DOAÇÕES

#seleciona o total doado;
SELECT SUM(doacao_valor) FROM doacao 
	WHERE doacao_valor IS NOT NULL   
	AND doacao_valor != 0.00;  

#consulta de total doado por doador_id;
SELECT SUM(doacao_valor) AS total_doado FROM doacao
	WHERE doacao_valor IS NOT NULL   
	AND doacao_valor != 0.00
    AND doacao.doacao_doador_id = 1;

#consulta de total doado por obra_id;
SELECT SUM(doacao_valor) AS total_obtido_obra FROM doacao
	WHERE doacao_valor IS NOT NULL   
	AND doacao_valor != 0.00
    AND doacao.doacao_obra_id = 1;
    
#consulta de total doado por artista_id;
Select Sum(doacao.doacao_valor) as total_obtido
from artista
inner join obra on obra.obra_artista_id = artista.artista_id
inner join doacao on obra.obra_id = doacao.doacao_obra_id
where artista.artista_id = 1;

#consulta de total doado recebido por organizacao;
Select Sum(doacao.doacao_valor) as total_recebido
from organizacao
inner join obra on obra.obra_organizacao_id = organizacao.organizacao_id
inner join doacao on obra.obra_id = doacao.doacao_obra_id
where organizacao.organizacao_id = 1;

# QUERY DE LOGIN
# para buscar todos os emails para fazer login (Não usado)
SELECT  administrador_email as user_email, administrador_senha as user_password FROM administrador
	UNION ALL
SELECT artista_email as user_email, artista_senha as user_password FROM artista
	UNION ALL
SELECT doador_email as user_email, doador_senha as user_password FROM doador
	UNION ALL
SELECT organizacao_email as user_email, organizacao_senha as user_password FROM organizacao;


# consultas simples:
select * from administrador;
select * from artista;
select * from doador;
select * from organizacao;
select * from obra;
select * from doacao;
