package jp.co.paygent.maneco.data;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import jp.co.paygent.maneco.exception.ManecoException;
import lombok.ToString;

@ToString
public class EventsIdRequest<O> extends RequestData<O> {

	private String id;

	public EventsIdRequest() {
	}

    public EventsIdRequest<O> id(String value) {
    	this.id = value;
        return this;
    }

	@Override
	public String pathTemplates() {
		return "/events/{id}";
	}

	@Override
    public MultivaluedMap<String, String> queryParams() {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>();
        return map;
    }

	@Override
	public Map<String, Object> urlKey() throws ManecoException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id == null) {
			throw new ManecoException(ManecoException.INPUT_ERROR, "Required field: id (event_id)");
		}
		map.put("id", id);
		return map;
	}

	public String getId() {
		return id;
	}

}
