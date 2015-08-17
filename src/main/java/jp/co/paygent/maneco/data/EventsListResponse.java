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
public class EventsListResponse extends ResponseData {
	public final int result;
	public final List<EventsResponse> data;

	public EventsListResponse(
			@JsonProperty("object") String object,
			@JsonProperty("result") int result,
			@JsonProperty("data") List<EventsResponse> data
			) {
		super(object);
		this.result = result;
		this.data = data;
	}
}
