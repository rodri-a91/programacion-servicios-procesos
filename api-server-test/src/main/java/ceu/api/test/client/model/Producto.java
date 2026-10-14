package ceu.api.test.client.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Producto {
	private String codBarra;
	private String descripcion;
	private BigDecimal precio;
	private LocalDate fechaCaducidad;

}
