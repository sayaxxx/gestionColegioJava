
create database COLEGIO;
use COLEGIO;

create table estudiante(
docEst varchar(11) not null,
nomEst varchar(30) not null,
apeEst varchar(30) not null,
dirEst varchar(60) not null,
telEst varchar(11) not null,
primary key (docEst)
);

create table profesor(
docPro varchar(11) not null,
nomPro varchar(30) not null,
apePro varchar(30) not null,
dirPro varchar(60) not null,
telPro varchar(11) not null,
emaPro varchar(30) not null,
titPro varchar(30) not null,
primary key (docPro)
);

create table acudiente(
docAcu varchar(11) not null,
nomAcu varchar(30) not null,
apeAcu varchar(30) not null,
dirAcu varchar(60) not null,
telAcu varchar(11) not null,
emaAcu varchar(30) not null,
primary key (docAcu)
);

create table materia (
codMat int auto_increment not null,
nomMat varchar(30) not null,
graMat varchar(10) not null,
primary key (codMat)
);

create table acudientexestudiante(
consAcuxEst int auto_increment not null,
docAcuAcuxEst varchar(11) not null,
docEstAcuxEst varchar(11) not null,
primary key (consAcuxEst),
foreign key (docAcuAcuxEst) references acudiente(docAcu),
foreign key (docEstAcuxEst) references estudiante(docEst)
);


create table materiaxprofesor (
conMatxPro int auto_increment not null,
codMatMatxPro integer not null,
docProMatxPro varchar(11),
graMatxPro varchar(10),
primary key (conMatxPro),
foreign key(codMatMatxPro) references materia(codMat),
foreign key(docProMatxPro) references profesor(docPro)
);



-- INSERTAR DATOS EN TABLA PROFESOR 
INSERT INTO profesor(docPro, nomPro, apePro, dirPro, telPro, emaPro, titPro) VALUES 
("63502720","Martha","Rojas","Carrera 10 #5-20", "3204445678", "elena.gomez@email.com", "Magíster en Física"),
("91216904","Carlos","Perez","Calle 45 #12-30", "3105551234", "carlos.ruiz@email.com", "Lic. en Matemáticas"),
("13826789","Marcos","Angarita","Calle 55 #16-25", "3178865235", "marcos.angarita@email.com", "Lic. en lenguaje");

-- INSERTAR DATOS EN TABLA ESTUDIANTE 
INSERT INTO estudiante(docEst, nomEst, apeEst, dirEst, telEst) VALUES 
("103658455","juliana","hernandez", "Carrera 15 #23-10", "3204445678"),
("302654877","arlhey","abadia", "Carrera 10 #6-40", "3114335678");

-- INSERTAR DATOS EN TABLA ACUDIENTE
INSERT INTO acudiente(docAcu, nomAcu, apeAcu, dirAcu, telAcu, emaAcu) values
("106589225","Jose","diaz","Carrera 15 #23-10","3226658425","josediaz@gmail.com"),
("1265489325","jesus","bautista","Carrera 10 #6-40","3569847120","jesusbautista@gmail.com");



