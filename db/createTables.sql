-- Reset database
--Derby does not support DROP TABLE IF EXISTS 

DROP TABLE REGISTROSENTRADASALIDATALLER;
DROP TABLE ORDENESDEREPARACION;
DROP TABLE TALLERES;
DROP TABLE INCIDENCIAS;
DROP TABLE TIPOSDEINCIDENCIA;
DROP TABLE ENTREGAS;
DROP TABLE ALQUILERES;
DROP TABLE RESERVAS;
DROP TABLE ESTADOSRESERVA;
DROP TABLE VEHICULOS;
DROP TABLE MODELOS;
DROP TABLE MARCAS;
DROP TABLE ESTADOSVEHICULO;
DROP TABLE CATEGORIASDETAMANO;
DROP TABLE CATEGORIASDEGAMA;

DROP TABLE DISPONIBILIDADES;
DROP TABLE VINCULACIONESCONLAEMPRESA;
DROP TABLE ROLESENEMPRESA;
DROP TABLE TIPOSDEDISPONIBILIDAD;
DROP TABLE TIPOSDEVINCULACION;
DROP TABLE TIPOSDEROL;

DROP TABLE PERFILES;
DROP TABLE CLIENTES;
DROP TABLE EMPLEADOS;
DROP TABLE USUARIOS;
DROP TABLE PUNTOS;
DROP TABLE DIRECCIONES;





-- Enum
create table TIPOSDEROL
(
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)
);

INSERT INTO TIPOSDEROL
VALUES  (1,'GerenteDePunto'),
        (2,'AtencionEnPunto'),
        (3,'TecnicoEnPunto');

-- Enum
create table TIPOSDEVINCULACION
(
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)

);

INSERT INTO TIPOSDEVINCULACION
VALUES  (1,'Contratado'),
        (2,'Despedido'),
        (3,'EnERTE');

-- Enum
create table TIPOSDEDISPONIBILIDAD
(
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)
);

INSERT INTO TIPOSDEDISPONIBILIDAD
VALUES  (1,'Vacaciones'),
        (2,'BajaTemporal'),
	(3,'Trabajando');


-- Datatype
create table DIRECCIONES
(
	Id SMALLINT not null,
	NombreDeLaVia VARCHAR(20) not null,
	Numero SMALLINT,
	Otros VARCHAR(20),
	CodigoPostal INTEGER not null,
	Localidad VARCHAR(20) not null,
	Provincia VARCHAR(20) not null,
		PRIMARY KEY(Id)
);

INSERT INTO DIRECCIONES
VALUES (7263, 'Calle Labradores', 33, NULL, 47005, 'Valladolid', 'Valladolid'),
(3535, 'Calle Galera', 14, NULL, 47270, 'Cigales', 'Valladolid'),
(9487, 'Calle Santiago', 2, NULL, 47001, 'Valladolid', 'Valladolid'),
(6274, 'Plaza Mayor', 65, NULL, 47001, 'Valladolid', 'Valladolid'),
(7252, 'Paseo Zorrilla', 77, NULL, 47005, 'Valladolid', 'Valladolid'),
(9345, 'Plaza Universidad', 17, NULL, 47006, 'Valladolid', 'Valladolid');

-- Entity
create table PUNTOS
(
	Id SMALLINT not null primary key,
	Nombre VARCHAR(50) not null,
	Email VARCHAR(100) not null,
	Telefono VARCHAR(12) not null,
	Localizacion SMALLINT not null,
		FOREIGN KEY(Localizacion) REFERENCES DIRECCIONES(Id)
);

INSERT INTO PUNTOS
VALUES (100, 'Punto Labradores', 'labradores-vll@pucelacar.com', '983224153', 7263),
       (101, 'Punto Paseo Zorilla', 'paseozorrilla-vll@pucelacar.com', '983521234', 7252),
       (102, 'Punto Plaza de la Universidad', 'plazauniversidad-vll@pucelar.com', '983536178', 9345);

-- Entity
create table USUARIOS
(
	Nif VARCHAR(9) not null primary key,
	Nombre VARCHAR(50) not null,
	Password VARCHAR(15) not null,
	Email VARCHAR(100) not null,
	Telefono VARCHAR(12) not null,
	DireccionPostal SMALLINT not null,
		FOREIGN KEY(DireccionPostal) REFERENCES DIRECCIONES(Id)
);

