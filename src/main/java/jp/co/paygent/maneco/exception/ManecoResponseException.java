package jp.co.paygent.maneco.exception;

import jp.co.paygent.maneco.data.ErrorResponse;


public class ManecoResponseException extends ManecoException {

	private static final long serialVersionUID = 8330251177385558621L;
	private int httpStatusCode;
	private ErrorResponse errorData;

	public ManecoResponseException(int httpStatusCode, ErrorResponse errorData) {
		super(errorData.errorCode, errorData.errorMsg);
		this.httpStatusCode = httpStatusCode;
		this.errorData = errorData;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public String[] getErrorParameter() {
		if (errorData.parameter == null || errorData.parameter.trim().length() == 0) {
			return new String[0];
		} else {
			return errorData.parameter.split(",");
		}
	}

	public ErrorResponse getErrorResponse() {
		return errorData;
	}

}
