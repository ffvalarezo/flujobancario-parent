package com.devsu.flujobancario.corebancario.cliente.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class CuentaRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String numeroCuenta;
	private String tipoCuenta;
	private BigDecimal saldoInicial;
	private long clienteId;

}
