package com.bncop.pruebatecnica.service;

import com.bncop.pruebatecnica.exception.CuentaNotFoundException;
import com.bncop.pruebatecnica.model.Cuenta;
import com.bncop.pruebatecnica.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

    public Optional<Cuenta> findById(Long id) {
        return cuentaRepository.findById(id);
    }

    public Cuenta save(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public void delete(Long id) {
    if (!cuentaRepository.existsById(id)) {
        throw new CuentaNotFoundException(id);
    }
    cuentaRepository.deleteById(id);
}

    // Puedes agregar más métodos personalizados si es necesario
}
