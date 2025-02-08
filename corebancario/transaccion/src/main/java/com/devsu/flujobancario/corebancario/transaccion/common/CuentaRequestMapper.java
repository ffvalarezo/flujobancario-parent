package com.devsu.flujobancario.corebancario.transaccion.common;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.devsu.flujobancario.corebancario.transaccion.common.enums.TipoCuenta;
import com.devsu.flujobancario.corebancario.transaccion.dtos.CuentaRequest;
import com.devsu.flujobancario.corebancario.transaccion.entities.Cuenta;

@Mapper(componentModel = "spring")
public interface CuentaRequestMapper {

	@Mappings({ @Mapping(source = "tipoCuenta", target = "tipoCuenta", qualifiedByName = "stringToTipoCuenta") })
	Cuenta CuentaResquestToCuenta(CuentaRequest source);

	List<Cuenta> CuentaRequestListToCuentaList(List<CuentaRequest> source);

	@InheritInverseConfiguration
	@Mappings({ @Mapping(source = "tipoCuenta", target = "tipoCuenta", qualifiedByName = "tipoCuentaToString") })
	CuentaRequest CuentaToCuentaRequest(Cuenta source);

	@InheritInverseConfiguration
	List<CuentaRequest> CuentaListToCuentaRequestList(List<Cuenta> source);

	@Named("stringToTipoCuenta")
	static TipoCuenta stringToTipoCuenta(String tipo) {
		return TipoCuenta.valueOf(tipo.toUpperCase());
	}

	@Named("tipoCuentaToString")
	static String tipoCuentaToString(TipoCuenta tipoCuenta) {
		return tipoCuenta.name();
	}

}
