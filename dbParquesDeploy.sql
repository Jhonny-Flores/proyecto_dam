CREATE DATABASE dbparques;
USE dbparques;

CREATE TABLE parque
(
    idParque int not null PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(100) not null,
    pais varchar(100) not null,
	estado varchar(100) not null,
    ciudad varchar(100) not null,
    direccion varchar(200) not null
);

CREATE TABLE empleado
(
    idEmpleado int not null PRIMARY KEY AUTO_INCREMENT,
    idParque int not null,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    edad int not null default '18',
    telefono varchar(9) not null,
    direccion varchar(200) not null,
    FOREIGN KEY(idParque) REFERENCES parque(idParque)
    ON DELETE CASCADE
);

CREATE TABLE usuario
(
    username varchar(50) not null PRIMARY KEY,
    pswd blob not null,
    idEmpleado int not null,
    rol varchar(50) not null,
    estado enum('Activo', 'Inactivo') not null,
    FOREIGN KEY(idEmpleado) REFERENCES empleado(idEmpleado)
    ON DELETE CASCADE
);

CREATE TABLE clasificacion
(
    idClasificacion int not null PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(100) not null,
    rangoInicial int not null,
    rangoFinal int null
);

CREATE TABLE registro
(
    idRegistro int not null PRIMARY KEY AUTO_INCREMENT,
    idParque int not null,
    usuarioCreador varchar(50) not null,
    fechaCreacion date not null,
    FOREIGN KEY(idParque) REFERENCES parque(idParque),
    FOREIGN KEY(usuarioCreador) REFERENCES usuario(username)
    ON DELETE CASCADE
);

CREATE TABLE DetalleRegistro
(
    idDetalle int not null PRIMARY KEY AUTO_INCREMENT,
    idRegistro int not null,
    idClasificacion int not null,
    totalVisitantes int not null,
    FOREIGN KEY(idRegistro) REFERENCES registro(idRegistro),
    FOREIGN KEY(idClasificacion) REFERENCES clasificacion(idClasificacion)
    ON DELETE CASCADE
);


insert into clasificacion (nombre,rangoInicial,rangoFinal) values ('Primera niñez (0-6 años)',0,6),('Segunda niñez (7-12 años)',7,12),
('Adolescentes (13-18 años)',13,18),('Jóvenes adultos (19-26 años)',19,26),('Adultos (27-59 años)',27,59),
('Adultos mayores (60 en adelante)',60,125);

insert into parque(nombre,pais,estado,ciudad,direccion) values('Parque Sunset','El Salvador','San Salvador','San Salvador','Estadio Cuscatlan');
insert into empleado(idParque,nombre,apellido,edad,telefono,direccion) values(1,'Juan Perez','Garcia',22,'75620593','Colonia Atlacat');
insert into usuario(username,pswd,idEmpleado,rol) values('administrador','4D186321C1A7F0F354B297E8914AB240',1,'Administrador');