INSERT INTO USUARIOS
VALUES ('20803099J', 'Andrea Gonzalez Diaz', 'hola123', 'andrea.gonzdiaz@pucela.car.com', '652348345', 3535),
('08567104H', 'Manuel Perez Molina', 'como33', 'manuel.perezmolina@pucela.car.com', '612314253', 9487),
('72130942K', 'Laura Sanchez Montero', 'mipassword2', 'laura.sanchezmontero@pucela.car.com', '689235461', 6274),
('88243081Q', 'Jose de Casares', '1234', 'jose.casares@mail.com', '623841093', 7263),
('26209381F', 'Quirino Querol', 'querool', 'quirino.querol@mail.com', '651235855', 7252),
('66111353S', 'Ana Sofia Cobo Rubio', 'ana21', 'ana.cobo@mail.com', '678123408', 6274);

-- Entity
create table EMPLEADOS
(
	Nif VARCHAR(9) not null primary key,
	NumeroSeguridadSocial VARCHAR(12) not null,
	Iban VARCHAR(24) not null,
	FechaInicioEnEmpresa DATE not null,
	DestinadoEn SMALLINT not null,
            FOREIGN KEY(Nif) REFERENCES USUARIOS(Nif),
            FOREIGN KEY(DestinadoEn) REFERENCES PUNTOS(Id)
);

INSERT INTO EMPLEADOS
VALUES ('20803099J', 'M03.010.1980', 'ES2020802599577376256315', '2021-04-12', 100),
('08567104H', 'H01.214.3487', 'ES3321005839749185648154', '2022-07-14', 100),
('72130942K', 'M45.621.4992', 'ES0304874611265518291243', '2021-02-27', 100);

-- Association
create table ROLESENEMPRESA
(
	ComienzoEnRol DATE not null,
	Empleado VARCHAR(9) not null,
	Rol SMALLINT not null,
            FOREIGN KEY(Empleado) REFERENCES EMPLEADOS(Nif),
            FOREIGN KEY(Rol) REFERENCES TIPOSDEROL(IdTipo)
);

INSERT INTO ROLESENEMPRESA
VALUES ('2021-04-12', '20803099J', 1), 
('2022-07-14', '08567104H', 2),
('2021-02-27', '72130942K', 3);


-- Association
create table VINCULACIONESCONLAEMPRESA
(
	Inicio DATE not null,
	Empleado VARCHAR(9) not null,
	Vinculo SMALLINT not null,
		FOREIGN KEY(Empleado) REFERENCES EMPLEADOS(Nif),
		FOREIGN KEY(Vinculo) REFERENCES TIPOSDEVINCULACION(IdTipo) 
);

INSERT INTO VINCULACIONESCONLAEMPRESA
VALUES ('2021-04-12', '20803099J', 1),
('2022-07-14', '08567104H', 1),
('2021-02-27','72130942K', 1);

-- Association
create table DISPONIBILIDADES
(
	Comienzo DATE not null,
	FinalPrevisto DATE,
	Empleado VARCHAR(9) not null,
	Disponibilidad SMALLINT not null,
		FOREIGN KEY(Empleado) REFERENCES EMPLEADOS(Nif),
		FOREIGN KEY(Disponibilidad) REFERENCES TIPOSDEDISPONIBILIDAD(IdTipo)
);

INSERT INTO DISPONIBILIDADES
VALUES ('2023-04-09', '2023-04-18', '20803099J', 3),
('2022-07-14', NULL,'08567104H', 3),
('2023-02-05', '2023-05-15', '72130942K', 3);

-- Entity
create table CLIENTES
(	Nif VARCHAR(9) not null,
	NumeroCliente INTEGER not null unique,
	TarjetaCredito VARCHAR(16) not null,
		PRIMARY KEY(Nif),
		FOREIGN KEY(Nif) REFERENCES USUARIOS(Nif)
);

INSERT INTO CLIENTES 
VALUES ('88243081Q', 1, '4013994445205892'),
('26209381F', 2, '4019283879695149'),
('66111353S', 3, '4002676865645714');

-- Entity
create table PERFILES
(
	NumeroCliente INTEGER not null,
	Puntos SMALLINT not null,
		PRIMARY KEY(NumeroCliente),
		FOREIGN KEY(NumeroCliente) REFERENCES CLIENTES(NumeroCliente)
);
INSERT INTO PERFILES 
VALUES (1, 150),
(2, 70),
(3, 400);

-- Powertype
create table CATEGORIASDEGAMA
(
	Id SMALLINT not null,
	Nombre VARCHAR(10) not null unique,
	Prestaciones VARCHAR(250) not null,
	ExtraPrecioAlDia REAL not null,
		PRIMARY KEY(Id)
);

INSERT INTO CATEGORIASDEGAMA
VALUES (1, 'Media', 'Una descripcion de gama media', 0),
       (2, 'Alta', 'Una descripcion de gama alta', 10);

-- Powertype
create table CATEGORIASDETAMANO 
(
	Id SMALLINT not null,
	Nombre VARCHAR(10) not null unique,
	Minimo REAL not null,
	Maximo REAL not null,
	PrecioPorDia REAL not null,
	FactorEntregaEnOtroPunto REAL not null,
		PRIMARY KEY(Id)
);

