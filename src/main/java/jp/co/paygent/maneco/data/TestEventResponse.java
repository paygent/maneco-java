package jp.co.paygent.maneco.data;

import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@ToString
@JsonTypeInfo(use = Id.NONE)
@JsonPropertyOrder(value = {"object"})
public class TestEventResponse extends EventData {

	public TestEventResponse(@JsonProperty("object") String object) {
		super(object);
	}

}
