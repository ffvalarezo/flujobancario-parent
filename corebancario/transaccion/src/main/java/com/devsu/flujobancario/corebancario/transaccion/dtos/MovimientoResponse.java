package com.devsu.flujobancario.corebancario.transaccion.dtos;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(name = "MovimientoResponse", description = "Modelo representativo de movimiento en la base de datos")
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoResponse {

	private Long id;
	private String tipoMovimiento;
	private BigDecimal valor;
	private BigDecimal saldo;
	private String numeroCuenta;
	private String fecha;

}
