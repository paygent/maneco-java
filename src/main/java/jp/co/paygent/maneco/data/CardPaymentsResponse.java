package jp.co.paygent.maneco.data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@ToString
@JsonTypeInfo(use = Id.NONE)
@JsonPropertyOrder(
		value={"payment_id",
				"object",
				"livemode",
				"create_time",
				"status",
				"amount",
				"currency",
				"customer_id",
				"recursion_id",
				"description",
				"authorized_until",
				"refunded_amount",
				"refundable_until",
				"card",
				"refunds",
				"fees"})
public class CardPaymentsResponse extends PaymentsResponse {

	@JsonProperty("payment_id")
	public final String paymentId;
	public final boolean livemode;
	@JsonProperty("create_time")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo")
	public final Date createTime;
	public final String status;
	public final BigDecimal amount;
	public final String currency;
	@JsonProperty("customer_id")
	public final String customerId;
	@JsonProperty("recursion_id")
	public final String recursionId;
	public final String description;
	@JsonProperty("authorized_until")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Tokyo")
	public final Date authorizedUntil;
	@JsonProperty("refunded_amount")
	public final BigDecimal refundedAmount;
	@JsonProperty("refundable_until")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Tokyo")
	public final Date refundableUntil;
	public final CardResponse card;
	public final List<RefundResponse> refunds;
	public final List<FeeResponse> fees;


	@JsonCreator
	public CardPaymentsResponse(
			@JsonProperty("object") String object,
			@JsonProperty("payment_id") String paymentId,
			@JsonProperty("livemode") boolean livemode,
			@JsonProperty("create_time") @JsonFormat(shape = Shape.STRING, pattern = "yyyyMMddHHmmss", timezone = "Asia/Tokyo") Date createTime,
			@JsonProperty("status") String status,
			@JsonProperty("amount") BigDecimal amount,
			@JsonProperty("currency") String currency,
			@JsonProperty("customer_id") String customerId,
			@JsonProperty("recursion_id") String recursionId,
			@JsonProperty("description") String description,
			@JsonProperty("authorized_until") @JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Tokyo") Date authorizedUntil,
			@JsonProperty("refunded_amount") BigDecimal refundedAmount,
			@JsonProperty("refundable_until") @JsonFormat(shape = Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Tokyo") Date refundableUntil,
			@JsonProperty("card") CardResponse card,
			@JsonProperty("refunds") List<RefundResponse> refunds,
			@JsonProperty("fees") List<FeeResponse> fees) {
		super(object);
		this.paymentId = paymentId;
		this.livemode = livemode;
		this.createTime = createTime;
		this.status = status;
		this.amount = amount;
		this.currency = currency;
		this.customerId = customerId;
		this.recursionId = recursionId;
		this.description = description;
		this.authorizedUntil = authorizedUntil;
		this.refundedAmount = refundedAmount;
		this.refundableUntil = refundableUntil;
		this.card = card;
		this.refunds = refunds;
		this.fees = fees;
	}
}
