package com.devsu.flujobancario.corebancario.cliente.controllers;

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

import com.devsu.flujobancario.corebancario.cliente.common.ClienteRequestMapper;
import com.devsu.flujobancario.corebancario.cliente.common.ClienteResponseMapper;
import com.devsu.flujobancario.corebancario.cliente.dtos.ClienteRequest;
import com.devsu.flujobancario.corebancario.cliente.dtos.ClienteResponse;
import com.devsu.flujobancario.corebancario.cliente.dtos.CuentaRequest;
import com.devsu.flujobancario.corebancario.cliente.entities.Cliente;
import com.devsu.flujobancario.corebancario.cliente.publisers.ClienteEvent;
import com.devsu.flujobancario.corebancario.cliente.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

	@Autowired
	ClienteService clienteService;
	@Autowired
	ClienteRequestMapper clienteRequestMapper;
	@Autowired
	ClienteResponseMapper clienteResponseMapper;
	@Autowired
	ClienteEvent clienteEvent;

	@GetMapping()
	public ResponseEntity<List<ClienteResponse>> list() {
		List<Cliente> findAll = clienteService.findAll();
		if (findAll.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(clienteResponseMapper.ClienteListToCLienteResposeList(findAll));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getClienteById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(clienteResponseMapper.ClienteToClienteResponse(clienteService.findById(id)));
	}

	@PostMapping
	public ResponseEntity<?> crearCliente(@RequestBody ClienteRequest input) {
		ClienteResponse clienteGuardado = clienteResponseMapper
				.ClienteToClienteResponse(clienteService.save(clienteRequestMapper.ClienteResquestToCliente(input)));
		if (input.getNumeroCuenta() != null) {
			CuentaRequest newCuenta = new CuentaRequest();
			newCuenta.setNumeroCuenta(input.getNumeroCuenta());
			newCuenta.setTipoCuenta(input.getTipoCuenta());
			newCuenta.setSaldoInicial(input.getSaldoInicial());
			newCuenta.setClienteId(clienteGuardado.getId());
			clienteEvent.publishCreateCustomerEvent(newCuenta);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarCliente(@PathVariable(name = "id") Long id, @RequestBody ClienteRequest input) {
		Cliente existeCliente = clienteService.findById(id);
		Cliente source = clienteRequestMapper.ClienteResquestToCliente(input);
		source.setId(existeCliente.getId());
		return ResponseEntity.ok(clienteResponseMapper.ClienteToClienteResponse(clienteService.save(source)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> borrarCliente(@PathVariable(name = "id") Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
