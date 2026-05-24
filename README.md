#BDD PARA USAR EL PROGRAMA

CREATE DATABASE Transportes_Hirata_v2;

USE Transportes_Hirata_v2;
show tables;



CREATE TABLE Conductor (
    idConductor INT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(12) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    licencia VARCHAR(50) NOT NULL,
    telefono VARCHAR(20),
    clave VARCHAR(50) NOT NULL 
);

CREATE TABLE Camion (
    idCamion INT AUTO_INCREMENT PRIMARY KEY,
    patente VARCHAR(10) UNIQUE NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    kilometrajeActual INT NOT NULL DEFAULT 0,
    idConductor INT, 
    FOREIGN KEY (idConductor) REFERENCES Conductor(idConductor) ON DELETE SET NULL 
);

CREATE TABLE Mantenimiento (
    idMantenimiento INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    tipo VARCHAR(20) NOT NULL, 
    descripcion TEXT NOT NULL,
    kilometrajeMantenimiento INT NOT NULL,
    idCamion INT NOT NULL, 
    FOREIGN KEY (idCamion) REFERENCES Camion(idCamion) ON DELETE CASCADE
);

CREATE TABLE equipos (
	idEquipo INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    codigo VARCHAR(12) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    ubicacion VARCHAR(50) NOT NULL,
    estado VARCHAR(25) NOT NULL
    );

CREATE TABLE MantenimientoEquipo (
    idMantenimientoEquipo INT AUTO_INCREMENT PRIMARY KEY,
    fechaMantenimiento DATE NOT NULL,
    tipoMantenimiento VARCHAR(20) NOT NULL,
    descripcion TEXT NOT NULL,
    tecnicoResponsable VARCHAR(100) NOT NULL,
    observaciones TEXT,
    estado VARCHAR(25) NOT NULL,
    idEquipo INT NOT NULL,
    FOREIGN KEY (idEquipo) REFERENCES equipos(idEquipo) ON DELETE CASCADE
);

CREATE TABLE Software (
    idSoftware INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    versionActual VARCHAR(50) NOT NULL,
    proveedor VARCHAR(100),
    tipo VARCHAR(50) NOT NULL,
    estado VARCHAR(25) NOT NULL
);

CREATE TABLE SoftwareEquipo (
    idSoftwareEquipo INT AUTO_INCREMENT PRIMARY KEY,
    idEquipo INT NOT NULL,
    idSoftware INT NOT NULL,
    versionInstalada VARCHAR(50) NOT NULL,
    fechaInstalacion DATE NOT NULL,
    estado VARCHAR(25) NOT NULL,
    UNIQUE KEY uk_software_equipo (idEquipo, idSoftware),
    FOREIGN KEY (idEquipo) REFERENCES equipos(idEquipo) ON DELETE CASCADE,
    FOREIGN KEY (idSoftware) REFERENCES Software(idSoftware) ON DELETE CASCADE
);

CREATE TABLE ActualizacionSoftware (
    idActualizacion INT AUTO_INCREMENT PRIMARY KEY,
    fechaActualizacion DATE NOT NULL,
    versionAnterior VARCHAR(50),
    versionNueva VARCHAR(50) NOT NULL,
    tecnicoResponsable VARCHAR(100) NOT NULL,
    estado VARCHAR(25) NOT NULL,
    observaciones TEXT,
    idEquipo INT NOT NULL,
    idSoftware INT NOT NULL,
    FOREIGN KEY (idEquipo) REFERENCES equipos(idEquipo) ON DELETE CASCADE,
    FOREIGN KEY (idSoftware) REFERENCES Software(idSoftware) ON DELETE CASCADE
);

CREATE TABLE Pieza (
    idPieza INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    marca VARCHAR(50),
    stockActual INT NOT NULL DEFAULT 0,
    stockMinimo INT NOT NULL DEFAULT 0,
    ubicacion VARCHAR(50),
    estado VARCHAR(25) NOT NULL
);

CREATE TABLE MovimientoInventario (
    idMovimiento INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    tipoMovimiento VARCHAR(20) NOT NULL,
    cantidad INT NOT NULL,
    motivo VARCHAR(150) NOT NULL,
    tecnicoResponsable VARCHAR(100) NOT NULL,
    idPieza INT NOT NULL,
    idMantenimientoEquipo INT,
    FOREIGN KEY (idPieza) REFERENCES Pieza(idPieza) ON DELETE CASCADE,
    FOREIGN KEY (idMantenimientoEquipo) REFERENCES MantenimientoEquipo(idMantenimientoEquipo) ON DELETE SET NULL
);

CREATE TABLE Usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(12) UNIQUE NOT NULL,
    clave VARCHAR(255) NOT NULL,  
    cargo ENUM('administrador', 'conductor','personal') NOT NULL
);

INSERT INTO Usuario (rut, clave, cargo)
VALUES ('1.111.111-1', '12345678', 'administrador');

INSERT INTO Usuario (rut, clave, cargo)
VALUES ('2.222.222-2', '12345678', 'personal');

select * from usuario;


#USUARIOS Y PERMISOS

Administrador:
- Puede registrar camiones, conductores, equipos y personal.
- Puede registrar mantenimientos de camiones y equipos.
- Puede registrar inventario de piezas y controlar movimientos de stock.
- Puede registrar actualizaciones de software en equipos.
- Puede ver historiales de mantenimiento.

Personal:
- Puede registrar mantenimientos de equipos de oficina.
- Puede registrar actualizaciones de software en equipos.
- No puede registrar camiones, conductores, equipos ni personal.
- No puede realizar mantenimientos de camiones.
- No puede ver historiales de mantenimiento ni actualizar kilometraje.

Usuario administrador de prueba:
RUT: 1.111.111-1
Clave: 12345678
Cargo: administrador

Usuario personal de prueba:
RUT: 2.222.222-2
Clave: 12345678
Cargo: personal

Para crear mas usuarios de personal desde el sistema:
Ingresar como administrador y usar la opcion Registro -> Registrar personal.
