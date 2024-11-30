
CREATE TABLE IF NOT EXISTS marcas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);
-- Insertar algunas marcas por defecto
INSERT INTO marcas (nombre) VALUES ('Volvo');
INSERT INTO marcas (nombre) VALUES ('Scania');
INSERT INTO marcas (nombre) VALUES ('Fiat');

CREATE TABLE IF NOT EXISTS buses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(50) NOT NULL,
    placa VARCHAR(50) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    caracteristicas TEXT,
    marca_id INT,
    capacidad INT NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (marca_id) REFERENCES marcas(id) ON DELETE SET NULL
);

INSERT INTO buses (numero, placa, caracteristicas, marca_id, capacidad)
VALUES 
('001', 'ABC-123', 'Bus cómodo con aire acondicionado', 1, 50),
('002', 'XYZ-789', 'Bus económico', 2, 40);

CREATE TABLE IF NOT EXISTS ciudades (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre varchar (50),
    departamento varchar (50)
);

INSERT INTO ciudades (nombre, departamento)
VALUES 
('Lima', 'Lima'),
('Cusco', 'Cusco'),
('Arequipa', 'Arequipa');


CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    celular VARCHAR (40) not null,
    role VARCHAR(255) NOT NULL
);
INSERT INTO usuarios (username, password,celular, role) 
VALUES ('testuser', 'password123',"999999999", 'USER');
UPDATE usuarios SET password = '$2a$10$IhSmqi0t.mQx73zHDfBOtuAZHzWPY/IxXIns37JOvRwGxudHKx18y' WHERE username = 'testuser';
UPDATE usuarios SET role = 'USER' WHERE username = 'testuser';

INSERT INTO usuarios (username, password, celular, role)
VALUES 
('usuario1', 'password123', '987654321', 'USER'),
('usuario2', 'password123', '912345678', 'USER');


CREATE TABLE Viajes (
    idViaje INT AUTO_INCREMENT PRIMARY KEY,
    idBus INT NOT NULL,
    origen INT NOT NULL,
    destino INT NOT NULL,
    fechaSalida DATETIME NOT NULL,
    fechaLlegadaEstimada DATETIME NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    asientosDisponibles INT NOT NULL,
    FOREIGN KEY (idBus) REFERENCES Buses(id),
    FOREIGN KEY (origen) REFERENCES Ciudades(id),
    FOREIGN KEY (destino) REFERENCES Ciudades(id)
);

INSERT INTO Viajes (idBus, origen, destino, fechaSalida, fechaLlegadaEstimada, precio, asientosDisponibles)
VALUES 
(1, 1, 2, '2024-12-01 08:00:00', '2024-12-01 18:00:00', 150.00, 50),
(2, 1, 3, '2024-12-02 07:00:00', '2024-12-02 19:00:00', 200.00, 40);


CREATE TABLE Pasajes (
    idPasaje INT AUTO_INCREMENT PRIMARY KEY,
    idViaje INT NOT NULL,
    idUsuario INT NOT NULL,
    numeroAsiento INT NOT NULL,
    fechaCompra DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(20) NOT NULL DEFAULT 'Confirmado',
    FOREIGN KEY (idViaje) REFERENCES Viajes(idViaje),
    FOREIGN KEY (idUsuario) REFERENCES Usuarios(id)
);

DELIMITER //
create PROCEDURE reservarViaje(
    IN id_viaje INT,
    IN id_usuario INT,
    IN numero_asiento INT
)
BEGIN
    -- Verificar si el número de asiento ya está reservado por otro usuario en este viaje
    DECLARE num_asiento_reservado INT;

    -- Consultar si el número de asiento está ocupado por otro usuario en el viaje
    SELECT COUNT(*) INTO num_asiento_reservado
    FROM Pasajes
    WHERE idViaje = id_viaje
    AND numeroAsiento = numero_asiento
     AND estado = 'Confirmado';

    -- Si el asiento está reservado, lanzar un error
    IF num_asiento_reservado > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El número de asiento ya está reservado';
    ELSE
        -- Si no está reservado, insertar la reserva
        INSERT INTO Pasajes (idViaje, idUsuario, numeroAsiento)
        VALUES (id_viaje, id_usuario, numero_asiento);
    END IF;
END //
DELIMITER ;

#CALL registrarViaje(1, 2, 5);
