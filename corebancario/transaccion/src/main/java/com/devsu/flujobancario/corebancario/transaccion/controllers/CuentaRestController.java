package com.devsu.flujobancario.corebancario.transaccion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.flujobancario.corebancario.transaccion.common.CuentaRequestMapper;
import com.devsu.flujobancario.corebancario.transaccion.common.CuentaResponseMapper;
import com.devsu.flujobancario.corebancario.transaccion.dtos.CuentaRequest;
import com.devsu.flujobancario.corebancario.transaccion.dtos.CuentaResponse;
import com.devsu.flujobancario.corebancario.transaccion.entities.Cuenta;
import com.devsu.flujobancario.corebancario.transaccion.services.CuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaRestController {

	@Autowired
	CuentaService cuentaService;
	@Autowired
	CuentaRequestMapper cuentaRequestMapper;
	@Autowired
	CuentaResponseMapper cuentaResponseMapper;

	@GetMapping()
	public ResponseEntity<List<CuentaResponse>> list() {
		List<Cuenta> findAll = cuentaService.findAll();
		if (findAll.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(cuentaResponseMapper.CuentaListToCuentaResposeList(findAll));
		}
	}

	@GetMapping("/{numeroCuenta}")
	public ResponseEntity<?> findByNumeroCuenta(@PathVariable(name = "numeroCuenta") String numeroCuenta) {
		return ResponseEntity
				.ok(cuentaResponseMapper.CuentaToCuentaResponse(cuentaService.findByNumeroCuenta(numeroCuenta)));
	}

	@GetMapping("/cliente/{clienteId}")
	public ResponseEntity<?> findByClienteId(@PathVariable(name = "clienteId") Long clienteId) {
		return ResponseEntity
				.ok(cuentaResponseMapper.CuentaListToCuentaResposeList(cuentaService.findByClienteId(clienteId)));
	}

	@PostMapping
	public ResponseEntity<?> crearCuenta(@RequestBody CuentaRequest input) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cuentaResponseMapper
				.CuentaToCuentaResponse(cuentaService.save(cuentaRequestMapper.CuentaResquestToCuenta(input))));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarCuenta(@PathVariable(name = "id") Long id, @RequestBody CuentaRequest input) {
		Cuenta existeCuenta = cuentaService.findById(id);
		Cuenta source = cuentaRequestMapper.CuentaResquestToCuenta(input);
		source.setId(existeCuenta.getId());
		return ResponseEntity.ok(cuentaResponseMapper.CuentaToCuentaResponse(cuentaService.save(source)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> borrarCuenta(@PathVariable(name = "id") Long id) {
		cuentaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
