package jp.co.paygent.maneco.data;

import java.util.List;

import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonPropertyOrder(
        value = {"object",
                "result",
                "data"})
@ToString
public class PaymentsListResponse extends ResponseData {
	public final int result;
	@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
	public final List<? extends PaymentsResponse> data;

	public PaymentsListResponse(
			@JsonProperty("object") String object,
			@JsonProperty("result") int result,
			@JsonProperty("data") List<? extends PaymentsResponse> data
			) {
		super(object);
		this.result = result;
		this.data = data;
	}
}
