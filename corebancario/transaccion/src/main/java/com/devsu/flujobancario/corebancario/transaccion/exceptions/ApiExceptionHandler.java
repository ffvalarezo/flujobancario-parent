package com.devsu.flujobancario.corebancario.transaccion.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.devsu.flujobancario.corebancario.transaccion.common.StandarizedApiExceptionResponse;


@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleUnknownHostException(Exception ex) {
		StandarizedApiExceptionResponse standarizedApiExceptionResponse = new StandarizedApiExceptionResponse(
				"Error tecnico", "Ingreso o salida de datos incorrecto", "1024", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standarizedApiExceptionResponse);
	}

	@ExceptionHandler(BussinesRuleException.class)
	public ResponseEntity<?> handleBussinesRuleException(BussinesRuleException ex) {
		StandarizedApiExceptionResponse standarizedApiExceptionResponse = new StandarizedApiExceptionResponse(
				"Error de negocio", "Error en la validacion", ex.getCode(), ex.getMessage());
		return ResponseEntity.status(ex.getHttpStatus()).body(standarizedApiExceptionResponse);
	}
}
