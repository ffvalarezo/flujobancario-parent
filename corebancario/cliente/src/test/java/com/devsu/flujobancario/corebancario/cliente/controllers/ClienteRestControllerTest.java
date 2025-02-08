package com.devsu.flujobancario.corebancario.cliente.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.devsu.flujobancario.corebancario.cliente.entities.Cliente;
import com.devsu.flujobancario.corebancario.cliente.services.ClienteService;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClienteRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Mock
	private ClienteService clienteService;

	@Mock
	private RabbitTemplate rabbitTemplate;

	@InjectMocks
	private ClienteRestController clienteController;

	private Cliente clienteDTO;

	@BeforeEach
	void setUp() {
		clienteDTO = new Cliente();
		clienteDTO.setId(1l);
		clienteDTO.setNombre("Juan Perez");
		clienteDTO.setGenero("Masculino");
		clienteDTO.setEdad(30);
		clienteDTO.setIdentificacion("172001");
		clienteDTO.setDireccion("Av. Siempre Viva");
		clienteDTO.setTelefono("0987654321");
		clienteDTO.setContrasena("password123");
		clienteDTO.setEstado(true);
	}

	@Test
	public void testObtenerClientes() throws Exception {
		List<Cliente> clientes = Arrays.asList(clienteDTO);
		when(clienteService.findAll()).thenReturn(clientes);
		String clienteJson = "{\"id\": 1,\"nombre\": \"Juan Perez\",\"genero\": \"Masculino\","
				+ "\"edad\": 30,\"identificacion\": \"172001\",\"direccion\": \"Av. Siempre Viva\","
				+ "\"telefono\": \"0987654321\",\"contrasena\": \"password123\",\"estado\": true}";
		mockMvc.perform(
				MockMvcRequestBuilders.post("/clientes").contentType(MediaType.APPLICATION_JSON).content(clienteJson))
				.andExpect(MockMvcResultMatchers.status().isCreated());

		String response = mockMvc.perform(get("/clientes")).andReturn().getResponse().getContentAsString();

		System.out.println("Respuesta JSON: " + response);

		mockMvc.perform(get("/clientes")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].nombre").value("Juan Perez"));
	}
}
