package com.devsu.flujobancario.corebancario.transaccion.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "CuentaRequest", description = "Modelo representativo de cuenta en la base de datos")
public class CuentaRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotBlank(message = "El numero de cuenta es requerido")
	private String numeroCuenta;
	@NotBlank(message = "El tipo de cuenta es requerido: AHORRO / CORRIENTE")
	private String tipoCuenta;
	private BigDecimal saldoInicial;
	@NotNull(message = "El estado es mandatorio")
	private boolean estado;
	@NotNull(message = "El cliente es mandatorio")
	private Long clienteId;

}
