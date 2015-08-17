package jp.co.paygent.maneco.data;

import java.util.List;

import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(
        value = {"object",
                "result",
                "data"})
@ToString
public class RecursionsListResponse extends ResponseData {
	public final int result;
	public final List<RecursionsResponse> data;

	public RecursionsListResponse(
			@JsonProperty("object") String object,
			@JsonProperty("result") int result,
			@JsonProperty("data") List<RecursionsResponse> data
			) {
		super(object);
		this.result = result;
		this.data = data;
	}
}
