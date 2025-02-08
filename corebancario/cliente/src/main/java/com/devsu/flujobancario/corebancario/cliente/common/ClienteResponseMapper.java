package com.devsu.flujobancario.corebancario.cliente.common;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.devsu.flujobancario.corebancario.cliente.dtos.ClienteResponse;
import com.devsu.flujobancario.corebancario.cliente.entities.Cliente;


@Mapper(componentModel = "spring")
public interface ClienteResponseMapper {

	ClienteResponse ClienteToClienteResponse(Cliente source);

	List<ClienteResponse> ClienteListToCLienteResposeList(List<Cliente> source);

	@InheritInverseConfiguration
	Cliente CLienteResponseToCliente(ClienteResponse source);

	@InheritInverseConfiguration
	List<Cliente> ClienteResponseToClienteList(List<ClienteResponse> source);

}
