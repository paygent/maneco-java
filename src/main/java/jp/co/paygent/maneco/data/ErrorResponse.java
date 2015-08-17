package jp.co.paygent.maneco.data;

import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@ToString
@JsonPropertyOrder(
		value = {"object",
				 "error_code",
				 "error_msg",
				 "parameter"})
public class ErrorResponse extends ResponseData {

	@JsonProperty("error_code")
	public final String errorCode;
	@JsonProperty("error_msg")
	public final String errorMsg;
	public final String parameter;

	public ErrorResponse(
			@JsonProperty("object") String object,
			@JsonProperty("error_code") String errorCode,
			@JsonProperty("error_msg") String errorMsg,
			@JsonProperty("parameter") String parameter
			) {
		super(object);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.parameter = parameter;
	}
}
