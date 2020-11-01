create database perifarte;

use perifarte;

create table administrador (
  administrador_id SMALLINT AUTO_INCREMENT,
  administrador_nome VARCHAR(45) NOT NULL,
  administrador_email VARCHAR(45) NOT NULL,
  administrador_senha VARCHAR(45) NOT NULL,
  PRIMARY KEY  (administrador_id)
); 

create table artista (
	artista_id SMALLINT AUTO_INCREMENT,
    artista_nome VARCHAR(45) NOT NULL,
	artista_email VARCHAR(45) NOT NULL,
	artista_senha VARCHAR(45) NOT NULL,
	artista_portifolio VARCHAR(45) NOT NULL,
    PRIMARY KEY  (artista_id)
);

create table organizacao (
	organizacao_id SMALLINT AUTO_INCREMENT,
    	organizacao_cnpj VARCHAR(20),
	organizacao_nome VARCHAR(45) NOT NULL,
	organizacao_email VARCHAR(45) NOT NULL,
	organizacao_senha VARCHAR(45) NOT NULL,
    	organizacao_telefone VARCHAR(15),
    	organizacao_status VARCHAR(45) NOT NULL,
organizacao_descricao VARCHAR(255),
organizacao_justificativa VARCHAR(255),
	PRIMARY KEY  (organizacao_id)
);

create table doacao (
	doacao_id SMALLINT AUTO_INCREMENT,
	doacao_doador_id SMALLINT,
    	doacao_obra_id SMALLINT,
    	doacao_data DATE NOT NULL,
    	doacao_valor DOUBLE,   /*aqui deverá ficar registrado o valor que será pago pelo usuário doador ao adquirir uma obra*/
    	PRIMARY KEY  (doacao_id),
	FOREIGN KEY (doacao_doador_id) REFERENCES doador (doador_id),
	FOREIGN KEY (doacao_obra_id) REFERENCES obra (obra_id)
);


create table doador (
	doador_id SMALLINT AUTO_INCREMENT,
	doador_nome VARCHAR(45) NOT NULL,
	doador_email VARCHAR(45) NOT NULL,
	doador_senha VARCHAR(45) NOT NULL,
    PRIMARY KEY  (doador_id)
	);

create table obra (
	obra_id SMALLINT AUTO_INCREMENT,
	obra_titulo VARCHAR(45) NOT NULL,
    obra_descricao VARCHAR(100) NOT NULL, 
    obra_organizacao_id SMALLINT,
    obra_artista_id SMALLINT,
    obra_preco DOUBLE,
    PRIMARY KEY  (obra_id),
	FOREIGN KEY (obra_organizacao_id) REFERENCES organizacao (organizacao_id),
    FOREIGN KEY (obra_artista_id) REFERENCES artista (artista_id)
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
("1", "monalisa", "a mais bela", "1", "1", 10),
("2", "o grito", "berrante", "2", "3", "15"),
("3", "guernica", "espanha", "1", "3", "30");

insert into organizacao values 
("1", "32767", "AjudaMuito", "AM@gmail", "am12", "137893", "estamos bem"),
("2", "87656", "MuitoBoa", "MB@hotmail", "mb12", "648769", "estamos ajudando");

insert into doacao values
("1", "2", "1", "2020-10-12", "30"),
("2", "1", "3", "2020-10-13", "100"),
("3", "3", "1", "2020-11-14", "40");

select o.obra_titulo, org.organizacao_nome, o.obra_preco, a.artista_nome from obra o
join artista a on o.obra_artista_id = a.artista_id
join organizacao org on org.organizacao_id = o.obra_organizacao_id;

SELECT ong.organizacao_id, ong.organizacao_nome, d.doacao_valor, o.obra_titulo
FROM obra o 
join doacao d on o.obra_id = d.doacao_obra_id
join organizacao ong on ong.organizacao_id = o.obra_organizacao_id;
