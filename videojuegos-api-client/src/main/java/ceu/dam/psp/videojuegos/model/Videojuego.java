package ceu.dam.psp.videojuegos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videojuego {
	private String id;
	private String nombre;
	private Double valoracion;
	private Integer añoPublicacion;
	private String paisOrigen;

}
