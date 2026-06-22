CREATE TABLE IF NOT EXISTS SensorGpsLectura (
    idLecturaGps INT AUTO_INCREMENT PRIMARY KEY,
    idCamion INT NOT NULL,
    fechaHora DATETIME NOT NULL,
    latitud DECIMAL(10, 6) NOT NULL,
    longitud DECIMAL(10, 6) NOT NULL,
    velocidadKmh DECIMAL(6, 2) NOT NULL,
    ruta VARCHAR(150) NOT NULL,
    tiempoRecorridoMinutos INT NOT NULL,
    estadoSenal VARCHAR(20) NOT NULL DEFAULT 'Con senal',
    CONSTRAINT fk_sensor_gps_camion
        FOREIGN KEY (idCamion) REFERENCES Camion(idCamion)
        ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_sensor_gps_camion_fecha (idCamion, fechaHora),
    INDEX idx_sensor_gps_ruta (ruta)
);

CREATE TABLE IF NOT EXISTS SensorTemperaturaLectura (
    idLecturaTemperatura INT AUTO_INCREMENT PRIMARY KEY,
    idCamion INT NOT NULL,
    fechaHora DATETIME NOT NULL,
    temperaturaCelsius DECIMAL(5, 2) NOT NULL,
    limiteCritico DECIMAL(5, 2) NOT NULL DEFAULT 8.00,
    estado VARCHAR(20) NOT NULL DEFAULT 'Normal',
    observacion TEXT,
    CONSTRAINT fk_sensor_temperatura_camion
        FOREIGN KEY (idCamion) REFERENCES Camion(idCamion)
        ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX idx_sensor_temp_camion_fecha (idCamion, fechaHora),
    INDEX idx_sensor_temp_estado (estado)
);
