package com.devsu.flujobancario.corebancario.cliente.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.devsu.flujobancario.corebancario.cliente.entities.Persona;


public class PersonaTest {
	
	@Test
	void testPersonaCreation() {
		Persona persona = new Persona() {
		};
		persona.setNombre("Juan Perez");
		persona.setGenero("Masculino");
		persona.setEdad(30);
		persona.setIdentificacion("172001");
		persona.setDireccion("Avenida Siempre Viva 123");
		persona.setTelefono("0987654321");

		assertNotNull(persona);
		assertEquals("Juan Perez", persona.getNombre());
		assertEquals("Masculino", persona.getGenero());
		assertEquals(30, persona.getEdad());
		assertEquals("172001", persona.getIdentificacion());
		assertEquals("Avenida Siempre Viva 123", persona.getDireccion());
		assertEquals("0987654321", persona.getTelefono());
	}
	
}
