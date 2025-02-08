package com.devsu.flujobancario.corebancario.cliente.common;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.devsu.flujobancario.corebancario.cliente.dtos.ClienteRequest;
import com.devsu.flujobancario.corebancario.cliente.entities.Cliente;


@Mapper(componentModel = "spring")
public interface ClienteRequestMapper {

	Cliente ClienteResquestToCliente(ClienteRequest source);

	List<Cliente> ClienteRequestListToClienteList(List<ClienteRequest> source);

	@InheritInverseConfiguration
	ClienteRequest ClienteToClienteRequest(Cliente source);

	@InheritInverseConfiguration
	List<ClienteRequest> ClienteListToClienteRequestList(List<Cliente> source);

}
