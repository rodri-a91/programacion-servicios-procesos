package ceu.dam.psp.padel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partido {

    private String codigo;
    private LocalDate fecha;
    private Integer hora;
    private Boolean individuales; // si es false, la partida sera de dobles

    private List<Jugador> jugadores;
    private Jugador organizador; // el organizador esta en la lista de jugadores siempre
}
