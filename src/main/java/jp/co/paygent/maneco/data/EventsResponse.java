package jp.co.paygent.maneco.data;

import java.util.Date;

import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@ToString
@JsonPropertyOrder(
		value = {"event_id",
				 "object",
				 "livemode",
				 "created_time",
				 "type",
				 "detail"})
public class EventsResponse extends ResponseData {

	@JsonProperty("event_id")
	public final String eventId;
	public final boolean livemode;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo")
	@JsonProperty("created_time")
	public final Date createdTime;
	public final String type;
	@JsonTypeInfo(use = Id.NONE)
	public final EventData detail;

	public EventsResponse(
			@JsonProperty("event_id") String eventId,
			@JsonProperty("object") String object,
			@JsonProperty("livemode") boolean livemode,
			@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo") @JsonProperty("created_time") Date createdTime,
			@JsonProperty("type") String type,
			@JsonProperty("detail")
			@JsonTypeInfo(
				    use      = JsonTypeInfo.Id.NAME,
				    include  = JsonTypeInfo.As.PROPERTY,
				    property = "object",
				    visible  = true)
			@JsonSubTypes({
			    @Type(value = PaymentsResponse.class,   name = "card-payment" ),
			    @Type(value = CustomersResponse.class,  name = "customer" ),
			    @Type(value = RecursionsResponse.class, name = "recursion" ),
			    @Type(value = TestEventResponse.class,  name = "test" )
				    })
			EventData detail
			) {
		super(object);
		this.eventId = eventId;
		this.livemode = livemode;
		this.createdTime = createdTime;
		this.type = type;
		this.detail = detail;
	}
}
