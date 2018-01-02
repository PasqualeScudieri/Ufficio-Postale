DROP database IF EXISTS provaProgetto3;
CREATE database provaProgetto3;
USE provaProgetto3;

DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente(
	nome varchar(50) not null,
    cognome varchar(50) not null,
    CF char(16) not null primary key,
    luogonascita varchar(50),
    datanascita date,
	indirizzo varchar(70)
);

DROP TABLE IF EXISTS telefono;

CREATE TABLE telefono(
	numero char(10) not null primary key,
    cliente char(16),
    FOREIGN KEY (cliente) REFERENCES cliente(CF) ON DELETE CASCADE
);

DROP TABLE IF EXISTS conto;

CREATE TABLE conto(
	IBAN char(27) not null primary key,
    saldo double default 0
);
/*
DROP TABLE IF EXISTS libretto;

CREATE TABLE libretto(
	tassoInt double,
    iban char(27) primary key not null,
    FOREIGN KEY (iban) REFERENCES conto(IBAN) ON DELETE CASCADE
);
*/
DROP TABLE IF EXISTS bancoposta;

CREATE TABLE bancoposta(
	tassoInt double,
    servInternet enum('y','n') default 'n',
    costo double default 0,
    carta enum('y','n') default 'n',
	iban char(27) primary key not null,
    FOREIGN KEY (iban) REFERENCES conto(IBAN) ON DELETE CASCADE
);

DROP TABLE IF EXISTS postePay;

CREATE TABLE postePay(
	numCarta int not null UNIQUE AUTO_INCREMENT,
    scadenza date,
    codSicur int not null ,
	iban char(27) primary key not null,
    FOREIGN KEY (iban) REFERENCES conto(IBAN) ON DELETE CASCADE
)AUTO_INCREMENT=100;

DROP TABLE IF EXISTS apre;

CREATE TABLE apre(
	ibanConto char(27) not null,
	clienteCF char(16) not null,
    PRIMARY KEY (ibanConto, clienteCF),
	FOREIGN KEY (clienteCF) REFERENCES cliente(CF) ON DELETE CASCADE, 
    FOREIGN KEY (ibanConto) REFERENCES conto(IBAN)	ON DELETE CASCADE
	
);

DROP TABLE IF EXISTS operazione;

CREATE TABLE operazione(
	codice int  not null AUTO_INCREMENT, 
    importo double not null,
    dataOper TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo enum('sportello', 'onLine') DEFAULT 'sportello',
	PRIMARY KEY (codice)	
)AUTO_INCREMENT=5;

DROP TABLE IF EXISTS dipendenti;

CREATE TABLE dipendenti(
	nome varchar(50) not null,
    cognome varchar(50) not null,
	indirizzo varchar(50) ,
    luogonascita varchar(50),
    datanascita date,
    CF char(16) unique not null,
    tipo enum('postino','addettoSportello'),
	matricola int not null primary key
);

DROP TABLE IF EXISTS effettuatoDa;

CREATE TABLE effettuatoDa(
	codice int not null,
	dipendente int not null,
    PRIMARY KEY (dipendente, codice),
	FOREIGN KEY (dipendente) REFERENCES dipendenti(matricola),
	FOREIGN KEY (codice) REFERENCES operazione(codice)
);

DROP TABLE IF EXISTS posta;

CREATE TABLE posta(
	codice int  not null AUTO_INCREMENT,
	dataSped date,
	tipo enum('pacchi', 'lettere', 'raccomandate'),
    dipendente int,
    destinatario varchar(50) not null,
    mittente varchar(16) not null,
    indirizzo varchar(70) not null,
    dataCons date,
    PRIMARY KEY (codice),
    FOREIGN KEY (mittente) REFERENCES cliente(CF),
	FOREIGN KEY (dipendente) REFERENCES dipendenti(matricola)
)AUTO_INCREMENT=10;

DROP TABLE IF EXISTS pacchi;

CREATE TABLE pacchi(
	peso double not null,
	volume double not null,
	codice int not null primary key,
	FOREIGN KEY (codice) REFERENCES posta(codice) ON DELETE CASCADE
);

DROP TABLE IF EXISTS interessatoDa;

CREATE TABLE interessatoDa(
	ibanConto char(27) not null,
	codOperazione int not null,
	segno enum('+', '-') not null,
    PRIMARY KEY (ibanConto, codOperazione), 
	FOREIGN KEY (ibanConto) REFERENCES conto(IBAN)	ON DELETE CASCADE,
	FOREIGN KEY (codOperazione) REFERENCES operazione(codice) ON DELETE CASCADE

);

CREATE TABLE utenteRegistrato(
    cliente char(16),
    user char(30) unique not null,
    password char(27) default '',
    tipo enum('user', 'admin', 'gestore') not null,
    PRIMARY KEY (cliente)
 );
 
