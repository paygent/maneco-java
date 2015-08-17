package jp.co.paygent.maneco.data;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import jp.co.paygent.maneco.exception.ManecoException;
import lombok.ToString;

@ToString
public class RecursionsResumeRequest<O> extends RequestData<O> {
	private String id;

	private Boolean retry;

	public RecursionsResumeRequest() {
	}

    public RecursionsResumeRequest<O> id(String value) {
    	this.id = value;
        return this;
    }
    public RecursionsResumeRequest<O> retry(Boolean value) {
    	this.retry = value;
        return this;
    }

	@Override
	public MultivaluedMap<String, String> queryParams() {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>();

        if (retry != null) map.add("retry", retry.toString());

        return map;
	}

	@Override
	public Map<String, Object> urlKey() throws ManecoException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (id == null) {
			throw new ManecoException(ManecoException.INPUT_ERROR, "Required field: id (recursion_id)");
		}
		map.put("id", id);
		return map;
	}

	@Override
	public String pathTemplates() {
		return "/recursions/{id}/resume";
	}

	public String getId() {
		return id;
	}
	public Boolean getRetry() {
		return retry;
	}
}
