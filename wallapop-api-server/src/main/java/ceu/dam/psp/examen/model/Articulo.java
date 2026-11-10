package ceu.dam.psp.examen.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Articulo {

	private Long codigo;
	private String nombre;
	private String detalles;
	private LocalDate fechaPublicacion;
	private Double precio;
	private String categoria;
	private String idPropietario;
	private Integer estado; // 0: Disponible // 1: Vendido 
	
}