INSERT INTO CATEGORIASDETAMANO
VALUES  (1, 'Pequeno', 2.4, 3.5, 10, 1.3),
	(2, 'Mediano', 3.6, 4.5, 15, 1.7),
	(3, 'Grande', 4.6, 6, 20, 2.0);

-- Enum
create table ESTADOSVEHICULO
(
	Id SMALLINT not null,
	Nombre VARCHAR(30) not null unique,
		PRIMARY KEY(Id)
);

INSERT INTO ESTADOSVEHICULO
VALUES  (1, 'Disponible'),
        (2, 'EnAlquiler'),
        (3, 'EnReserva'),
        (4, 'EnTaller'),
        (5, 'Averiado'),
        (6, 'EnPreparacion'),
        (7, 'PropuestoParaBaja');

-- Entity
create table MARCAS
(
	Id SMALLINT not null,
	Nombre VARCHAR(20) not null unique,
		PRIMARY KEY(Id)
);

INSERT INTO MARCAS
VALUES (1, 'Audi'),
       (2, 'Mercedes'),
       (3, 'Ford'),
       (4, 'Skoda'),
       (5, 'Citroen'),
       (6, 'Peugeot'),
       (7, 'Opel'),
       (8, 'Volkswagen');

-- Entity
create table MODELOS
(
	Id SMALLINT not null,
	Nombre VARCHAR(20) not null unique,
	Puertas SMALLINT not null,
	Largo REAL not null,
	Ancho REAL not null,
	Alto REAL not null,
	CapacidadMaletero REAL not null,
	Pasajeros SMALLINT not null,
	Marca SMALLINT not null,
	CategoriaTamano SMALLINT not null,
	CategoriaGama SMALLINT not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Marca) REFERENCES MARCAS(Id),
		FOREIGN KEY(CategoriaTamano) REFERENCES CATEGORIASDETAMANO(Id),
		FOREIGN KEY(CategoriaGama) REFERENCES CATEGORIASDEGAMA(Id)
);

INSERT INTO MODELOS
VALUES (333, 'A4', 5, 4.762, 1.847, 1.401, 420.0, 5, 1, 1, 2),
       (123, 'Clase C', 5, 4.686, 1.810, 1.402, 490.0, 5, 2, 2, 2),
       (725, 'Golf', 5, 4.284, 1.789, 1.465, 350.0, 5, 8, 1, 2),
       (803, 'C1', 4, 3.466, 1.615, 1.460, 320.0, 4, 5, 1, 1);

-- Entity
create table VEHICULOS
(
	Matricula VARCHAR(7) not null,
	Color VARCHAR(20) not null,
	Estado SMALLINT not null,
	PuntoAsignado SMALLINT not null,
	SituadoEn SMALLINT,
	Modelo SMALLINT not null,
		PRIMARY KEY(Matricula),
		FOREIGN KEY(Estado) REFERENCES ESTADOSVEHICULO(Id),
		FOREIGN KEY(PuntoAsignado) REFERENCES PUNTOS(Id),
		FOREIGN KEY(SituadoEn) REFERENCES PUNTOS(Id),
		FOREIGN KEY(Modelo) REFERENCES MODELOS(Id)
);

INSERT INTO VEHICULOS
VALUES ('7251LZK', 'Rojo', 2, 100, 101, 333),
       ('3333MBC', 'Plateado', 1, 100, 102, 123),
       ('1937LPM', 'Blanco', 2, 100, 100, 725),
       ('9991LWJ', 'Azul', 4, 100, 101, 803), 
       ('2468POI', 'Negro', 4, 100, 100, 123);

-- Enum
create table ESTADOSRESERVA
(
	Id SMALLINT not null,
	Nombre VARCHAR(30) not null unique,
		PRIMARY KEY(Id)

);

INSERT INTO ESTADOSRESERVA
VALUES  (1,'Solicitada'),
        (2,'EnFirme'),
        (3, 'Anulada'),
        (4, 'Facturada'),
        (5, 'EnAlquiler'),
        (6,'Finalizada');

-- Entity
create table RESERVAS
(
	Id INTEGER not null,
	Estado SMALLINT not null,
	FechaReserva DATE not null,
	FechaInicio DATE not null,
	HoraInicio TIME not null,
	FechaFin DATE not null,
	HoraFin TIME not null,
	Vehiculo VARCHAR(7) not null,
	PuntoRecogida SMALLINT not null,
	PuntoEntrega SMALLINT not null,
	Cliente VARCHAR(9) not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Estado) REFERENCES ESTADOSRESERVA(Id),
		FOREIGN KEY(Vehiculo) REFERENCES VEHICULOS(Matricula),
		FOREIGN KEY(PuntoRecogida) REFERENCES PUNTOS(Id),
		FOREIGN KEY(PuntoEntrega) REFERENCES PUNTOS(Id),
		FOREIGN KEY(Cliente) REFERENCES CLIENTES(Nif)

);

