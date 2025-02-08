package com.devsu.flujobancario.corebancario.cliente.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.devsu.flujobancario.corebancario.cliente.entities.Cliente;


public class ClienteTest {

	@Test
	public void testClienteCreation() {
		Cliente cliente = new Cliente();
		cliente.setId(1l);
		cliente.setContrasena("1234");
		cliente.setEstado(true);

		assertNotNull(cliente);
		assertEquals(1l, cliente.getId());
		assertEquals("1234", cliente.getContrasena());
		assertTrue(cliente.isEstado());
	}
}
