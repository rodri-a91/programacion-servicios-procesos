package ceu.dam.psp.padel.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PartidoRequestDto {

	private LocalDate fecha;
	private Integer hora;
	private Boolean individuales;
}
