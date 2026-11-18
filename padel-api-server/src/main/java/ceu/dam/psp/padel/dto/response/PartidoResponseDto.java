package ceu.dam.psp.padel.dto.response;

import java.time.LocalDate;

import ceu.dam.psp.padel.model.Jugador;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PartidoResponseDto {

	private String codigo;
	@FutureOrPresent
	private LocalDate fecha;
	@Min(8)
	@Max(22)
	private Integer hora;
	@NotNull
	private Boolean individuales; // si es false, la partida sera de dobles

	private Jugador organizador;

}
