create database dbportfolio;
use dbportfolio;

CREATE TABLE portfolio (
   id_portfolio varchar(36) NOT NULL,
   id_user varchar(36),
   balance double NOT NULL,
   PRIMARY KEY (id_portfolio)
);

CREATE TABLE stocktransaction (
  id_stocktransaction varchar(36) NOT NULL,
  id_portfolio varchar(36) NOT NULL,
  id_stock varchar(36) NOT NULL,
  st_date date NOT NULL,
  amount double NOT NULL,
  PRIMARY KEY (id_stocktransaction)
);

insert into stocktransaction(id_stocktransaction, id_portfolio, id_stock, st_date,amount) values (uuid(), uuid(), uuid(), "2002-02-02",20);
select * from stocktransaction;

insert into portfolio(id_portfolio, id_user, balance) values (uuid(), uuid(), 100.00);
select * from portfolio;