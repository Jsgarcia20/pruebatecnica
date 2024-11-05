package com.bncop.pruebatecnica;

import com.bncop.pruebatecnica.controller.MovimientoController;
import com.bncop.pruebatecnica.exception.SaldoInsuficienteException;
import com.bncop.pruebatecnica.model.Movimientos;
import com.bncop.pruebatecnica.service.MovimientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class MovimientoControllerTest {

    @InjectMocks
    private MovimientoController movimientoController;

    @Mock
    private MovimientoService movimientoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearMovimiento() {
        Movimientos movimiento = new Movimientos();
        when(movimientoService.registrarMovimiento(any(Movimientos.class))).thenReturn(movimiento);

        ResponseEntity<Movimientos> response = movimientoController.crearMovimiento(movimiento);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(movimiento, response.getBody());
    }

    @Test
    public void testCrearMovimientoConSaldoInsuficiente() {
        Movimientos movimiento = new Movimientos(); 
        when(movimientoService.registrarMovimiento(any(Movimientos.class))).thenThrow(new SaldoInsuficienteException("Saldo insuficiente"));

        ResponseEntity<Movimientos> response = movimientoController.crearMovimiento(movimiento);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testObtenerMovimientos() {
        Movimientos movimiento1 = new Movimientos();
        Movimientos movimiento2 = new Movimientos();
        List<Movimientos> movimientos = Arrays.asList(movimiento1, movimiento2);
        when(movimientoService.findAll()).thenReturn(movimientos);

        ResponseEntity<List<Movimientos>> response = movimientoController.obtenerMovimientos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movimientos, response.getBody());
    }

    @Test
    public void testObtenerMovimientoPorId() {
        Long id = 1L;
        Movimientos movimiento = new Movimientos();
        when(movimientoService.findById(id)).thenReturn(Optional.of(movimiento));

        ResponseEntity<Movimientos> response = movimientoController.obtenerMovimientoPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movimiento, response.getBody());
    }

    @Test
    public void testEliminarMovimiento() {
        Long id = 1L;
        ResponseEntity<Void> response = movimientoController.eliminarMovimiento(id);

        verify(movimientoService).deleteById(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testActualizarMovimiento() {
        Long id = 1L;
        Movimientos movimiento = new Movimientos(); 
        when(movimientoService.update(eq(id), any(Movimientos.class))).thenReturn(Optional.of(movimiento));

        ResponseEntity<Movimientos> response = movimientoController.actualizarMovimiento(id, movimiento);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movimiento, response.getBody());
    }

    @SpringBootTest
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public class PruebatecnicaApplicationTests {
    }
}
