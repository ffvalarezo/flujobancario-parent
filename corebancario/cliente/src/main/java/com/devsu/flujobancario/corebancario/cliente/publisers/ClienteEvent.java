package com.devsu.flujobancario.corebancario.cliente.publisers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.devsu.flujobancario.corebancario.cliente.RabbitMQConfig;
import com.devsu.flujobancario.corebancario.cliente.dtos.CuentaRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClienteEvent {

	private final RabbitTemplate rabbitTemplate;

	public ClienteEvent(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void publishCreateCustomerEvent(CuentaRequest customerId) {
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, customerId);
	}

}
