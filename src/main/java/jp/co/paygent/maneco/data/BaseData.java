package jp.co.paygent.maneco.data;

import jp.co.paygent.maneco.exception.ManecoException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public abstract class BaseData {

	public String toJsonString(boolean setIndent) throws ManecoException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			if (setIndent) mapper.enable(SerializationFeature.INDENT_OUTPUT);;
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new ManecoException(ManecoException.JSON_ERROR, e);
		}
	}

}
