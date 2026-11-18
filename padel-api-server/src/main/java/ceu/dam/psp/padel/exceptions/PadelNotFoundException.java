package ceu.dam.psp.padel.exceptions;

public class PadelNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1834265147325961678L;

	public PadelNotFoundException() {
	}

	public PadelNotFoundException(String message) {
		super(message);
	}

	public PadelNotFoundException(Throwable cause) {
		super(cause);
	}

	public PadelNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PadelNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
