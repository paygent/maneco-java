package jp.co.paygent.maneco.data;

import java.math.BigDecimal;
import java.util.Date;

import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@ToString
@JsonPropertyOrder(
		value = {"object",
				"type",
				"rate",
				"amount",
				"created_time"})
public class FeeResponse extends ResponseData {

	public final String type;
	public final BigDecimal rate;
	public final BigDecimal amount;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo")
	@JsonProperty("created_time")
	public final Date createdTime;

	public FeeResponse(
			@JsonProperty("object") String object,
			@JsonProperty("type") String type,
			@JsonProperty("rate") BigDecimal rate,
			@JsonProperty("amount") BigDecimal amount,
			@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo") @JsonProperty("created_time") Date createdTime
			) {
		super(object);
		this.type = type;
		this.rate = rate;
		this.amount = amount;
		this.createdTime = createdTime;
	}
}
