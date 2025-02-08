package com.devsu.flujobancario.corebancario.transaccion.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteRequest {

	private String nombre;
	private String identificacion;

}