package com.bncop.pruebatecnica.repository;

import com.bncop.pruebatecnica.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    // Métodos personalizados si son necesarios
}
