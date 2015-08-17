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
				"amount",
				"created_time"})
public class RefundResponse extends ResponseData {

	public final BigDecimal amount;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo")
	@JsonProperty("created_time")
	public final Date createdTime;

	public RefundResponse (
			@JsonProperty("object") String object,
			@JsonProperty("amount") BigDecimal amount,
			@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo") @JsonProperty("created_time") Date createdTime
			) {
		super(object);
		this.createdTime = createdTime;
		this.amount = amount;
	}
}
