package ceu.dam.psp.examen.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequestDto {
	@NotBlank
	private String identificador;
	@NotBlank
	private String contraseña;

}
