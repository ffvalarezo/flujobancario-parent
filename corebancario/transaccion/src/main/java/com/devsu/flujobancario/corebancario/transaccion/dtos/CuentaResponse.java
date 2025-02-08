package com.devsu.flujobancario.corebancario.transaccion.dtos;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "CuentaResponse", description = "Modelo representativo de cuenta en la base de datos")
public class CuentaResponse {

	private Long id;
	private String numeroCuenta;
	private String tipoCuenta;
	private BigDecimal saldoInicial;
	private boolean estado;
	private Long clienteId;

}
