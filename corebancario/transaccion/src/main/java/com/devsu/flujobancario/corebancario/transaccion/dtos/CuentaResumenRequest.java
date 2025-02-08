package com.devsu.flujobancario.corebancario.transaccion.dtos;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuentaResumenRequest {
	
	private String numeroCuenta;
	private String tipoCuenta;
	private BigDecimal saldoActual;
	private BigDecimal totalCreditos;
	private BigDecimal totalDebitos;
	private List<MovimientoResponse> movimientos;
	
}
