package jp.co.paygent.maneco.data;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.EXISTING_PROPERTY,
	    property = "object",
	    visible = true)
@JsonSubTypes({
	    @Type(value = CardPaymentsResponse.class, name = "card-payment" )
	    })
public abstract class PaymentsResponse extends EventData {

	public PaymentsResponse(String object) {
		super(object);
	}

}
