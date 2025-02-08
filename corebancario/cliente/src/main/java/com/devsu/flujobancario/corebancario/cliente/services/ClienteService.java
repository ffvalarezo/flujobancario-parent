package com.devsu.flujobancario.corebancario.cliente.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devsu.flujobancario.corebancario.cliente.entities.Cliente;
import com.devsu.flujobancario.corebancario.cliente.repositories.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findById(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
	}

	public Cliente save(Cliente cliente) {
		if (cliente.getId() == 0 && clienteRepository.findByIdentificacion(cliente.getIdentificacion()).isPresent()) {
			throw new IllegalArgumentException(
					"El cliente con la identificacion " + cliente.getIdentificacion() + ", ya existe.");
		}

		Cliente savedCustomer = clienteRepository.save(cliente);
		return savedCustomer;
	}

	public void delete(Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
		}
		clienteRepository.deleteById(id);
	}

}
