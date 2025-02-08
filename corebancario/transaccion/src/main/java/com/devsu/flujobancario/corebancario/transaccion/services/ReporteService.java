package com.devsu.flujobancario.corebancario.transaccion.services;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.devsu.flujobancario.corebancario.transaccion.dtos.ClienteRequest;
import com.devsu.flujobancario.corebancario.transaccion.dtos.CuentaResumenRequest;
import com.devsu.flujobancario.corebancario.transaccion.dtos.MovimientoResponse;
import com.devsu.flujobancario.corebancario.transaccion.dtos.ReporteEstadoCuentaResponse;
import com.devsu.flujobancario.corebancario.transaccion.entities.Cuenta;
import com.devsu.flujobancario.corebancario.transaccion.entities.Movimiento;
import com.devsu.flujobancario.corebancario.transaccion.exceptions.BussinesRuleException;
import com.fasterxml.jackson.databind.JsonNode;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Service
public class ReporteService {

	@Autowired
	private MovimientoService movimientoService;
	@Autowired
	private CuentaService cuentaService;
	@Autowired
	private WebClient.Builder webClientBuilder;

	HttpClient client = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
			.option(ChannelOption.SO_KEEPALIVE, true).option(EpollChannelOption.TCP_KEEPIDLE, 300)
			.option(EpollChannelOption.TCP_KEEPINTVL, 60).responseTimeout(Duration.ofSeconds(1))
			.doOnConnected(connection -> {
				connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
				connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
			});

	public ReporteEstadoCuentaResponse findByNumeroCuentaAndFechaBetween(Long clientId, LocalDateTime fechaInicio,
			LocalDateTime fechaFin) throws UnknownHostException, BussinesRuleException {

		List<Cuenta> cuentasCliente = cuentaService.findByClienteId(clientId);
		if (cuentasCliente.isEmpty()) {
			BussinesRuleException bussinesRuleException = new BussinesRuleException("1025",
					"Error validacion: No existe registros " + clientId, HttpStatus.PRECONDITION_FAILED);
			throw bussinesRuleException;
		}

		List<CuentaResumenRequest> cuentasResumen = cuentasCliente.stream().map(cuenta -> {

			List<Movimiento> movimientos = movimientoService.findByAccountIdAndDatyBetween(cuenta.getNumeroCuenta(),
					fechaInicio, fechaFin);

			BigDecimal totalCreditos = movimientos.stream().filter(m -> "CREDITO".equals(m.getTipoMovimiento().name()))
					.map(Movimiento::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

			BigDecimal totalDebitos = movimientos.stream().filter(m -> "DEBITO".equals(m.getTipoMovimiento().name()))
					.map(Movimiento::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

			List<MovimientoResponse> movimientoResponse = movimientos.stream()
					.map(mov -> new MovimientoResponse(mov.getId(), mov.getTipoMovimiento().name(), mov.getValor(),
							mov.getSaldo(), cuenta.getNumeroCuenta(), mov.getFecha().toString()))
					.collect(Collectors.toList());

			return new CuentaResumenRequest(cuenta.getNumeroCuenta(), cuenta.getTipoCuenta().name(),
					cuenta.getSaldoInicial(), totalCreditos, totalDebitos, movimientoResponse);

		}).collect(Collectors.toList());
		ClienteRequest cliente = getCliente(clientId);
		return new ReporteEstadoCuentaResponse(cliente.getNombre(), cliente.getIdentificacion(), cuentasResumen);
	}

	private ClienteRequest getCliente(Long clientId) throws UnknownHostException {
		ClienteRequest cliente = null;
		try {
			WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(client))
					.baseUrl("http://COREBANCARIO-CLIENTE/clientes")
					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.defaultUriVariables(Collections.singletonMap("url", "http://COREBANCARIO-CLIENTE/clientes"))
					.build();
			JsonNode block = build.method(HttpMethod.GET).uri("/" + clientId).retrieve().bodyToMono(JsonNode.class)
					.block();
			cliente = new ClienteRequest(block.get("nombre").asText(), block.get("identificacion").asText());
		} catch (WebClientResponseException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				return cliente;
			} else {
				throw new UnknownHostException(ex.getMessage());
			}
		}
		return cliente;
	}

	public String generarPdfBase64(ReporteEstadoCuentaResponse reporte) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document();
			PdfWriter.getInstance(document, baos);
			document.open();

			document.add(new Paragraph("Estado de Cuenta"));
			document.add(new Paragraph("Cliente: " + reporte.getNombre()));

			for (CuentaResumenRequest cuenta : reporte.getCuentas()) {
				document.add(new Paragraph("\nNúmero de Cuenta: " + cuenta.getNumeroCuenta()));
				document.add(new Paragraph("Tipo: " + cuenta.getTipoCuenta()));
				document.add(new Paragraph("Saldo Actual: $" + cuenta.getSaldoActual()));
				document.add(new Paragraph("Total Créditos: $" + cuenta.getTotalCreditos()));
				document.add(new Paragraph("Total Débitos: $" + cuenta.getTotalDebitos()));

				for (MovimientoResponse mov : cuenta.getMovimientos()) {
					document.add(new Paragraph("  - " + mov.getFecha() + " | " + mov.getTipoMovimiento() + " | $"
							+ mov.getValor() + " | Saldo: $" + mov.getSaldo()));
				}
			}

			document.close();
			return Base64.getEncoder().encodeToString(baos.toByteArray());

		} catch (Exception e) {
			throw new RuntimeException("Error al generar PDF", e);
		}
	}

}
