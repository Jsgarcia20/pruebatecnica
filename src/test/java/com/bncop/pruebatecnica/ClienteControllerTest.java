package com.bncop.pruebatecnica;

import com.bncop.pruebatecnica.controller.ClienteController;

import com.bncop.pruebatecnica.model.Cliente;
import com.bncop.pruebatecnica.service.ClienteService;
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

public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearCliente() {
        Cliente cliente = new Cliente(); // inicializa según sea necesario
        when(clienteService.save(any(Cliente.class))).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.crearCliente(cliente);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    public void testObtenerClientes() {
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
        when(clienteService.findAll()).thenReturn(clientes);

        ResponseEntity<List<Cliente>> response = clienteController.obtenerClientes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientes, response.getBody());
    }

    @Test
    public void testObtenerClientePorId() {
        Long id = 1L;
        Cliente cliente = new Cliente(); // inicializa según sea necesario
        when(clienteService.findById(id)).thenReturn(Optional.of(cliente));

        ResponseEntity<Cliente> response = clienteController.obtenerClientePorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    public void testActualizarCliente() {
        Long id = 1L;
        Cliente cliente = new Cliente(); // inicializa según sea necesario
        when(clienteService.update(eq(id), any(Cliente.class))).thenReturn(Optional.of(cliente));

        ResponseEntity<Cliente> response = clienteController.actualizarCliente(id, cliente);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    public void testEliminarCliente() {
        Long id = 1L;
        ResponseEntity<Void> response = clienteController.eliminarCliente(id);

        verify(clienteService).deleteById(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    @SpringBootTest
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public class PruebatecnicaApplicationTests {
    }

}
