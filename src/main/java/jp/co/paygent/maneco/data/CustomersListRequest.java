package jp.co.paygent.maneco.data;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@ToString
public class CustomersListRequest<O> extends RequestData<O> {

	private Integer count;
	private Integer offset;
	private String createdTimeFrom;
	private String createdTimeTo;

	public CustomersListRequest() {
	}

    public CustomersListRequest<O> count(Integer value) {
    	this.count = value;
        return this;
    }
    public CustomersListRequest<O> offset(Integer value) {
    	this.offset = value;
        return this;
    }
    public CustomersListRequest<O> createdTimeFrom(String value) {
    	this.createdTimeFrom = value;
        return this;
    }
    public CustomersListRequest<O> createdTimeTo(String value) {
    	this.createdTimeTo = value;
        return this;
    }

	@Override
	public String pathTemplates() {
		return "/customers";
	}

	@Override
    public MultivaluedMap<String, String> queryParams() {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>();

        if (count != null) map.add("count", count.toString());
        if (offset != null) map.add("offset", offset.toString());
        if (createdTimeFrom != null) map.add("created_time_from", createdTimeFrom);
        if (createdTimeTo != null) map.add("created_time_to", createdTimeTo);

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
}
