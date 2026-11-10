package ceu.dam.psp.examen.exceptions;

public class UsuarioDuplicadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5551592049208734465L;

	public UsuarioDuplicadoException() {
	}

	public UsuarioDuplicadoException(String message) {
		super(message);
	}

	public UsuarioDuplicadoException(Throwable cause) {
		super(cause);
	}

	public UsuarioDuplicadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioDuplicadoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
