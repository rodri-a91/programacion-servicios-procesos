package ceu.dam.psp.padel.controller;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ceu.dam.psp.padel.dto.request.JugadorRequestDto;
import ceu.dam.psp.padel.dto.response.JugadorResponseDto;
import ceu.dam.psp.padel.dto.response.PartidoResponseDto;
import ceu.dam.psp.padel.exceptions.PadelInvalidDataException;
import ceu.dam.psp.padel.exceptions.PadelNotFoundException;
import ceu.dam.psp.padel.model.Jugador;
import ceu.dam.psp.padel.model.Partido;
import ceu.dam.psp.padel.service.PadelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class PadelController {
	private final PadelService service;
	private final ModelMapper mapper;

	@GetMapping("/partidos")
	public List<PartidoResponseDto> consultarProximosPartidos() {

		List<Partido> partidos = service.consultarProximosPartidos();

		return partidos.stream().map(p -> mapper.map(p, PartidoResponseDto.class)).toList();

	}

	@GetMapping("/partidos/{codigo}")
	public PartidoResponseDto consultarPartido(@PathVariable String codigo) throws PadelNotFoundException {
		Partido partidoConsul = service.consultarPartido(codigo);

		return mapper.map(partidoConsul, PartidoResponseDto.class);
	}

	@GetMapping("/jugadores/{idJugador}")
	public JugadorResponseDto consultarJugador(Long idJugador) throws PadelNotFoundException {
		Jugador jugadorConsul = service.consultarJugador(idJugador);

		return mapper.map(jugadorConsul, JugadorResponseDto.class);
	}

	@PostMapping("/jugadores")
	public JugadorResponseDto crearJugador(@Valid @RequestBody JugadorRequestDto jugador)
			throws PadelInvalidDataException {
		jugador.setNivel(jugador.getNivel().toUpperCase());
		if (!jugador.getNivel().equals("PRINCIPIANTE") || !jugador.getNivel().equals("INTERMEDIO")
				|| !jugador.getNivel().equals("AVANZADO")) {
			throw new PadelInvalidDataException("Los datos de nivel son invalidos");
		}

		Jugador jug = mapper.map(jugador, Jugador.class);
		jug = service.crearJugador(jug);
		return mapper.map(jug, JugadorResponseDto.class);
	}

	@PostMapping("/jugadores/{idOrganizador}/partidos")
	public String crearPartido(@RequestBody @Valid PartidoResponseDto partido, @PathVariable Long idOrganizador)
			throws PadelNotFoundException {
		service.consultarJugador(idOrganizador);

		if (partido.getFecha() == null) {
			partido.setFecha(LocalDate.now());
		}

		Partido partidoCreado = service.crearPartido(partido.getFecha(), partido.getHora(), partido.getIndividuales(),
				idOrganizador);

		return partidoCreado.getCodigo();
	}

	@PostMapping("/partidos/{codPartido}/jugadores/{idJugador}")
	public List<JugadorResponseDto> addJugador(@PathVariable String codPartido, @PathVariable Long idJugador)
			throws PadelNotFoundException, PadelInvalidDataException {
		Partido partido = service.consultarPartido(codPartido);
		Jugador jugador = service.consultarJugador(idJugador);

		for (Jugador jug : partido.getJugadores()) {
			if (jug.equals(jugador)) {
				throw new PadelInvalidDataException("Este jugador ya está apuntado al partido");
			}
		}
		
		

		return null;
	}

}
