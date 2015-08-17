package jp.co.paygent.maneco.data;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import jp.co.paygent.maneco.exception.ManecoException;
import lombok.ToString;

@ToString
public class CardPaymentsRefundRequest<O> extends RequestData<O> {

	private String id;

	private Long amount;

	public CardPaymentsRefundRequest() {
	}

    public CardPaymentsRefundRequest<O> id(String value) {
    	this.id = value;
        return this;
    }

    public CardPaymentsRefundRequest<O> amount(Long value) {
    	amount = value;
    	return this;
    }
    public CardPaymentsRefundRequest<O> amount(Integer value) {
    	amount = value.longValue();
    	return this;
    }


	@Override
	public MultivaluedMap<String, String> queryParams() {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>();

        if (amount != null) map.add("amount", amount.toString());

        return map;
	}

	@Override
	public Map<String, Object> urlKey() throws ManecoException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id == null) {
			throw new ManecoException(ManecoException.INPUT_ERROR, "Required field: id (payment_id)");
		}
		map.put("id", id);
		return map;
	}

	@Override
	public String pathTemplates() {
		return "/card-payments/{id}/refund";
	}

	public String getId() {
		return id;
	}
	public Long getAmount() {
		return amount;
	}
}
