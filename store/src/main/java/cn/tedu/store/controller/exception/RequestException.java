package cn.tedu.store.controller.exception;

public class RequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1409798915235738182L;

	public RequestException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RequestException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RequestException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
