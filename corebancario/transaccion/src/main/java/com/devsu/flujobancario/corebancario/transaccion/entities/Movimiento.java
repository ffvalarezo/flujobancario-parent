package com.devsu.flujobancario.corebancario.transaccion.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.devsu.flujobancario.corebancario.transaccion.common.enums.TipoMovimiento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDateTime fecha;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoMovimiento tipoMovimiento;

	@Column(nullable = false)
	private BigDecimal valor;

	@Column(nullable = false)
	private BigDecimal saldo;

	@ManyToOne
	@JoinColumn(name = "cuenta_id", nullable = false)
	private Cuenta cuenta;

}
