package ceu.dam.psp.videojuegos.exceptions;

public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5485376342523241107L;

	public NotFoundException() {
	}

	public NotFoundException(String arg0) {
		super(arg0);
	}

	public NotFoundException(Throwable arg0) {
		super(arg0);
	}

	public NotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
