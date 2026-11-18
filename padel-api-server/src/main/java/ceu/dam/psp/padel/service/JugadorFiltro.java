package ceu.dam.psp.padel.service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JugadorFiltro {
    private String nivel;      // PRINCIPIANTE / INTERMEDIO / AVANZADO
    private Integer edadMin;
    private Integer edadMax;
}
