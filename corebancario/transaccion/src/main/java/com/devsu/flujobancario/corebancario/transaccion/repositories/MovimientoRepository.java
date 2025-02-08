package com.devsu.flujobancario.corebancario.transaccion.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devsu.flujobancario.corebancario.transaccion.entities.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

	@Query("SELECT m FROM Movimiento m WHERE m.cuenta.numeroCuenta = :numeroCuenta ORDER BY m.fecha DESC")
	List<Movimiento> findByNumeroCuenta(@Param("numeroCuenta") String numeroCuenta);

	@Query("SELECT m  FROM Movimiento m WHERE m.cuenta.numeroCuenta = :numeroCuenta and m.fecha BETWEEN :fechaInicio AND :fechaFin")
	List<Movimiento> findByAccountIdAndDatyBetween(@Param("numeroCuenta") String numeroCuenta,
			@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);
}