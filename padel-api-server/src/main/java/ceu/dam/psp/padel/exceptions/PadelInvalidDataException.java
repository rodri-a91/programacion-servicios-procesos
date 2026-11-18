package ceu.dam.psp.padel.exceptions;

public class PadelInvalidDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4662810538569476210L;

	public PadelInvalidDataException() {
	}

	public PadelInvalidDataException(String message) {
		super(message);
	}

	public PadelInvalidDataException(Throwable cause) {
		super(cause);
	}

	public PadelInvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public PadelInvalidDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
