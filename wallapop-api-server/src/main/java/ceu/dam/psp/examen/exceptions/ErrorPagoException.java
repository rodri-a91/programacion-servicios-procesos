package ceu.dam.psp.examen.exceptions;

public class ErrorPagoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3719369822776021016L;

	public ErrorPagoException() {
	}

	public ErrorPagoException(String message) {
		super(message);
	}

	public ErrorPagoException(Throwable cause) {
		super(cause);
	}

	public ErrorPagoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorPagoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
