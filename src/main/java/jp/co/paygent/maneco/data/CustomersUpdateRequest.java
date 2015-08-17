package jp.co.paygent.maneco.data;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import jp.co.paygent.maneco.exception.ManecoException;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@ToString
public class CustomersUpdateRequest<O> extends RequestData<O> {

	private String id;

    private String card;
    private String cardNumber;
    private String cardExpireYear;
    private String cardExpireMonth;
    private String cardCvc;
    private String cardName;
    private String email;
    private String description;

    public CustomersUpdateRequest() {
    }

    public CustomersUpdateRequest<O> id(String value) {
    	this.id = value;
        return this;
    }

    public CustomersUpdateRequest<O> card(String value) {
    	card = value;
    	return this;
    }

    public CustomersUpdateRequest<O> cardNumber(String value) {
    	cardNumber = value;
    	return this;
    }

    public CustomersUpdateRequest<O> cardExpireYear(String value) {
    	cardExpireYear = value;
    	return this;
    }

    public CustomersUpdateRequest<O> cardExpireMonth(String value) {
    	cardExpireMonth = value;
    	return this;
    }

    public CustomersUpdateRequest<O> cardCvc(String value) {
    	cardCvc = value;
    	return this;
    }

    public CustomersUpdateRequest<O> cardName(String value) {
    	cardName = value;
    	return this;
    }

    public CustomersUpdateRequest<O> email(String value) {
    	email = value;
    	return this;
    }

    public CustomersUpdateRequest<O> description(String value) {
    	description = value;
    	return this;
    }


	@Override
	public String pathTemplates() {
		return "/customers/{id}";
	}

	@Override
    public MultivaluedMap<String, String> queryParams() {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>();

        if (card != null) map.add("card", card);
        if (cardNumber != null) map.add("card_number", cardNumber);
        if (cardExpireYear != null) map.add("card_expire_year", cardExpireYear);
        if (cardExpireMonth != null) map.add("card_expire_month", cardExpireMonth);
        if (cardCvc != null) map.add("card_cvc", cardCvc);
        if (cardName != null) map.add("card_name", cardName);
        if (email != null) map.add("email", email);
        if (description != null) map.add("description", description);

        return map;
    }

	@Override
	public Map<String, Object> urlKey() throws ManecoException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id == null) {
			throw new ManecoException(ManecoException.INPUT_ERROR, "Required field: id (customer_id)");
		}
		map.put("id", id);
		return map;
	}

	public String getId() {
		return id;
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
	public String getEmail() {
		return email;
	}
	public String getDescription() {
		return description;
	}
}
