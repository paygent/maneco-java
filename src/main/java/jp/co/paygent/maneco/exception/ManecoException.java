package jp.co.paygent.maneco.exception;

public class ManecoException extends Exception {

    public static final String CONNECTION_ERROR = "ME0001";
    public static final String JSON_ERROR       = "ME0002";
    public static final String UNKNOWN_ERROR    = "ME0003";
    public static final String INPUT_ERROR      = "ME0004";

	private final String errorCode;

	public ManecoException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ManecoException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ManecoException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public ManecoException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getCode() {
		return errorCode;
	}
}