CREATE TABLE notifiche(
	cliente char(16) not null,
	dipendente int not null,
	tipo enum('nuovaPostePay', 'nuovoBancoPosta','aggiungiCarta', 'aggiungiServiziInternet','postaConsegnata'),
	codice int not null AUTO_INCREMENT,
	iban char(27),
	codPosta int,
	PRIMARY KEY (codice),
	FOREIGN KEY (dipendente) REFERENCES dipendenti(matricola),
	FOREIGN KEY (cliente) REFERENCES cliente(CF)
)AUTO_INCREMENT=1;
/*****************popoliamo la base di dati******************/
LOAD DATA LOCAL INFILE 'cliente.txt' INTO TABLE cliente FIELDS TERMINATED BY','(nome, cognome, CF, indirizzo, luogonascita,datanascita);
LOAD DATA LOCAL INFILE 'telefono.txt' INTO TABLE telefono FIELDS TERMINATED BY','(cliente, numero);
LOAD DATA LOCAL INFILE 'dipendenti.txt' INTO TABLE dipendenti FIELDS TERMINATED BY','(nome, cognome, indirizzo, luogonascita, datanascita,matricola,tipo,cf);
LOAD DATA LOCAL INFILE 'conto.txt' INTO TABLE conto FIELDS TERMINATED BY','(IBAN,saldo);
/*LOAD DATA LOCAL INFILE 'libretto.txt' INTO TABLE libretto FIELDS TERMINATED BY','(iban, tassoInt);
*/
LOAD DATA LOCAL INFILE 'bancoposta.txt' INTO TABLE bancoposta FIELDS TERMINATED BY','(iban,tassoInt, servInternet, costo, carta);
LOAD DATA LOCAL INFILE 'postepay.txt' INTO TABLE postePay FIELDS TERMINATED BY','(iban,scadenza,codSicur);
LOAD DATA LOCAL INFILE 'apre.txt' INTO TABLE apre FIELDS TERMINATED BY','(ibanConto, clienteCF);
LOAD DATA LOCAL INFILE 'operazioni.txt' INTO TABLE operazione FIELDS TERMINATED BY','(importo, dataOper, tipo);
LOAD DATA LOCAL INFILE 'interessatoDa.txt' INTO TABLE interessatoDa FIELDS TERMINATED BY','(ibanConto, codOperazione,segno);
LOAD DATA LOCAL INFILE 'effettuatoDa.txt' INTO TABLE effettuatoDa FIELDS TERMINATED BY','(codice, dipendente);
LOAD DATA LOCAL INFILE 'posta.txt' INTO TABLE posta FIELDS TERMINATED BY','(dataSped, dataCons, tipo, dipendente, destinatario,indirizzo, mittente);
LOAD DATA LOCAL INFILE 'pacchi.txt' INTO TABLE pacchi FIELDS TERMINATED BY','(codice, peso, volume);
/************* operazioni********************/

/*operazione 1*/
INSERT INTO cliente (nome, cognome ,CF, luogonascita, datanascita) VALUES ('Mario', 'Rossi','RSSMRA82B14G813s','Pompei', '1982-02-14');
INSERT INTO telefono(numero,cliente) VALUES ('0819548723','RSSMRA82B14G813s');

/*operazione 2*/
INSERT INTO conto(IBAN) VALUES ('IT65C256387451245963258749');
INSERT INTO postePay(scadenza,iban,codSicur) VALUES ('2017-05-02', 'IT65C256387451245963258749', '123456');
INSERT INTO apre(ibanConto , clienteCF) VALUES ('IT65C256387451245963258749','RSSMRA82B14G813s');

/*operazione 2*/
INSERT INTO conto(IBAN) VALUES ('IT65C256387451245963258750');
INSERT INTO postePay(scadenza,iban,codSicur) VALUES ('2017-05-02', 'IT65C256387451245963258750', '123456');
INSERT INTO apre(ibanConto , clienteCF) VALUES ('IT65C256387451245963258750','RSSMRA82B14G813s');


/*operazione 3*/
INSERT INTO operazione (importo) VALUES (50.00);
UPDATE conto SET saldo=saldo +50 WHERE iban='IT65C256387451245963258749';
INSERT INTO interessatoDa (ibanConto,codOperazione,segno) VALUES ('IT65C256387451245963258749',(SELECT MAX(codice) FROM operazione),'+');
INSERT INTO effettuatoDa (codice,dipendente) VALUES ((SELECT MAX(codice) FROM operazione),'422923');
  
/*operazione 4*/ 
INSERT INTO operazione (importo,tipo) VALUES (+100.00,'onLine');
UPDATE conto SET saldo=saldo +100 WHERE iban='IT65C256387451245963258749';
INSERT INTO interessatoDa (ibanConto, codOperazione,segno) VALUES ('IT65C256387451245963258749', (SELECT MAX(codice) FROM operazione),'+');

/*operazione 8*/
INSERT INTO posta(mittente, destinatario, codice ,tipo,dataSped, indirizzo) VALUES ('SCTCNZ95S55Z133K','luigi', 80,'lettere','2016-12-30', 'via lepanto 54 Pompei (NA)');

/*operazione 5*/
UPDATE posta SET dataCons='2016-12-24', dipendente=426854 WHERE codice=80; 

