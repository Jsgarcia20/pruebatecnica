package com.bncop.pruebatecnica.controller;

import com.bncop.pruebatecnica.exception.SaldoInsuficienteException;
import com.bncop.pruebatecnica.model.Movimientos;
import com.bncop.pruebatecnica.service.MovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;


    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<Movimientos> crearMovimiento(@RequestBody Movimientos movimiento) {
        try {
            Movimientos nuevoMovimiento = movimientoService.registrarMovimiento(movimiento);
            return new ResponseEntity<>(nuevoMovimiento, HttpStatus.CREATED);
        } catch (SaldoInsuficienteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Movimientos>> obtenerMovimientos() {
        List<Movimientos> movimientos = movimientoService.findAll();
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimientos> obtenerMovimientoPorId(@PathVariable Long id) {
        Optional<Movimientos> movimiento = movimientoService.findById(id);
        return movimiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Long id) {
        movimientoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimientos> actualizarMovimiento(@PathVariable Long id, @RequestBody Movimientos movimiento) {
        Optional<Movimientos> movimientoActualizado = movimientoService.update(id, movimiento);
        return movimientoActualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
