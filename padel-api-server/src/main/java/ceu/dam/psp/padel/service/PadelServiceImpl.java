package ceu.dam.psp.padel.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import ceu.dam.psp.padel.exceptions.PadelNotFoundException;
import ceu.dam.psp.padel.model.Jugador;
import ceu.dam.psp.padel.model.Partido;

@Service
public class PadelServiceImpl implements PadelService {

	private static final Map<Long, Jugador> jugadores = new HashMap<>();
	private static final Map<String, Partido> partidos = new HashMap<>();
	private static final AtomicLong idGeneradorJugador = new AtomicLong(1);
	private static final AtomicLong idGeneradorPartido = new AtomicLong(1);

	public PadelServiceImpl() {
		cargarDatosIniciales();
	}

	@Override
	public Jugador crearJugador(Jugador jugador) {
		long id = idGeneradorJugador.getAndIncrement();
		jugador.setId(id);
		jugadores.put(id, jugador);
		return jugador;
	}
	
	
	@Override
	public Partido crearPartido(LocalDate fecha, Integer hora, Boolean individuales, Long idOrganizador) {
	    Jugador organizador = jugadores.get(idOrganizador);
	    String codigo = String.format("P%04d", idGeneradorPartido.getAndIncrement());

	    Partido partido = new Partido();
	    partido.setCodigo(codigo);
	    partido.setFecha(fecha);
	    partido.setHora(hora);
	    partido.setIndividuales(individuales);
	    partido.setOrganizador(organizador);
	    partido.setJugadores(new ArrayList<>(List.of(organizador)));

	    partidos.put(codigo, partido);
	    return partido;
	}

	
	
	@Override
	public Partido actualizarJugadoresPartido(String codigoPartido, List<Jugador> listaJugadores) {
	    Partido partido = partidos.get(codigoPartido);
	    partido.setJugadores(listaJugadores);
	    return partido;
	}

	
	@Override
	public Partido consultarPartido(String codigo) throws PadelNotFoundException {
	    Partido partido = partidos.get(codigo);
	    if (partido == null) {
	        throw new PadelNotFoundException("No existe el partido con código: " + codigo);
	    }
	    return partido;
	}
	
	@Override
	public List<Partido> consultarProximosPartidos() {
		return partidos.values().stream().filter(p -> p.getFecha().compareTo(LocalDate.now()) >= 0 ).toList();
	}
	
	@Override
	public Jugador consultarJugador(Long idJugador) throws PadelNotFoundException {
	    Jugador jugador = jugadores.get(idJugador);
	    if (jugador == null) {
	        throw new PadelNotFoundException("No existe el jugador con id: " + idJugador);
	    }
	    return jugador;
	}
	
	
	@Override
	public List<Jugador> buscarJugadores(JugadorFiltro filtro) {
	    return jugadores.values().stream()
	            .filter(j -> filtro.getNivel() == null || filtro.getNivel().isBlank() || j.getNivel().equalsIgnoreCase(filtro.getNivel()))
	            .filter(j -> filtro.getEdadMin() == null || j.getEdad() >= filtro.getEdadMin())
	            .filter(j -> filtro.getEdadMax() == null || j.getEdad() <= filtro.getEdadMax())
	            .toList();
	}




	private void cargarDatosIniciales() {

		// ===== JUGADORES (10) =====
		Jugador j1 = new Jugador(idGeneradorJugador.getAndIncrement(), "Carlos", "INTERMEDIO", 30);
		Jugador j2 = new Jugador(idGeneradorJugador.getAndIncrement(), "Lucía",  "AVANZADO", 28);
		Jugador j3 = new Jugador(idGeneradorJugador.getAndIncrement(), "Miguel",  "PRINCIPIANTE", 19);
		Jugador j4 = new Jugador(idGeneradorJugador.getAndIncrement(), "Ana",  "INTERMEDIO", 26);
		Jugador j5 = new Jugador(idGeneradorJugador.getAndIncrement(), "Javier",  "AVANZADO", 40);
		Jugador j6 = new Jugador(idGeneradorJugador.getAndIncrement(), "Laura",  "PRINCIPIANTE", 22);
		Jugador j7 = new Jugador(idGeneradorJugador.getAndIncrement(), "Pablo",  "INTERMEDIO", 33);
		Jugador j8 = new Jugador(idGeneradorJugador.getAndIncrement(), "Marta",  "AVANZADO", 29);
		Jugador j9 = new Jugador(idGeneradorJugador.getAndIncrement(), "Sergio",  "PRINCIPIANTE", 24);
		Jugador j10 = new Jugador(idGeneradorJugador.getAndIncrement(), "Elena",  "INTERMEDIO", 31);

		jugadores.put(j1.getId(), j1);
		jugadores.put(j2.getId(), j2);
		jugadores.put(j3.getId(), j3);
		jugadores.put(j4.getId(), j4);
		jugadores.put(j5.getId(), j5);
		jugadores.put(j6.getId(), j6);
		jugadores.put(j7.getId(), j7);
		jugadores.put(j8.getId(), j8);
		jugadores.put(j9.getId(), j9);
		jugadores.put(j10.getId(), j10);

		// ===== PARTIDOS (4) =====

		String c1 = String.format("P%04d", idGeneradorPartido.getAndIncrement());
		String c2 = String.format("P%04d", idGeneradorPartido.getAndIncrement());
		String c3 = String.format("P%04d", idGeneradorPartido.getAndIncrement());
		String c4 = String.format("P%04d", idGeneradorPartido.getAndIncrement());

		// 1️ Partido DOBLES (4 jugadores)
		Partido p1 = new Partido(c1, LocalDate.now().plusDays(1), 18, false,
				Arrays.asList(j1, j2, j3, j4), j1);

		// 2️ Partido DOBLES (4 jugadores)
		Partido p2 = new Partido(c2, LocalDate.now().plusDays(2), 19, false,
				Arrays.asList(j3, j4, j5, j6), j3);

		// 3️Partido DOBLES (solo organizador)
		Partido p3 = new Partido(c3, LocalDate.now().plusDays(3), 20, false,
				new ArrayList<>(List.of(j2)), j2);

		// 4️Partido INDIVIDUAL (2 jugadores)
		Partido p4 = new Partido(c4, LocalDate.now().plusDays(4), 17, true,
				Arrays.asList(j5, j6), j5);

		partidos.put(p1.getCodigo(), p1);
		partidos.put(p2.getCodigo(), p2);
		partidos.put(p3.getCodigo(), p3);
		partidos.put(p4.getCodigo(), p4);
	}




}
