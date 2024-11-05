-- Usamos la base de datos pruebatecnica previamente creada
USE pruebatecnica;
-- Crear tabla Persona
CREATE TABLE persona (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- Clave primaria
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(50),
    edad INT,
    identificacion VARCHAR(50) UNIQUE NOT NULL,  -- Columna única
    direccion VARCHAR(255),
    telefono VARCHAR(50)
);

-- Crear tabla Cliente
CREATE TABLE cliente (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- Clave primaria
    persona_id BIGINT NOT NULL,  -- Clave foránea que referencia a Persona
    contrasena VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES persona(id)  -- Relación con Persona
);

-- Crear tabla Cuenta
CREATE TABLE cuenta (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- Clave primaria para Cuenta
    cliente_id BIGINT NOT NULL,  -- Clave foránea que referencia a Cliente
    numeroCuenta VARCHAR(50) NOT NULL,
    tipo VARCHAR(50),  -- Ahorro, Corriente, etc.
    saldoInicial DOUBLE,
    estado BOOLEAN,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)  -- Relación con Cliente
);

-- Crear tabla Movimientos
CREATE TABLE movimientos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- Clave primaria
    cuenta_id BIGINT NOT NULL,  -- Clave foránea que referencia a Cuenta
    fecha TIMESTAMP NOT NULL,
    tipoMovimiento VARCHAR(50),  -- Tipo de movimiento
    valor DOUBLE,
    saldo DOUBLE,
    FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)  -- Relación con Cuenta
);
