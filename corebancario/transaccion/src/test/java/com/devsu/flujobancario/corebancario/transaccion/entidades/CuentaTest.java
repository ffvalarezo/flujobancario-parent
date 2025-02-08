package com.devsu.flujobancario.corebancario.transaccion.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.devsu.flujobancario.corebancario.transaccion.common.enums.TipoCuenta;
import com.devsu.flujobancario.corebancario.transaccion.entities.Cuenta;

public class CuentaTest {
	@Test
	public void testCuentaCreation() {
		Cuenta cuenta = new Cuenta();
		cuenta.setNumeroCuenta("478758");
		cuenta.setTipoCuenta(TipoCuenta.AHORRO);
		cuenta.setSaldoInicial(new BigDecimal(2000.00));
		cuenta.setEstado(true);

		assertNotNull(cuenta);
		assertEquals("478758", cuenta.getNumeroCuenta());
		assertEquals(TipoCuenta.AHORRO, cuenta.getTipoCuenta());
		assertEquals(new BigDecimal(2000.00).compareTo(cuenta.getSaldoInicial()), 0);
		assertTrue(cuenta.isEstado());
	}

	@Test
	void testCuentaModificacion() {
		Cuenta cuenta = new Cuenta();
		cuenta.setNumeroCuenta("478758");
		cuenta.setTipoCuenta(TipoCuenta.CORRIENTE);
		cuenta.setSaldoInicial(new BigDecimal(56000.00));
		cuenta.setEstado(true);
		assertNotNull(cuenta);
		assertEquals("478758", cuenta.getNumeroCuenta());
		assertEquals(TipoCuenta.CORRIENTE, cuenta.getTipoCuenta());
		assertEquals(new BigDecimal(56000.00).compareTo(cuenta.getSaldoInicial()), 0);
		assertTrue(cuenta.isEstado());
	}
}
