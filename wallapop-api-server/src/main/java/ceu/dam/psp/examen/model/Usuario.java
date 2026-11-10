package ceu.dam.psp.examen.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Usuario {

	private String identificador;
	private String descripcion;
	private String contraseña;
	private LocalDate fechaAlta;
	private LocalDate fechaUltimoAcceso;
	private List<Articulo> catalogo;
	
	
	
}
