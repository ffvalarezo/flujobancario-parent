package com.devsu.flujobancario.corebancario.transaccion.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devsu.flujobancario.corebancario.transaccion.entities.Cuenta;
import com.devsu.flujobancario.corebancario.transaccion.repositories.CuentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuentaService {

	private final CuentaRepository cuentaRepository;

	public Cuenta findById(Long id) {
		return cuentaRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta no encontrada"));
	}

	public List<Cuenta> findAll() {
		return cuentaRepository.findAll();
	}

	public Cuenta findByNumeroCuenta(String numeroCuenta) {
		return cuentaRepository.findByNumeroCuenta(numeroCuenta)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta no encontrada"));
	}

	public List<Cuenta> findByClienteId(Long clienteId) {
		return cuentaRepository.findByClienteId(clienteId);
	}

	public Cuenta save(Cuenta cuenta) {
		return cuentaRepository.save(cuenta);
	}

	public void delete(Long id) {
		if (!cuentaRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cuenta no encontrada");
		}
		cuentaRepository.deleteById(id);
	}

}
