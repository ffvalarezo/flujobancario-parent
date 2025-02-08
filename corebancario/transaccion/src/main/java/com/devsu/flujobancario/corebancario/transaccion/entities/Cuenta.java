package com.devsu.flujobancario.corebancario.transaccion.entities;

import java.math.BigDecimal;

import com.devsu.flujobancario.corebancario.transaccion.common.enums.TipoCuenta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String numeroCuenta;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoCuenta tipoCuenta;

	@Column(nullable = false)
	private BigDecimal saldoInicial;

	@Column(nullable = false)
	private boolean estado;

	@Column(nullable = false)
	private Long clienteId;
}
