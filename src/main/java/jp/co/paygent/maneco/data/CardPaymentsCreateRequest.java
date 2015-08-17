package jp.co.paygent.maneco.data;


import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@ToString
public class CardPaymentsCreateRequest<O> extends RequestData<O> {

	private Long amount;
    private String card;
    private String cardNumber;
    private String cardExpireYear;
    private String cardExpireMonth;
    private String cardCvc;
    private String cardName;
    private String customerId;
    private Boolean capture;
    private String currency;
    private String description;
    private Integer expireDays;

    public CardPaymentsCreateRequest() {
    }


    public CardPaymentsCreateRequest<O> amount(Long value) {
    	amount = value;
    	return this;
    }
    public CardPaymentsCreateRequest<O> amount(Integer value) {
    	amount = value.longValue();
    	return this;
    }

    public CardPaymentsCreateRequest<O> card(String value) {
    	card = value;
    	return this;
    }

    public CardPaymentsCreateRequest<O> cardNumber(String value) {
    	cardNumber = value;
    	return this;
    }

    public CardPaymentsCreateRequest<O> cardExpireYear(String value) {
    	cardExpireYear = value;
    	return this;
    }

    public CardPaymentsCreateRequest<O> cardExpireMonth(String value) {
    	cardExpireMonth = value;
    	return this;
    }

    public CardPaymentsCreateRequest<O> cardCvc(String value) {
    	cardCvc = value;
    	return this;
    }

    public CardPaymentsCreateRequest<O> cardName(String value) {
    	cardName = value;
    	return this;
    }

    public CardPaymentsCreateRequest<O> customerId(String value) {
    	customerId = value;
    	return this;
    }

    public CardPaymentsCreateRequest<O> capture(Boolean value) {
    	capture = value;
    	return this;
    }

    public CardPaymentsCreateRequest<O> currency(String value) {
    	currency = value;
    	return this;
    }

    public CardPaymentsCreateRequest<O> description(String value) {
    	description = value;
    	return this;
    }

    public CardPaymentsCreateRequest<O> expireDays(Integer value) {
    	expireDays = value;
    	return this;
    }

	@Override
	public String pathTemplates() {
		return "/card-payments";
	}

	@Override
    public MultivaluedMap<String, String> queryParams() {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>();

        if (amount != null) map.add("amount", amount.toString());
        if (card != null) map.add("card", card);
        if (cardNumber != null) map.add("card_number", cardNumber);
        if (cardExpireYear != null) map.add("card_expire_year", cardExpireYear);
        if (cardExpireMonth != null) map.add("card_expire_month", cardExpireMonth);
        if (cardCvc != null) map.add("card_cvc", cardCvc);
        if (cardName != null) map.add("card_name", cardName);
        if (customerId != null) map.add("customer_id", customerId);
        if (capture != null) map.add("capture", capture.toString());
        if (currency != null) map.add("currency", currency);
        if (description != null) map.add("description", description);
        if (expireDays != null) map.add("expire_days", expireDays.toString());


        return map;
    }

	@Override
	public Map<String, Object> urlKey() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return map;
	}

	public Long getAmount() {
		return amount;
	}
	public String getCard() {
		return card;
	}
	@JsonProperty("card_number")
	public String getCardNumber() {
		return cardNumber;
	}
	@JsonProperty("card_expire_year")
	public String getCardExpireYear() {
		return cardExpireYear;
	}
	@JsonProperty("card_expire_month")
	public String getCardExpireMonth() {
		return cardExpireMonth;
	}
	@JsonProperty("card_cvc")
	public String getCardCvc() {
		return cardCvc;
	}
	@JsonProperty("card_name")
	public String getCardName() {
		return cardName;
	}
	@JsonProperty("customer_id")
	public String getCustomerId() {
		return customerId;
	}
	public Boolean getCapture() {
		return capture;
	}
	public String getCurrency() {
		return currency;
	}
	public String getDescription() {
		return description;
	}
	@JsonProperty("expire_days")
	public Integer getExpireDays() {
		return expireDays;
	}

}
