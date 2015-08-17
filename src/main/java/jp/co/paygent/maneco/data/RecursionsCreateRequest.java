package jp.co.paygent.maneco.data;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@ToString
public class RecursionsCreateRequest<O> extends RequestData<O> {

    private String customerId;
	private Long amount;
    private String currency;
    private String cycle;
    private String timing;
	private String firstExecuted;
	private String endScheduled;
	private String description;


	public RecursionsCreateRequest() {
	}

    public RecursionsCreateRequest<O> customerId(String value) {
    	customerId = value;
    	return this;
    }

    public RecursionsCreateRequest<O> amount(Long value) {
    	amount = value;
    	return this;
    }
    public RecursionsCreateRequest<O> amount(Integer value) {
    	amount = value.longValue();
    	return this;
    }

	public RecursionsCreateRequest<O> currency(String value) {
		this.currency = value;
		return this;
	}

	public RecursionsCreateRequest<O> cycle(String value) {
		this.cycle = value;
		return this;
	}

	public RecursionsCreateRequest<O> timing(String value) {
		this.timing = value;
		return this;
	}

	public RecursionsCreateRequest<O> firstExecuted(String value) {
		this.firstExecuted = value;
		return this;
	}

	public RecursionsCreateRequest<O> endScheduled(String value) {
		this.endScheduled = value;
		return this;
	}

	public RecursionsCreateRequest<O> description(String value) {
		this.description = value;
		return this;
	}


	@Override
	public String pathTemplates() {
		return "/recursions";
	}

	@Override
    public MultivaluedMap<String, String> queryParams() {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>();

        if (customerId != null) map.add("customer_id", customerId);
        if (amount != null) map.add("amount", amount.toString());
        if (currency != null) map.add("currency", currency);
        if (cycle != null) map.add("cycle", cycle);
        if (timing != null) map.add("timing", timing);
        if (firstExecuted != null) map.add("first_executed", firstExecuted);
        if (endScheduled != null) map.add("end_scheduled", endScheduled);
        if (description != null) map.add("description", description);

        return map;
	}

	@Override
	public Map<String, Object> urlKey() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return map;
	}

	@JsonProperty("customer_id")
	public String getCustomerId() {
		return customerId;
	}
	public Long getAmount() {
		return amount;
	}
	public String getCurrency() {
		return currency;
	}
	public String getCycle() {
		return cycle;
	}
	public String getTiming() {
		return timing;
	}
	@JsonProperty("first_executed")
	public String getFirstExecuted() {
		return firstExecuted;
	}
	@JsonProperty("end_scheduled")
	public String getEndScheduled() {
		return endScheduled;
	}
	public String getDescription() {
		return description;
	}
}
