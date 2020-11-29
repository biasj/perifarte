create database perifarte;

use perifarte;

drop table administrador;
drop table artista;
drop table organizacao;
drop table doador;
drop table obra;
drop table doacao;

create table administrador (
  administrador_id SMALLINT AUTO_INCREMENT,
  administrador_nome VARCHAR(45) NOT NULL,
  administrador_email VARCHAR(45) NOT NULL,
  administrador_senha VARCHAR(150) NOT NULL,
  administrador_status VARCHAR(45) NOT NULL,
  PRIMARY KEY  (administrador_id)
); 

create table artista (
	artista_id SMALLINT AUTO_INCREMENT,
    artista_nome VARCHAR(45) NOT NULL,
	artista_email VARCHAR(45) NOT NULL,
	artista_senha VARCHAR(150) NOT NULL,
	artista_portifolio VARCHAR(45) NOT NULL,
    PRIMARY KEY  (artista_id)
);

create table organizacao (
	organizacao_id SMALLINT AUTO_INCREMENT,
    organizacao_cnpj VARCHAR(20),
	organizacao_nome VARCHAR(45) NOT NULL,
	organizacao_email VARCHAR(45) NOT NULL,
	organizacao_senha VARCHAR(150) NOT NULL,
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
	doador_senha VARCHAR(150) NOT NULL,
    PRIMARY KEY  (doador_id)
	);

