package com.devsu.flujobancario.corebancario.transaccion.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.devsu.flujobancario.corebancario.transaccion.common.enums.TipoMovimiento;
import com.devsu.flujobancario.corebancario.transaccion.dtos.MovimientoRequest;
import com.devsu.flujobancario.corebancario.transaccion.entities.Movimiento;

@Mapper(componentModel = "spring")
public interface MovimientoRequestMapper {
	DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	@Mappings({ @Mapping(source = "numeroCuenta", target = "cuenta.numeroCuenta"),
			@Mapping(source = "fecha", target = "fecha", qualifiedByName = "stringToLocalDateTime"),
			@Mapping(source = "tipoMovimiento", target = "tipoMovimiento", qualifiedByName = "stringToTipoMovimiento") })
	Movimiento MovimientoResquestToMovimiento(MovimientoRequest source);

	List<Movimiento> MovimientoRequestListToMovimientoList(List<MovimientoRequest> source);

	@InheritInverseConfiguration
	@Mappings({ @Mapping(source = "cuenta.numeroCuenta", target = "numeroCuenta"),
			@Mapping(source = "fecha", target = "fecha", qualifiedByName = "localDateTimeToString"),
			@Mapping(source = "tipoMovimiento", target = "tipoMovimiento", qualifiedByName = "tipoMovimientoToString") })
	MovimientoRequest MovimientoToMovimientoRequest(Movimiento source);

	@InheritInverseConfiguration
	List<MovimientoRequest> MovimientoListToMovimientoRequestList(List<Movimiento> source);

	@Named("stringToTipoMovimiento")
	static TipoMovimiento stringToTipoMovimiento(String tipo) {
		return TipoMovimiento.valueOf(tipo.toUpperCase()); // Convierte de String a Enum
	}

	@Named("tipoMovimientoToString")
	static String tipoMovimientoToString(TipoMovimiento tipoMovimiento) {
		return tipoMovimiento.name();
	}

	@Named("stringToLocalDateTime")
	static LocalDateTime stringToLocalDateTime(String fecha) {
		return LocalDateTime.parse(fecha, FORMATTER);
	}

	@Named("localDateTimeToString")
	static String localDateTimeToString(LocalDateTime fecha) {
		return fecha.format(FORMATTER);
	}
}
