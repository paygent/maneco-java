package jp.co.paygent.maneco.data;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@ToString
public class RecursionsListRequest<O> extends RequestData<O> {

	private Integer count;
	private Integer offset;
	private String createdTimeFrom;
	private String createdTimeTo;
	private String customerId;
	private String status;

	public RecursionsListRequest() {
	}

    public RecursionsListRequest<O> count(Integer value) {
    	this.count = value;
        return this;
    }
    public RecursionsListRequest<O> offset(Integer value) {
    	this.offset = value;
        return this;
    }
    public RecursionsListRequest<O> createdTimeFrom(String value) {
    	this.createdTimeFrom = value;
        return this;
    }
    public RecursionsListRequest<O> createdTimeTo(String value) {
    	this.createdTimeTo = value;
        return this;
    }
    public RecursionsListRequest<O> customerId(String value) {
    	this.customerId = value;
        return this;
    }
    public RecursionsListRequest<O> status(String value) {
    	this.status = value;
        return this;
    }

	@Override
	public String pathTemplates() {
		return "/recursions";
	}

	@Override
    public MultivaluedMap<String, String> queryParams() {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>();

        if (count != null) map.add("count", count.toString());
        if (offset != null) map.add("offset", offset.toString());
        if (createdTimeFrom != null) map.add("created_time_from", createdTimeFrom);
        if (createdTimeTo != null) map.add("created_time_to", createdTimeTo);
        if (customerId != null) map.add("customer_id", customerId);
        if (status != null) map.add("status", status);

        return map;
    }

	@Override
	public Map<String, Object> urlKey() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return map;
	}

	public Integer getCount() {
		return count;
	}
	public Integer getOffset() {
		return offset;
	}
	@JsonProperty("created_time_from")
	public String getCreatedTimeFrom() {
		return createdTimeFrom;
	}
	@JsonProperty("created_time_to")
	public String getCreatedTimeTo() {
		return createdTimeTo;
	}
	@JsonProperty("customer_id")
	public String getCustomerId() {
		return customerId;
	}
	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

}
