package com.devsu.flujobancario.corebancario.transaccion.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.devsu.flujobancario.corebancario.transaccion.common.enums.TipoCuenta;
import com.devsu.flujobancario.corebancario.transaccion.entities.Cuenta;
import com.devsu.flujobancario.corebancario.transaccion.repositories.CuentaRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CuentaServiceTest {

	@Mock
	private CuentaRepository cuentaRepository;

	@InjectMocks
	private CuentaService cuentaService;

	private Cuenta cuenta;

	@BeforeEach
	void setUp() {
		cuenta = new Cuenta();
		cuenta.setId(1L);
		cuenta.setNumeroCuenta("478758");
		cuenta.setTipoCuenta(TipoCuenta.AHORRO);
		cuenta.setSaldoInicial(new BigDecimal(2000.00));
		cuenta.setEstado(true);
	}

	@Test
	void testCrearCuenta() {
		when(cuentaRepository.save(any(Cuenta.class))).thenReturn(cuenta);

		Cuenta cuentaDTO = new Cuenta();
		cuentaDTO.setNumeroCuenta("4787589");
		cuentaDTO.setTipoCuenta(TipoCuenta.AHORRO);
		cuentaDTO.setSaldoInicial(new BigDecimal(2000.00));
		cuentaDTO.setEstado(true);

		Cuenta resultado = cuentaService.save(cuentaDTO);
		assertNotNull(resultado);
		assertEquals("478758", resultado.getNumeroCuenta());
	}

	@Test
	void testListarCuentas() {
		when(cuentaRepository.findAll()).thenReturn(Arrays.asList(cuenta));
		List<Cuenta> resultado = cuentaService.findAll();
		assertFalse(resultado.isEmpty());
		assertEquals(1, resultado.size());
		assertEquals("478758", resultado.get(0).getNumeroCuenta());
	}
}