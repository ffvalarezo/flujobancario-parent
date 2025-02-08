package com.devsu.flujobancario.corebancario.transaccion.dtos;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteEstadoCuentaResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String identificacion;
	private List<CuentaResumenRequest> cuentas;

}
