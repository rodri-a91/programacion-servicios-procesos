package ceu.dam.psp.videojuegos.exceptions;

public class ApiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6691964952239556328L;

	public ApiException() {
	}

	public ApiException(String message) {
		super(message);
	}

	public ApiException(Throwable cause) {
		super(cause);
	}

	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
