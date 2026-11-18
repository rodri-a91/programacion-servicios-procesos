package ceu.dam.psp.padel.service;

import java.time.LocalDate;
import java.util.List;

import ceu.dam.psp.padel.exceptions.PadelNotFoundException;
import ceu.dam.psp.padel.model.Jugador;
import ceu.dam.psp.padel.model.Partido;


public interface PadelService {

    /**
     * Crea un nuevo jugador en el sistema.
     * <p>
     * El identificador del jugador se genera automáticamente.
     *
     * @param jugador objeto Jugador con los datos básicos (sin id)
     * @return el jugador creado, incluyendo el id asignado
     */
    Jugador crearJugador(Jugador jugador);


    /**
     * Crea un nuevo partido de pádel.
     * <p>
     * El partido se crea con un código único generado automáticamente.
     * El organizador queda inscrito automáticamente como primer jugador del partido.
     *
     * @param fecha fecha en la que se jugará el partido
     * @param hora hora del partido (formato entero, por ejemplo 18 para las 18:00)
     * @param individuales indica si el partido es individual (true) o de dobles (false)
     * @param idOrganizador identificador del jugador que organiza el partido
     * @return el partido creado
     */
    Partido crearPartido(LocalDate fecha, Integer hora, Boolean individuales, Long idOrganizador);


    /**
     * Actualiza la lista completa de jugadores inscritos en un partido.
     * <p>
     * Sustituye la lista de jugadores existente por la nueva lista proporcionada.
     *
     * @param codigoPartido código identificador del partido
     * @param listaJugadores lista de jugadores que participarán en el partido
     * @return el partido actualizado
     */
    Partido actualizarJugadoresPartido(String codigoPartido, List<Jugador> listaJugadores);


    /**
     * Consulta los datos de un partido a partir de su código.
     *
     * @param codigo código identificador del partido
     * @return el partido correspondiente al código indicado
     * @throws PadelNotFoundException si no existe ningún partido con ese código
     */
    Partido consultarPartido(String codigo) throws PadelNotFoundException;


    /**
     * Devuelve la lista de partidos cuya fecha es igual o posterior a la fecha actual.
     *
     * @return lista de próximos partidos
     */
    List<Partido> consultarProximosPartidos();


    /**
     * Consulta un jugador a partir de su identificador.
     *
     * @param idJugador identificador del jugador
     * @return el jugador correspondiente al id indicado
     * @throws PadelNotFoundException si no existe ningún jugador con ese id
     */
    Jugador consultarJugador(Long idJugador) throws PadelNotFoundException;


    /**
     * Busca jugadores aplicando criterios de filtrado opcionales.
     * <p>
     * El filtro puede incluir nivel y rango de edad.
     * Si un campo del filtro es nulo, no se aplica ese criterio.
     *
     * @param filtro objeto que contiene los criterios de búsqueda
     * @return lista de jugadores que cumplen los criterios del filtro
     */
    List<Jugador> buscarJugadores(JugadorFiltro filtro);

}
