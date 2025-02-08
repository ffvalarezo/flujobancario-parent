package com.devsu.flujobancario.corebancario.cliente.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente extends Persona {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;

	@Column(nullable = false)
	private String contrasena;

	@Column(nullable = false)
	private boolean estado;

	@Transient
	private String numeroCuenta;
	@Transient
	private String tipoCuenta;
	@Transient
	private BigDecimal saldoInicial;

}
