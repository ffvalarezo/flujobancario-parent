package com.devsu.flujobancario.corebancario.transaccion;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.devsu.flujobancario.corebancario.transaccion.listeners.CuentaEvent;

@SpringBootTest
class TransaccionApplicationTests {

	@MockitoBean
	private RabbitTemplate rabbitTemplate;
	@MockitoBean
	private CuentaEvent cuentaListener;

	@Test
	void contextLoads() {
	}

}
