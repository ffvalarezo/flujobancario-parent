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
import com.devsu.flujobancario.corebancario.transaccion.dtos.MovimientoResponse;
import com.devsu.flujobancario.corebancario.transaccion.entities.Movimiento;

@Mapper(componentModel = "spring")
public interface MovimientoResponseMapper {

	DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	@Mappings({ @Mapping(source = "cuenta.numeroCuenta", target = "numeroCuenta"),
			@Mapping(source = "fecha", target = "fecha", qualifiedByName = "localDateTimeToString"),
			@Mapping(source = "tipoMovimiento", target = "tipoMovimiento", qualifiedByName = "tipoMovimientoToString") })
	MovimientoResponse MovimientoToMovimientoResponse(Movimiento source);

	List<MovimientoResponse> MovimientoListToMovimientoResposeList(List<Movimiento> source);

	@InheritInverseConfiguration
	@Mappings({ @Mapping(source = "numeroCuenta", target = "cuenta.numeroCuenta"),
			@Mapping(source = "fecha", target = "fecha", qualifiedByName = "stringToLocalDateTime"),
			@Mapping(source = "tipoMovimiento", target = "tipoMovimiento", qualifiedByName = "stringToTipoMovimiento") })
	Movimiento MovimientoResponseToMovimiento(MovimientoResponse source);

	@InheritInverseConfiguration
	List<Movimiento> MovimientoResponseToMovimientoList(List<MovimientoResponse> source);

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
