package com.devsu.flujobancario.corebancario.transaccion.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devsu.flujobancario.corebancario.transaccion.entities.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	Optional<Cuenta> findByNumeroCuenta(@Param("numeroCuenta") String numeroCuenta);

	List<Cuenta> findByClienteId(@Param("clienteId") Long clienteId);

}