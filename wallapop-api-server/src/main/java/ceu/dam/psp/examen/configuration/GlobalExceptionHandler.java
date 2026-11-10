package ceu.dam.psp.examen.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ceu.dam.psp.examen.exceptions.ErrorPagoException;
import ceu.dam.psp.examen.exceptions.NoAutorizadoException;
import ceu.dam.psp.examen.exceptions.NoExisteException;
import ceu.dam.psp.examen.exceptions.UsuarioDuplicadoException;
import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

	@ExceptionHandler(exception = ErrorPagoException.class)
	public ResponseEntity<String> handle(ErrorPagoException e) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}

	@ExceptionHandler(exception = NoAutorizadoException.class)
	public ResponseEntity<String> handle(NoAutorizadoException e) {

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	@ExceptionHandler(exception = NoExisteException.class)
	public ResponseEntity<String> handle(NoExisteException e) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ExceptionHandler(exception = UsuarioDuplicadoException.class)
	public ResponseEntity<String> handle(UsuarioDuplicadoException e) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@ExceptionHandler(exception = MethodArgumentNotValidException.class)
	public ResponseEntity<String> handle(MethodArgumentNotValidException e) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error de validación de "
				+ e.getFieldError().getField() + " - " + e.getFieldError().getDefaultMessage());
	}

	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<String> handle(Exception e) {
		log.error("Error inesperado" + e.getMessage(), e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
}
