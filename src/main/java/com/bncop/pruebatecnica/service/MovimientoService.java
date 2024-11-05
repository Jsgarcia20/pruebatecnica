package com.bncop.pruebatecnica.service;

import com.bncop.pruebatecnica.exception.SaldoInsuficienteException;
import com.bncop.pruebatecnica.model.Cuenta;
import com.bncop.pruebatecnica.model.Movimientos;
import com.bncop.pruebatecnica.repository.CuentaRepository;
import com.bncop.pruebatecnica.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;


    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public Movimientos registrarMovimiento(Movimientos movimiento) throws SaldoInsuficienteException {
        // Lógica para verificar saldo, crear el movimiento, etc.
        // Asegúrate de que estás manejando correctamente el objeto cuenta.
        if (movimiento.getCuenta() == null || movimiento.getCuenta().getid() == null) {
            throw new IllegalArgumentException("Cuenta no puede ser nula");
        }
        
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getid())
            .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        return movimientoRepository.save(movimiento);
    }
  
    public List<Movimientos> findAll() {
        return movimientoRepository.findAll();
    }

    public Optional<Movimientos> findById(Long id) {
        return movimientoRepository.findById(id);
    }

    public void deleteById(Long id) {
        movimientoRepository.deleteById(id);
    }

    public Optional<Movimientos> update(Long id, Movimientos movimiento) {
        if (movimientoRepository.existsById(id)) {
            movimiento.setId(id);
            return Optional.of(movimientoRepository.save(movimiento));
        }
        return Optional.empty();
    }
}
