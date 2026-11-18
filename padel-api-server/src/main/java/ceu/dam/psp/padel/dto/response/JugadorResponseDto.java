package ceu.dam.psp.padel.dto.response;

import lombok.Data;

@Data
public class JugadorResponseDto {

	private Long id;
	private String nombre;
	private String nivel; // PRINCIPIANTE / INTERMEDIO / AVANZADO
	private Integer edad;
}
