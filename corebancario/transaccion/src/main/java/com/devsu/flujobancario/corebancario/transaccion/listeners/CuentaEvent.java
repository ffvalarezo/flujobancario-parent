package com.devsu.flujobancario.corebancario.transaccion.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.devsu.flujobancario.corebancario.transaccion.RabbitMQConfig;
import com.devsu.flujobancario.corebancario.transaccion.common.enums.TipoCuenta;
import com.devsu.flujobancario.corebancario.transaccion.dtos.CuentaRequest;
import com.devsu.flujobancario.corebancario.transaccion.entities.Cuenta;
import com.devsu.flujobancario.corebancario.transaccion.services.CuentaService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CuentaEvent {

	private final CuentaService cuentaService;

	public CuentaEvent(CuentaService cuentaService) {
		this.cuentaService = cuentaService;
	}

	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void handleCreateAccountEvent(CuentaRequest clientId) {
		log.info("Evento recibido: Crear cuenta para Cliente con ID " + clientId.getNumeroCuenta());
		Cuenta nuevaCuenta = new Cuenta();
		nuevaCuenta.setNumeroCuenta(clientId.getNumeroCuenta());
		nuevaCuenta.setSaldoInicial(clientId.getSaldoInicial());
		nuevaCuenta.setEstado(true);
		nuevaCuenta.setTipoCuenta(TipoCuenta.valueOf(clientId.getTipoCuenta().toUpperCase()));
		nuevaCuenta.setClienteId(clientId.getClienteId());
		cuentaService.save(nuevaCuenta);
	}
}
