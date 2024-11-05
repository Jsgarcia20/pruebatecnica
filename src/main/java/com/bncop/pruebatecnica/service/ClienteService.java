package com.bncop.pruebatecnica.service;

import com.bncop.pruebatecnica.model.Cliente;
import com.bncop.pruebatecnica.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> update(Long id, Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setid(id);
            return Optional.of(clienteRepository.save(cliente));
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
