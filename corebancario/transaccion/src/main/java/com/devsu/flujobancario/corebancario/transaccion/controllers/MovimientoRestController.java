package com.devsu.flujobancario.corebancario.transaccion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.flujobancario.corebancario.transaccion.common.MovimientoRequestMapper;
import com.devsu.flujobancario.corebancario.transaccion.common.MovimientoResponseMapper;
import com.devsu.flujobancario.corebancario.transaccion.dtos.MovimientoRequest;
import com.devsu.flujobancario.corebancario.transaccion.dtos.MovimientoResponse;
import com.devsu.flujobancario.corebancario.transaccion.entities.Movimiento;
import com.devsu.flujobancario.corebancario.transaccion.services.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoRestController {

	@Autowired
	MovimientoService movimientoService;
	@Autowired
	MovimientoRequestMapper movimientoRequestMapper;
	@Autowired
	MovimientoResponseMapper movimientoResponseMapper;

	@GetMapping()
	public ResponseEntity<List<MovimientoResponse>> list() {
		List<Movimiento> findAll = movimientoService.findAll();
		if (findAll.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(movimientoResponseMapper.MovimientoListToMovimientoResposeList(findAll));
		}
	}

	@PostMapping
	public ResponseEntity<?> crearMovimiento(@RequestBody MovimientoRequest input) {
		return ResponseEntity.ok(movimientoResponseMapper.MovimientoToMovimientoResponse(
				movimientoService.realizarMovimiento(movimientoRequestMapper.MovimientoResquestToMovimiento(input))));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarMovimiento(@PathVariable(name = "id") Long id,
			@RequestBody MovimientoRequest input) {
		Movimiento existeMovimiento = movimientoService.findById(id);
		Movimiento source = movimientoRequestMapper.MovimientoResquestToMovimiento(input);
		source.setId(existeMovimiento.getId());
		return ResponseEntity
				.ok(movimientoResponseMapper.MovimientoToMovimientoResponse(movimientoService.save(source)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> borrarMovimiento(@PathVariable(name = "id") Long id) {
		movimientoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/cuenta/{numeroCuenta}")
	public ResponseEntity<List<MovimientoResponse>> obtenerMovimientosPorNumeroCuenta(
			@PathVariable(name = "numeroCuenta") String numeroCuenta) {
		return ResponseEntity.ok(movimientoResponseMapper.MovimientoListToMovimientoResposeList(
				movimientoService.obtenerMovimientosPorNumeroCuenta(numeroCuenta)));
	}

}
