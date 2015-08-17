package jp.co.paygent.maneco.exception;

public class ManecoRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 6555138789365669995L;
	private final String errorCode;

	public ManecoRuntimeException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ManecoRuntimeException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ManecoRuntimeException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public ManecoRuntimeException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getCode() {
		return errorCode;
	}

}