/*operazione 6*/
SELECT importo, dataOper, tipo FROM operazione, interessatoDa WHERE operazione.codice=interessatoDa.codOperazione AND interessatoDa.ibanConto='IT65C256387451245963258749';
SELECT saldo FROM conto WHERE iban='IT65C256387451245963258749';

/*operazione 7*/
SELECT nome, cognome,luogonascita, datanascita,count(*)AS numeroConti 
FROM cliente,apre, conto WHERE apre.ibanConto=conto.IBAN AND apre.clienteCF=cliente.CF AND cliente.CF='RSSMRA82B14G813s';
SELECT numero FROM telefono WHERE telefono.cliente='RSSMRA82B14G813s';


/*operazione 9*/
SELECT  nome, cognome,count(*) AS numOperazioni FROM cliente,apre,bancoposta,interessatoDa WHERE cliente.CF=apre.clienteCF AND
apre.ibanConto=bancoposta.iban AND apre.ibanConto=interessatoDa.ibanConto
GROUP BY cliente.CF
HAVING count(*)>5;

/*operazione 10*/
SELECT nome,cognome
FROM cliente as C, apre AS A
WHERE C.CF=A.clienteCF AND A.ibanConto IN(SELECT IBAN 
	FROM conto AS CO, interessatoDa AS I1,operazione as OP1 
	WHERE CO.IBAN=I1.ibanConto AND I1.codOperazione=OP1.codice AND OP1.dataOper>'2016-10-22' 
	GROUP BY IBAN 
	HAVING count(*) <=0.5*( SELECT count(*) 
		FROM interessatoDa AS I2, operazione AS OP2 
		WHERE I2.ibanConto=CO.IBAN AND OP2.codice=I2.codOperazione AND OP2.dataOper>= '2016-08-22' AND OP2.dataOper<='2016-10-22'));

/*operazione 11*/
(SELECT matricola FROM dipendenti,posta WHERE dipendenti.matricola=posta.dipendente AND posta.dataCons>= '2016-12-10' GROUP BY dipendenti.matricola HAVING count(*)>3
)UNION(
SELECT matricola FROM dipendenti,effettuatoDa, operazione WHERE dipendenti.matricola=effettuatoDa.dipendente AND effettuatoDa.codice=operazione.codice AND operazione.dataOper>'2016-12-10' GROUP BY dipendenti.matricola HAVING count(*)>3);

/*operazione 12*/
UPDATE conto SET saldo=saldo+10 WHERE iban='IT65C256387451245963258749';
UPDATE conto SET saldo=saldo-10 WHERE iban='IT65C256387451245963258750';
INSERT INTO operazione (importo) VALUES (50.00);
INSERT INTO interessatoDa (ibanConto, codOperazione,segno) VALUES ('IT65C256387451245963258749',(SELECT MAX(codice) FROM operazione),'+');
INSERT INTO interessatoDa (ibanConto, codOperazione,segno) VALUES ('IT65C256387451245963258750',(SELECT MAX(codice) FROM operazione),'-');
INSERT INTO effettuatoDa (codice,dipendente) VALUES ((SELECT MAX(codice) FROM operazione),'422923');




INSERT INTO posta(mittente, destinatario, codice ,tipo,dataSped, indirizzo) VALUES ('SCTCNZ95S55Z133K','Mario', 81,'lettere','2016-11-30', 'via franzesi 26 Ottaviano (NA)');
INSERT INTO posta(mittente, destinatario, codice ,tipo,dataSped, indirizzo) VALUES ('SCTCNZ95S55Z133K','Antonio', 82,'lettere','2016-10-30', 'via Roma 33 San Giuseppe vesuviano (NA)');
INSERT INTO posta(mittente, destinatario, codice ,tipo,dataSped,indirizzo) VALUES ('SCTCNZ95S55Z133K','Filippo', 83,'lettere','2016-12-20', 'via 20 settembre Pomigliano (NA)');
INSERT INTO posta(mittente, destinatario, codice ,tipo,dataSped,indirizzo) VALUES ('SCTCNZ95S55Z133K','Barbara', 84,'lettere','2016-12-21', 'via Viuli 83 Milano (MI)');




INSERT INTO utenteRegistrato(cliente, user, password, tipo) VALUES ('SCTCNZ95S55Z133K' ,'catello', 'sefungesonofelice','user');
INSERT INTO utenteRegistrato(cliente, user, password, tipo) VALUES ('BNNMRC78B02G813O' ,'marco', 'marco','user');
INSERT INTO utenteRegistrato(cliente, user, password, tipo) VALUES ('SCGLVR70T56G813V' ,'425863', 'luigi','admin');
INSERT INTO utenteRegistrato(cliente, user, password, tipo) VALUES ('ZZISNT84E59I483G' ,'254631', 'pasquale','admin');
INSERT INTO utenteRegistrato(cliente, user, password, tipo) VALUES ('GESTOREABCDEFGHI' ,'gestore', 'gestore','gestore');
