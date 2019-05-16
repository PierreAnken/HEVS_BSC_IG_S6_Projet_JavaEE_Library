package library.exception;

public class BookException extends RuntimeException {

	private static final long serialVersionUID = 3587959669456501009L;

	public BookException() {
		super();
	}

	public BookException(String arg0) {
		super(arg0);
	}

	public BookException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BookException(Throwable arg0) {
		super(arg0);
	}

}
