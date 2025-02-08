package com.devsu.flujobancario.corebancario.transaccion.dtos;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "MovimientoRequest", description = "Modelo representativo de movimiento en la base de datos")
public class MovimientoRequest {

	@NotBlank(message = "El tipo de movimiento es requerido, ejm: CREDITO / DEBITO")
	private String tipoMovimiento;
	@NotNull(message = "El valor es mandatorio")
	private BigDecimal valor;
	private BigDecimal saldo;
	@NotBlank(message = "El numero de cuenta es mandatorio")
	private String numeroCuenta;
	private String fecha;

}
