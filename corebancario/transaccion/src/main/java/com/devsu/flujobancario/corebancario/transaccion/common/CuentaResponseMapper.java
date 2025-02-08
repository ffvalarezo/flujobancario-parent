package com.devsu.flujobancario.corebancario.transaccion.common;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.devsu.flujobancario.corebancario.transaccion.common.enums.TipoCuenta;
import com.devsu.flujobancario.corebancario.transaccion.dtos.CuentaResponse;
import com.devsu.flujobancario.corebancario.transaccion.entities.Cuenta;

@Mapper(componentModel = "spring")
public interface CuentaResponseMapper {

	@Mappings({ @Mapping(source = "tipoCuenta", target = "tipoCuenta", qualifiedByName = "tipoCuentaToString") })
	CuentaResponse CuentaToCuentaResponse(Cuenta source);

	List<CuentaResponse> CuentaListToCuentaResposeList(List<Cuenta> source);

	@InheritInverseConfiguration
	@Mappings({ @Mapping(source = "tipoCuenta", target = "tipoCuenta", qualifiedByName = "stringToTipoCuenta") })
	Cuenta CuentaResponseToCuenta(CuentaResponse source);

	@InheritInverseConfiguration
	List<Cuenta> CuentaResponseToCuentaList(List<CuentaResponse> source);

	@Named("stringToTipoCuenta")
	static TipoCuenta stringToTipoCuenta(String tipo) {
		return TipoCuenta.valueOf(tipo.toUpperCase());
	}

	@Named("tipoCuentaToString")
	static String tipoCuentaToString(TipoCuenta tipoCuenta) {
		return tipoCuenta.name();
	}
}
