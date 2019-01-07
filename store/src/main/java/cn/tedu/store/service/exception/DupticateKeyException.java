package cn.tedu.store.service.exception;

public class DupticateKeyException extends ServiceException {

	/**
	 * 用户名被占用冲突异常
	 */
	private static final long serialVersionUID = 5984692441411508072L;

	public DupticateKeyException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DupticateKeyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DupticateKeyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DupticateKeyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DupticateKeyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
