package com.devsu.flujobancario.corebancario.cliente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.flujobancario.corebancario.cliente.entities.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
