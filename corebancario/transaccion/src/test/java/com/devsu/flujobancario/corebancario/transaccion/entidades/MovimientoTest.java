package com.devsu.flujobancario.corebancario.transaccion.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.devsu.flujobancario.corebancario.transaccion.common.enums.TipoMovimiento;
import com.devsu.flujobancario.corebancario.transaccion.entities.Cuenta;
import com.devsu.flujobancario.corebancario.transaccion.entities.Movimiento;

public class MovimientoTest {

	@Test
	public void testMovimientoCreation() {
		Cuenta cuenta = new Cuenta();
		cuenta.setId(1L);

		Movimiento movimiento = new Movimiento();
		movimiento.setFecha(LocalDateTime.now());
		movimiento.setTipoMovimiento(TipoMovimiento.DEBITO);
		movimiento.setValor(new BigDecimal(-500.00));
		movimiento.setSaldo(new BigDecimal(1500.00));
		movimiento.setCuenta(cuenta);

		assertNotNull(movimiento);
		assertEquals(TipoMovimiento.DEBITO, movimiento.getTipoMovimiento());
		assertEquals(new BigDecimal(-500.00).compareTo(movimiento.getValor()), 0);
		assertEquals(new BigDecimal(1500.00).compareTo(movimiento.getSaldo()), 0);
		assertNotNull(movimiento.getCuenta());
		assertEquals(1L, movimiento.getCuenta().getId());
	}

}
