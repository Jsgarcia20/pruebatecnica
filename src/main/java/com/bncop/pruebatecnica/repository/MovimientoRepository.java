package com.bncop.pruebatecnica.repository;

import com.bncop.pruebatecnica.model.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimientos, Long> {
    // Métodos personalizados si son necesarios
}
