package com.devsu.flujobancario.corebancario.cliente.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false, unique = true)
	private String identificacion;

	@Column(nullable = false)
	private String genero;

	@Column(nullable = false)
	private Integer edad;

	@Column(nullable = false)
	private String direccion;

	@Column(nullable = false)
	private String telefono;

}
