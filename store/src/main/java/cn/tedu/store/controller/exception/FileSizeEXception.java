package cn.tedu.store.controller.exception;

public class FileSizeEXception extends FileuploadEXception {

	/**
	 * 文件大小异常
	 */
	private static final long serialVersionUID = 3667641053984307663L;

	public FileSizeEXception() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileSizeEXception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileSizeEXception(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileSizeEXception(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileSizeEXception(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
