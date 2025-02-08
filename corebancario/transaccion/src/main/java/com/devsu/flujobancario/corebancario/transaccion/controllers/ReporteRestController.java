package com.devsu.flujobancario.corebancario.transaccion.controllers;

import java.net.UnknownHostException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.flujobancario.corebancario.transaccion.dtos.ReporteEstadoCuentaResponse;
import com.devsu.flujobancario.corebancario.transaccion.exceptions.BussinesRuleException;
import com.devsu.flujobancario.corebancario.transaccion.services.ReporteService;

@RestController
@RequestMapping("/reportes")
public class ReporteRestController {

	@Autowired
	ReporteService reporteService;

	@GetMapping("/{clienteId}/{fechaInicio}/{fechaFin}")
	public ReporteEstadoCuentaResponse obtenerReporte(@RequestParam(name = "clienteId") Long clienteId,
			@RequestParam(name = "fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
			@RequestParam(name = "fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin)
			throws UnknownHostException, BussinesRuleException {
		return reporteService.findByNumeroCuentaAndFechaBetween(clienteId, fechaInicio, fechaFin);
	}

	@GetMapping("/pdf/{clienteId}/{fechaInicio}/{fechaFin}")
	public String obtenerReportePdfBase64(@RequestParam(name = "clienteId") Long clienteId,
			@RequestParam(name = "fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
			@RequestParam(name = "fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin)
			throws UnknownHostException, BussinesRuleException {
		ReporteEstadoCuentaResponse reporte = reporteService.findByNumeroCuentaAndFechaBetween(clienteId, fechaInicio,
				fechaFin);
		return reporteService.generarPdfBase64(reporte);
	}

}
