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
public class CustomersListResponse extends ResponseData {
	public final int result;
	public final List<CustomersResponse> data;

	public CustomersListResponse(
			@JsonProperty("object") String object,
			@JsonProperty("result") int result,
			@JsonProperty("data") List<CustomersResponse> data
			) {
		super(object);
		this.result = result;
		this.data = data;
	}
}
