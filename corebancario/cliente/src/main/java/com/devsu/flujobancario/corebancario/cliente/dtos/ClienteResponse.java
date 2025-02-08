package com.devsu.flujobancario.corebancario.cliente.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "ClienteResponse", description = "Modelo representativo del cliente en la base de datos")
public class ClienteResponse {

	private long id;
	private String nombre;
	private String genero;
	private int edad;
	private String identificacion;
	private String direccion;
	private String telefono;
	private String contrasena;
	private boolean estado;
}