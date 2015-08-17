package jp.co.paygent.maneco.data;

import java.util.Date;

import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@ToString
@JsonPropertyOrder(
		value={"object",
				"expire_year",
				"expire_month",
				"fingerprint",
				"name",
				"brand",
				"last4_digits",
				"created_time"})
public class CardResponse extends ResponseData {

	@JsonProperty("expire_year")
	public final String expireYear;
	@JsonProperty("expire_month")
	public final String expireMonth;
	public final String fingerprint;
	public final String name;
	public final String brand;
	@JsonProperty("last4_digits")
	public final String last4Digits;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo")
	@JsonProperty("created_time")
	public final Date createdTime;

	public CardResponse(
			@JsonProperty("object") String object,
			@JsonProperty("expire_year") String expire_year,
			@JsonProperty("expire_month") String expire_month,
			@JsonProperty("fingerprint") String fingerprint,
			@JsonProperty("name") String name,
			@JsonProperty("brand") String brand,
			@JsonProperty("last4_digits") String last4_digits,
			@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo") @JsonProperty("created_time") Date created_time
			) {
		super(object);
		this.expireYear = expire_year;
		this.expireMonth = expire_month;
		this.fingerprint = fingerprint;
		this.name = name;
		this.brand = brand;
		this.last4Digits = last4_digits;
		this.createdTime = created_time;
	}
}
