create user if not exists CANDIDATE identified by 'CANDIDATE';
create database if not exists BOOKSTORE;
grant all privileges on BOOKSTORE.* to 'CANDIDATE'@'%';

USE BOOKSTORE;

CREATE TABLE IF NOT EXISTS AUTHOR (
    ID SERIAL PRIMARY KEY NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    BIO TEXT,
    BIRTHDATE DATE NOT NULL
);
    


CREATE TABLE IF NOT EXISTS BOOK (
    ID SERIAL PRIMARY KEY NOT NULL,
    ISBN CHAR(17) UNIQUE NOT NULL,
    TITLE VARCHAR(1000) NOT NULL,
    DESCRIPTION TEXT,
    RELEASE_DATE DATE NOT NULL,
    AUTHOR_ID BIGINT UNSIGNED NOT NULL,
    INDEX AUTHOR_IDX (AUTHOR_ID), 
    CONSTRAINT FK_BOOK_AUTHOR_ID FOREIGN KEY (AUTHOR_ID)
        REFERENCES AUTHOR(ID)
);

INSERT INTO AUTHOR(NAME, BIRTHDATE, BIO) VALUES('J. R. R. TOLKIEN','1892-01-03', 'Conhecido internacionalmente como J.R.R. Tolkien, John Ronald Reuel Tolkien foi um premiado escritor, professor universitário e filólogo britânico. Nascido no Estado Livre de Orange (atual África do Sul), passou a viver na Inglaterra a partir dos 3 anos de idade. Desde pequeno ele era fascinado por línguas e costumava inventar seus próprios idiomas. Doutor em Letras e Filologia pela Universidade de Liège e de Dublin, foi nomeado Comandante da Ordem do Império Britânico pela Rainha Elizabeth II e recebeu o título de doutor honorário em Letras pela Universidade de Oxford. Tolkien é considerado o "mestre da literatura fantástica". Suas obras, como O Hobbit, O Senhor dos Anéis e O Silmarillion, foram traduzidas para mais de 50 idiomas, vendendo mais de 200 milhões de cópias e influenciando continuadamente gerações e gerações de escritores e leitores no mundo todo. '),
('George R. R. Martin','1948-09-20', 'George R.R. Martin trabalhou dez anos em Hollywood como escritor e produtor de diversas séries e filmes de grande sucesso. Autor de diversos best-sellers nos EUA e na Europa, foi em meados dos anos 1990 que Martin deu início a sua mais importante obra: As crônicas de gelo e fogo, a saga de fantasia mais vendida dos últimos anos, vencedora de diversos prêmios, que ganhou a premiada adaptação Game of Thrones pela HBO e também chegou aos quadrinhos');


INSERT INTO BOOK(ISBN, TITLE, DESCRIPTION, RELEASE_DATE, AUTHOR_ID) VALUES
('978-83-731-9172-3', 
'O Senhor dos Anéis: A Sociedade do Anel', 
'Numa cidadezinha indolente do Condado, um jovem hobbit é encarregado de uma imensa tarefa. Deve empreender uma perigosa viagem através da Terra-média até as Fendas da Perdição, e lá destruir o Anel do Poder - a única coisa que impede o domínio maléfico do Senhor do Escuro.',
'1954-07-29',1),
('978-85-441-0012-7','As Crônicas de Gelo e Fogo: A Guerra dos Tronos',
'Edição comemorativa. Novo formato 16x23cm e nova capa, criada pelo ilustrador francês Marc Simonetti',
'1996-01-04',2);
    
    