-- El segundo caso es el caso de error para nuestro caso de prueba
INSERT INTO RESERVAS 
VALUES (1, 5, '2023-03-14', '2023-04-30', '11:00:00', '2023-05-14', '20:00:00', '7251LZK', 102, 100, '88243081Q'),
(2, 4, '2023-02-28', '2023-05-01', '10:00:00', '2023-05-01', '20:00:00', '3333MBC', 100, 101, '26209381F'),
(3, 5, '2023-04-04', '2023-05-02', '10:30:00', '2023-05-11', '19:00:00', '1937LPM', 101, 100, '66111353S');

-- Entity. Transaction.
create table ALQUILERES
(
	Id INTEGER not null,
	Momento TIMESTAMP not null,
	Empleado VARCHAR(9) not null,
	Reserva INTEGER not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Empleado) REFERENCES EMPLEADOS(Nif),
		FOREIGN KEY(Reserva) REFERENCES RESERVAS(Id)
);

INSERT INTO ALQUILERES 
VALUES (1, '2023-04-30 11:09:00', '08567104H', 1),
(2, '2023-05-01 11:00:00', '08567104H', 2),
(3, '2023-05-02 10:32:50', '08567104H', 3);

-- Entity. Transaction.
create table ENTREGAS
(
	Id INTEGER not null,
	Momento TIMESTAMP not null,
	Alquiler INTEGER not null,
	Empleado VARCHAR(9) not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Alquiler) REFERENCES ALQUILERES(Id),
		FOREIGN KEY(Empleado) REFERENCES EMPLEADOS(Nif)
);

INSERT INTO ENTREGAS
VALUES (1, '2023-05-01 19:46:00', 2, '08567104H');


-- Enum
create table TIPOSDEINCIDENCIA
(
	Id SMALLINT not null,
	Nombre VARCHAR(20) not null unique,
		PRIMARY KEY(Id)
);

INSERT INTO TIPOSDEINCIDENCIA
VALUES  (1,'Accidente'),
        (2,'Averia'),
        (3,'SuciedadExtrema'),
        (4,'RetrasoEnLaEntrega');

-- Entity
create table INCIDENCIAS
(
	Id INTEGER not null,
	Tipo SMALLINT not null,
	Descripcion VARCHAR(250) not null,
	CargoAsociado REAL,
	Entrega INTEGER not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Tipo) REFERENCES TIPOSDEINCIDENCIA(Id),
		FOREIGN KEY(Entrega) REFERENCES ENTREGAS(Id)
);

-- Entity
create table TALLERES
(
	Id INTEGER not null,
	Nombre VARCHAR(50) not null,
	Telefono VARCHAR(12) not null,
	Email VARCHAR(100) not null,
	Localizacion SMALLINT not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Localizacion) REFERENCES DIRECCIONES(Id)
);

INSERT INTO TALLERES
VALUES (1, 'Talleres Paco', '983654792', 'tallerespaco@gmail.com', 9345);

-- Association + Data
create table ORDENESDEREPARACION
(
	Id INTEGER not null,
	Momento TIMESTAMP not null,
	Razones VARCHAR(250) not null,
	Vehiculo VARCHAR(7) not null,
	Taller INTEGER not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Vehiculo) REFERENCES VEHICULOS(Matricula),
		FOREIGN KEY(Taller) REFERENCES TALLERES(Id)
);

INSERT INTO ORDENESDEREPARACION
VALUES  (1, '2023-03-02 17:26:00', 'Motor roto', '9991LWJ', 1),
        (2, '2022-10-22 11:00:00', 'Caja de cambios destrozada', '2468POI' , 1);


-- Auxiliary
create table REGISTROSENTRADASALIDATALLER
(
	OrdenDeReparacion INTEGER not null,
	LlegadaAlTaller TIMESTAMP,
	SalidaDelTaller TIMESTAMP,
	EntradaEnPunto TIMESTAMP,
	PropuestoParaBaja BOOLEAN,
	RazonesParaBaja VARCHAR(250),
		FOREIGN KEY(OrdenDeReparacion) REFERENCES ORDENESDEREPARACION(Id)
);

INSERT INTO REGISTROSENTRADASALIDATALLER
VALUES (1, '2023-03-06 18:00:32', '2023-03-06 22:12:00', NULL, TRUE, 'No se puede reparar el motor'),
       (2, '2023-03-06 17:00:32', '2023-03-06 21:10:00', NULL, FALSE, NULL);