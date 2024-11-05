package com.bncop.pruebatecnica.exception;

public class CuentaNotFoundException extends RuntimeException {
    public CuentaNotFoundException(Long id) {
        super("No se pudo encontrar la cuenta con ID: " + id);
    }
}
