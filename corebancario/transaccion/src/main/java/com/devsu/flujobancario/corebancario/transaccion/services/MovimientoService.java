package com.devsu.flujobancario.corebancario.transaccion.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devsu.flujobancario.corebancario.transaccion.common.enums.TipoMovimiento;
import com.devsu.flujobancario.corebancario.transaccion.entities.Cuenta;
import com.devsu.flujobancario.corebancario.transaccion.entities.Movimiento;
import com.devsu.flujobancario.corebancario.transaccion.repositories.CuentaRepository;
import com.devsu.flujobancario.corebancario.transaccion.repositories.MovimientoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimientoService {

	private final MovimientoRepository movimientoRepository;
	private final CuentaRepository cuentaRepository;

	public List<Movimiento> findByAccountIdAndDatyBetween(String numeroCuenta, LocalDateTime fechaInicio,
			LocalDateTime fechaFin) {
		return movimientoRepository.findByAccountIdAndDatyBetween(numeroCuenta, fechaInicio, fechaFin);
	}

	public List<Movimiento> findByNumeroCuenta(String numeroCuenta) {
		return movimientoRepository.findByNumeroCuenta(numeroCuenta);
	}

	public Movimiento save(Movimiento movimiento) {
		return movimientoRepository.save(movimiento);
	}

	public Movimiento findById(Long id) {
		return movimientoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimiento no encontrado"));
	}

	public List<Movimiento> findAll() {
		return movimientoRepository.findAll();
	}

	public void delete(Long id) {
		if (!movimientoRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movimiento no encontrado");
		}
		movimientoRepository.deleteById(id);
	}

	public Movimiento realizarMovimiento(Movimiento movimiento) {
		Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimiento.getCuenta().getNumeroCuenta())
				.orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

		if (movimiento.getTipoMovimiento().equals(TipoMovimiento.DEBITO)
				&& movimiento.getValor().compareTo(BigDecimal.ZERO) >= 0) {
			throw new RuntimeException("Los valores de Débito deben ser negativos");
		}

		if (movimiento.getTipoMovimiento().equals(TipoMovimiento.CREDITO)
				&& movimiento.getValor().compareTo(BigDecimal.ZERO) <= 0) {
			throw new RuntimeException("Los valores de Crédito deben ser positivos");
		}

		if (movimiento.getTipoMovimiento().equals(TipoMovimiento.DEBITO)
				&& cuenta.getSaldoInicial().compareTo(movimiento.getValor()) < 0) {
			throw new RuntimeException("Saldo no disponible");
		}

		BigDecimal nuevoSaldo = (cuenta.getSaldoInicial() == null ? BigDecimal.ZERO : cuenta.getSaldoInicial())
				.add(movimiento.getValor() == null ? BigDecimal.ZERO : movimiento.getValor());

		cuenta.setSaldoInicial(nuevoSaldo);
		cuentaRepository.save(cuenta);
		Movimiento nuevoMovimiento = movimiento;
		nuevoMovimiento.setSaldo(nuevoSaldo);
		nuevoMovimiento.setCuenta(cuenta);
		return movimientoRepository.save(nuevoMovimiento);

	}

	public List<Movimiento> obtenerMovimientosPorNumeroCuenta(String numeroCuenta) {
		return movimientoRepository.findByNumeroCuenta(numeroCuenta);
	}

}
