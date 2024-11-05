package com.bncop.pruebatecnica;

import com.bncop.pruebatecnica.controller.CuentaController;
import com.bncop.pruebatecnica.exception.CuentaNotFoundException;
import com.bncop.pruebatecnica.model.Cuenta;
import com.bncop.pruebatecnica.service.CuentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CuentaControllerTest {

    @InjectMocks
    private CuentaController cuentaController;

    @Mock
    private CuentaService cuentaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerCuentas() {
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        List<Cuenta> cuentas = Arrays.asList(cuenta1, cuenta2);
        when(cuentaService.findAll()).thenReturn(cuentas);

        ResponseEntity<List<Cuenta>> response = cuentaController.obtenerCuentas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cuentas, response.getBody());
    }

    @Test
    public void testCrearCuenta() {
        Cuenta cuenta = new Cuenta(); // inicializa según sea necesario
        when(cuentaService.save(any(Cuenta.class))).thenReturn(cuenta);

        ResponseEntity<Cuenta> response = cuentaController.crearCuenta(cuenta);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cuenta, response.getBody());
    }

    @Test
    public void testEliminarCuenta() {
        Long id = 1L;
        ResponseEntity<Void> response = cuentaController.eliminarCuenta(id);

        verify(cuentaService).delete(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testHandleCuentaNotFound() {
        Long id = 1L;  // Define un valor Long
        when(cuentaService.findById(any(Long.class))).thenThrow(new CuentaNotFoundException(id));
    
        ResponseEntity<String> response = cuentaController.handleCuentaNotFound(new CuentaNotFoundException(id));
    
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No se pudo encontrar la cuenta con ID: " + id, response.getBody());
    }
    
}
