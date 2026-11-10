package ceu.dam.psp.examen.exceptions;

public class NoExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8594599380627859213L;

	public NoExisteException() {
	}

	public NoExisteException(String message) {
		super(message);
	}

	public NoExisteException(Throwable cause) {
		super(cause);
	}

	public NoExisteException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoExisteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