create table obra (
	obra_id SMALLINT AUTO_INCREMENT,
	obra_titulo VARCHAR(45) NOT NULL,
    obra_descricao VARCHAR(100) NOT NULL, 
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


insert into artista values
("1", "marcelo", "marcelo@hotmail", "feliz", "eh isso ai"),
("2", "gabriel", "gabriel@hotmail", "ok", "eh isso mesmo"),
("3", "bia", "bia@gmail", "tranquila", "eh isso");

insert into doador values 
("1", "ma", "ma@hotmail", "ma12"),
("2", "ga", "ga@hotmail", "ga12"),
("3", "bi", "bi@gmail", "bi12");


insert into obra values
("1", "monalisa", "a mais bela", "1", "1", 10, LOAD_FILE('//Users/marcelofrostmarchesan/Pictures.monalisa.png')),
("2", "o grito", "berrante", "2", "3", "15", LOAD_FILE('//Users/marcelofrostmarchesan/Pictures.grito.png')),
("3", "guernica", "espanha", "1", "3", "30",  LOAD_FILE('//Users/marcelofrostmarchesan/Pictures.guernica.png'));

insert into organizacao values 
("1", "32767", "AjudaMuito", "AM@gmail", "am12", "137893", "estamos bem", "estamos localizados na zona sul", "ajudamos os mais carentes"),
("2", "87656", "MuitoBoa", "MB@hotmail", "mb12", "648769", "estamos ajudando", "ficamos na zona norte de sp", "atuamos junto às minorias");

insert into doacao values
("1", "2", "1", "2020-10-12", "30", "comprada"),
("2", "1", "3", "2020-10-13", "100", "comprada"),
("3", "3", "1", "2020-11-14", "40", "comprada");	

select o.obra_titulo, org.organizacao_nome, o.obra_preco, a.artista_nome from obra o
join artista a on o.obra_artista_id = a.artista_id
join organizacao org on org.organizacao_id = o.obra_organizacao_id;

SELECT ong.organizacao_id, ong.organizacao_nome, d.doacao_valor, o.obra_titulo
FROM obra o 
join doacao d on o.obra_id = d.doacao_obra_id
join organizacao ong on ong.organizacao_id = o.obra_organizacao_id;

SELECT  administrador_email as user_email, administrador_senha as user_password FROM administrador
	UNION ALL
SELECT artista_email as user_email, artista_senha as user_password FROM artista
	UNION ALL
SELECT doador_email as user_email, doador_senha as user_password FROM doador
	UNION ALL
SELECT organizacao_email as user_email, organizacao_senha as user_password FROM organizacao;

#seleciona o total doado;

SELECT SUM(doacao_valor) FROM doacao 
	WHERE doacao_valor IS NOT NULL   
	AND doacao_valor != 0.00;  

#consulta de total doado por doador_id;

SELECT doacao_doador_id, SUM(doacao_valor) FROM doacao
	WHERE doacao_valor IS NOT NULL   
	AND doacao_valor != 0.00
    GROUP BY doacao_doador_id;

#consulta de total doado por obra_id;

SELECT doacao_obra_id, SUM(doacao_valor) FROM doacao
	WHERE doacao_valor IS NOT NULL   
	AND doacao_valor != 0.00
    GROUP BY doacao_obra_id;
    
#consulta de total doado por artista_id;

Select artista.artista_id, Sum(doacao.doacao_valor)
from artista
inner join obra on obra.obra_artista_id = artista.artista_id
inner join doacao on obra.obra_id = doacao.doacao_obra_id
group by artista_id
order by artista_id;

#consulta de total doado recebido por organizacao;

Select organizacao.organizacao_id, Sum(doacao.doacao_valor)
from organizacao
inner join obra on obra.obra_organizacao_id = organizacao.organizacao_id
inner join doacao on obra.obra_id = doacao.doacao_obra_id
group by organizacao_id
order by organizacao_id;

# consultas simples:

select * from organizacao;
select * from obra;
select * from artista;
select * from administrador;
select * from doador;
select * from doacao;












--create database perifarte;
--
--use perifarte;
--
--create table administrador (
--  administrador_id SMALLINT AUTO_INCREMENT,
--  administrador_nome VARCHAR(45) NOT NULL,
--  administrador_email VARCHAR(45) NOT NULL,
--  administrador_senha VARCHAR(45) NOT NULL,
--  PRIMARY KEY  (administrador_id)
--); 
--
--create table artista (
--	artista_id SMALLINT AUTO_INCREMENT,
--    artista_nome VARCHAR(45) NOT NULL,
--	artista_email VARCHAR(45) NOT NULL,
--	artista_senha VARCHAR(45) NOT NULL,
--	artista_portifolio VARCHAR(45) NOT NULL,
--    PRIMARY KEY  (artista_id)
--);
--
--create table organizacao (
--	organizacao_id SMALLINT AUTO_INCREMENT,
--    organizacao_cnpj VARCHAR(20),
--	organizacao_nome VARCHAR(45) NOT NULL,
--	organizacao_email VARCHAR(45) NOT NULL,
--	organizacao_senha VARCHAR(45) NOT NULL,
--    organizacao_telefone VARCHAR(15),
--    organizacao_status VARCHAR(45) NOT NULL,
--	organizacao_descricao VARCHAR(255),
--	organizacao_justificativa VARCHAR(255),
--	PRIMARY KEY  (organizacao_id)
--);
--
--create table doador (
--	doador_id SMALLINT AUTO_INCREMENT,
--	doador_nome VARCHAR(45) NOT NULL,
--	doador_email VARCHAR(45) NOT NULL,
--	doador_senha VARCHAR(45) NOT NULL,
--    PRIMARY KEY  (doador_id)
--);
--    
--create table obra (
--	obra_id SMALLINT AUTO_INCREMENT,
--	obra_titulo VARCHAR(45) NOT NULL,
--    obra_descricao VARCHAR(100) NOT NULL, 
--    obra_organizacao_id SMALLINT,
--    obra_artista_id SMALLINT,
--    obra_preco DOUBLE,
--    PRIMARY KEY  (obra_id),
--	FOREIGN KEY (obra_organizacao_id) REFERENCES organizacao (organizacao_id),
--    FOREIGN KEY (obra_artista_id) REFERENCES artista (artista_id)
--);
--
--create table doacao (
--	doacao_id SMALLINT AUTO_INCREMENT,
--	doacao_doador_id SMALLINT,
--    doacao_obra_id SMALLINT,
--    doacao_data DATE NOT NULL,
--    doacao_valor DOUBLE,   /*aqui deverá ficar registrado o valor que será pago pelo usuário doador ao adquirir uma obra*/
--    PRIMARY KEY  (doacao_id),
--	FOREIGN KEY (doacao_doador_id) REFERENCES doador (doador_id),
--	FOREIGN KEY (doacao_obra_id) REFERENCES obra (obra_id)
--);
--
--
--insert into administrador values
--("1", "Marcelo", "marcelo@hotmail", "marcelo123"),
--("2", "Gabriel", "gabriel@hotmail", "gabriel123"),
--("3", "Bia", "bia@gmail", "bia123");
--
--insert into artista values
--("1", "Leonardo", "leonardo@hotmail", "davinci", "instagram.com/leodavinci"),
--("2", "Nadja", "nadja@hotmail", "tonariau", "behance.com/nadja"),
--("3", "Juan", "juan@gmail", "juanzito", "linkedin.com/juan");
--
--insert into doador values 
--("1", "Ana", "ana@hotmail", "ana12"),
--("2", "Gabi", "gabi@hotmail", "gabi12"),
--("3", "Bianca", "bianca@gmail", "bianca12");
--
--insert into organizacao values 
--("1", "32767", "Org Zona Sul", "zonasul@gmail", "zonasul", "1199998888", "aprovado","organizacao da zona sul",  "compraremos mascara"),
--("2", "87656", "Org Ajuda Muito", "am@hotmail", "am12", "1334559900", "aprovado","organizacao que ajuda muito",  "vamos ajudar as pessoas");
--
--insert into obra values
--("1", "Monalisa", "A mais bela", "1", "1", 10),
--("2", "O grito", "Obra berrante", "2", "3", "15"),
--("3", "Guernica", "Representação da Espanha", "1", "3", "30");
--
--insert into doacao values
--("1", "2", "1", "2020-10-12", "30"),
--("2", "1", "3", "2020-10-13", "100"),
--("3", "3", "1", "2020-11-14", "40");
--
--select o.obra_titulo, org.organizacao_nome, o.obra_preco, a.artista_nome from obra o
--join artista a on o.obra_artista_id = a.artista_id
--join organizacao org on org.organizacao_id = o.obra_organizacao_id;
--
--SELECT ong.organizacao_id, ong.organizacao_nome, d.doacao_valor, o.obra_titulo
--FROM obra o 
--join doacao d on o.obra_id = d.doacao_obra_id
--join organizacao ong on ong.organizacao_id = o.obra_organizacao_id;
--
--SELECT  administrador_email as user_email, administrador_senha as user_password FROM administrador
--UNION ALL
--SELECT artista_email as user_email, artista_senha as user_password FROM artista
--UNION ALL
--SELECT doador_email as user_email, doador_senha as user_password FROM doador
--UNION ALL
--SELECT organizacao_email as user_email, organizacao_senha as user_password FROM organizacao;
