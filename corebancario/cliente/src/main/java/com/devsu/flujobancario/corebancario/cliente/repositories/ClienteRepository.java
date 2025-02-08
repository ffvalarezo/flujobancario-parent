package com.devsu.flujobancario.corebancario.cliente.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.flujobancario.corebancario.cliente.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByIdentificacion(String indentificacion);

}
