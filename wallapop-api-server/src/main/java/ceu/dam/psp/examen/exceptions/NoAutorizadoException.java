package ceu.dam.psp.examen.exceptions;

public class NoAutorizadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4110813135456279325L;

	public NoAutorizadoException() {
		super();
	}

	public NoAutorizadoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoAutorizadoException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoAutorizadoException(String message) {
		super(message);
	}

	public NoAutorizadoException(Throwable cause) {
		super(cause);
	}

	
	
}
