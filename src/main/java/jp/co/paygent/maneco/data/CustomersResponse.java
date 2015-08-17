package jp.co.paygent.maneco.data;

import java.util.Date;
import java.util.List;

import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@ToString
@JsonTypeInfo(use = Id.NONE)
@JsonPropertyOrder(
		value = {"customer_id",
				"object",
				"livemode",
				"created_time",
				"status",
				"email",
				"description",
				"card",
				"recursions"})
public class CustomersResponse extends EventData {

	@JsonProperty("customer_id")
	public final String customerId;
	public final boolean livemode;
	@JsonProperty("created_time")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo")
	public final Date createdTime;
	public final String status;
	public final String email;
	public final String description;
	public final CardResponse card;
	public final List<RecursionsResponse> recursions;

	public CustomersResponse(
			@JsonProperty("customer_id") String customerId,
			@JsonProperty("object") String object,
			@JsonProperty("livemode") boolean livemode,
			@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo")
			@JsonProperty("created_time") Date createdTime,
			@JsonProperty("status") String status,
			@JsonProperty("email") String email,
			@JsonProperty("description") String description,
			@JsonProperty("card") CardResponse card,
			@JsonProperty("recursions") List<RecursionsResponse> recursions
			) {
		super(object);
		this.customerId = customerId;
		this.livemode = livemode;
		this.createdTime = createdTime;
		this.status = status;
		this.email = email;
		this.description = description;
		this.card = card;
		this.recursions = recursions;
	}
}
