package ceu.dam.psp.padel.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JugadorRequestDto {
	
	@NotBlank
	@Size(max = 100)
    private String nombre;
	@NotBlank
    private String nivel; // PRINCIPIANTE / INTERMEDIO / AVANZADO
    @NotNull
    @Min(18)
    private Integer edad;

}
