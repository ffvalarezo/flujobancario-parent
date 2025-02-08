package com.devsu.flujobancario.corebancario.transaccion.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.devsu.flujobancario.corebancario.transaccion.listeners.CuentaEvent;

@SpringBootTest
@AutoConfigureMockMvc
public class CuentaRestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockitoBean
	private RabbitTemplate rabbitTemplate;
	@MockitoBean
	private CuentaEvent cuentaListener;

	@Test
	void testCrearCuentaEndpoint() throws Exception {
		String cuentaJson = "{\"numeroCuenta\":\"4478758\",\"tipoCuenta\":"
				+ "\"AHORRO\",\"saldoInicial\":2000.00,\"estado\":true,\"clienteId\":1}";

		mockMvc.perform(
				MockMvcRequestBuilders.post("/cuentas").contentType(MediaType.APPLICATION_JSON).content(cuentaJson))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.numeroCuenta").value("4478758"));
	}
}
