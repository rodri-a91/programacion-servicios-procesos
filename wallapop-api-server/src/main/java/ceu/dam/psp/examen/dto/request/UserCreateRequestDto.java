package ceu.dam.psp.examen.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateRequestDto {
	@NotBlank
	private String identificador;
	@NotBlank
	@Size(max = 30)
	private String descripcion;
	@NotBlank
	@Size(min = 10)
	private String contraseña;

}
