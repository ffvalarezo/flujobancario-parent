package com.devsu.flujobancario.corebancario.cliente.dtos;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "ClienteRequest", description = "Modelo representativo del cliente en la base de datos")

public class ClienteRequest {

	@NotBlank(message = "El nombre es requerido")
	private String nombre;
	@NotBlank(message = "El genero es requerido")
	private String genero;
	@NotNull(message = "La edad es requerido")
	@Min(value = 18, message = "Edad minima de 18")
	@Max(value = 100, message = "No se puede registrar mayor a 100")
	private int edad;
	@NotBlank(message = "Identificacion requerida")
	@Pattern(regexp = "\\d{10}", message = "La identificacion debe tener 10 digitos")
	private String identificacion;
	@NotBlank(message = "La direccion requerida")
	private String direccion;
	@NotBlank(message = "Telefono requerido")
	private String telefono;
	@NotBlank(message = "Clave requerido")
	private String contrasena;
	@NotNull(message = "El estado es mandatorio")
	private boolean estado;
	@NotBlank(message = "El numero de cuenta es requerido")
	private String numeroCuenta;
	@NotBlank(message = "El tipo de cuenta es requerido: AHORRO / CORRIENTE")
	private String tipoCuenta;
	private BigDecimal saldoInicial;

}