package ceu.dam.psp.padel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jugador {

    private Long id;
    private String nombre;
    private String nivel; // PRINCIPIANTE / INTERMEDIO / AVANZADO
    private Integer edad;
}
