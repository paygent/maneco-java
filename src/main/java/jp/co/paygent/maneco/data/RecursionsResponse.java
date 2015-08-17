package jp.co.paygent.maneco.data;

import java.math.BigDecimal;
import java.util.Date;

import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@ToString
@JsonTypeInfo(use = Id.NONE)
@JsonPropertyOrder({
	"recursion_id",
	"object",
	"livemode",
	"created_time",
	"status",
	"amount",
	"currency",
	"cycle",
	"timing",
	"first_executed",
	"last_executed",
	"next_scheduled",
	"resumable_until",
	"end_scheduled",
	"end",
	"description",
	"customer_id",
	"error_cd",
	"error_msg"
})
public class RecursionsResponse extends EventData {

	@JsonProperty("recursion_id")
	public final String recursionId;
	public final String livemode;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo") @JsonProperty("created_time")
	public final Date createdTime;
	public final String status;
	public final BigDecimal amount;
	public final String currency;
	public final String cycle;
	public final String timing;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Tokyo") @JsonProperty("first_executed")
	public final Date firstExecuted;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Tokyo") @JsonProperty("last_executed")
	public final Date lastExecuted;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Tokyo") @JsonProperty("next_scheduled")
	public final Date nextScheduled;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Tokyo") @JsonProperty("resumable_until")
	public final Date resumableUntil;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Tokyo") @JsonProperty("end_scheduled")
	public final Date endScheduled;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo")
	public final Date end;
	public final String description;
	@JsonProperty("customer_id")
	public final String customerId;
	@JsonProperty("error_cd")
	public final String errorCd;
	@JsonProperty("error_msg")
	public final String errorMsg;

	public RecursionsResponse(
			@JsonProperty("recursion_id") String recursionId,
			@JsonProperty("object") String object,
			@JsonProperty("livemode") String livemode,
			@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo") @JsonProperty("created_time") Date createdTime,
			@JsonProperty("status") String status,
			@JsonProperty("amount") BigDecimal amount,
			@JsonProperty("currency") String currency,
			@JsonProperty("cycle") String cycle,
			@JsonProperty("timing") String timing,
			@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Tokyo") @JsonProperty("first_executed") Date firstExecuted,
			@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Tokyo") @JsonProperty("last_executed") Date lastExecuted,
			@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Tokyo") @JsonProperty("next_scheduled") Date nextScheduled,
			@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Tokyo") @JsonProperty("resumable_until") Date resumableUntil,
			@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Tokyo") @JsonProperty("end_scheduled") Date endScheduled,
			@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo") @JsonProperty("end") Date end,
			@JsonProperty("description") String description,
			@JsonProperty("customer_id") String customerId,
			@JsonProperty("error_cd") String errorCd,
			@JsonProperty("error_msg") String errorMsg
			) {
		super(object);
		this.recursionId = recursionId;
		this.livemode = livemode;
		this.createdTime = createdTime;
		this.status = status;
		this.amount = amount;
		this.currency = currency;
		this.cycle = cycle;
		this.timing = timing;
		this.firstExecuted = firstExecuted;
		this.lastExecuted = lastExecuted;
		this.nextScheduled = nextScheduled;
		this.resumableUntil = resumableUntil;
		this.endScheduled = endScheduled;
		this.end = end;
		this.description = description;
		this.customerId = customerId;
		this.errorCd = errorCd;
		this.errorMsg = errorMsg;
	}

}
