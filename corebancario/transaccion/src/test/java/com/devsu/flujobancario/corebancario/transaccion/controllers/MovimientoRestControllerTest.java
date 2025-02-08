package com.devsu.flujobancario.corebancario.transaccion.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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

import com.devsu.flujobancario.corebancario.transaccion.dtos.MovimientoResponse;
import com.devsu.flujobancario.corebancario.transaccion.listeners.CuentaEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class MovimientoRestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockitoBean
	private RabbitTemplate rabbitTemplate;
	@MockitoBean
	private CuentaEvent cuentaListener;

	@Test
	void testRealizarMovimiento() throws Exception {
		MovimientoResponse movimientoDTO = new MovimientoResponse();
		movimientoDTO.setTipoMovimiento("DEBITO");
		movimientoDTO.setValor(new BigDecimal(-500.00));
		movimientoDTO.setSaldo(new BigDecimal(1500.00));
		movimientoDTO.setNumeroCuenta("447875800");
		movimientoDTO.setFecha("2024-01-31T15:45:30");
		ObjectMapper objectMapper = new ObjectMapper();
		String movimientoJson = objectMapper.writeValueAsString(movimientoDTO);

		String cuentaJson = "{\"numeroCuenta\":\"447875800\",\"tipoCuenta\":"
				+ "\"AHORRO\",\"saldoInicial\":2000.00,\"estado\":true,\"clienteId\":1}";

		mockMvc.perform(
				MockMvcRequestBuilders.post("/cuentas").contentType(MediaType.APPLICATION_JSON).content(cuentaJson))
				.andExpect(MockMvcResultMatchers.status().isCreated());

		mockMvc.perform(post("/movimientos").contentType(MediaType.APPLICATION_JSON).content(movimientoJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.tipoMovimiento").value("DEBITO"));
	}
}
