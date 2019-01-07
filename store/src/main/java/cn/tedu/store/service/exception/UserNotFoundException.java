package cn.tedu.store.service.exception;

public class UserNotFoundException extends ServiceException {

	/**
	 * 用户数据不存在
	 */
	private static final long serialVersionUID = -1412497531641215421L;

	public UserNotFoundException() {
		super();
		
	}

	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UserNotFoundException(String message) {
		super(message);
		
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
		
	}

}
