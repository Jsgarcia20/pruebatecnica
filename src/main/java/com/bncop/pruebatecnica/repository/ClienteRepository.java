package com.bncop.pruebatecnica.repository;

import com.bncop.pruebatecnica.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Aquí puedes definir métodos personalizados si es necesario
}
