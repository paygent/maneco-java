package jp.co.paygent.maneco;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jp.co.paygent.maneco.api.CardPayments;
import jp.co.paygent.maneco.api.Customers;
import jp.co.paygent.maneco.api.Events;
import jp.co.paygent.maneco.api.Payments;
import jp.co.paygent.maneco.api.Recursions;
import jp.co.paygent.maneco.data.ErrorResponse;
import jp.co.paygent.maneco.data.EventsResponse;
import jp.co.paygent.maneco.data.RequestData;
import jp.co.paygent.maneco.exception.ManecoException;
import jp.co.paygent.maneco.exception.ManecoResponseException;
import jp.co.paygent.maneco.exception.ManecoRuntimeException;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.internal.util.Base64;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class Maneco {

	public static final String API_VERSION = "1.0.0";

	public static final int DEFAULT_CONNECT_TIMEOUT_PERIOD = 35000;
	public static final int DEFAULT_READ_TIMEOUT_PERIOD    = 35000;

	public static final String DEFAULT_API_BASE_URL = "https://maneco.paygent.co.jp/maneco-api";


	private final Client       client;
	private final ObjectMapper mapper;

	public final Customers    customers;
	public final Payments     payments;
	public final CardPayments cardPayments;
	public final Recursions   recursions;
	public final Events       events;

	private String authorizationKey;
	private String apiBaseUrl;

	public Maneco(String authKey) {
		this(authKey, DEFAULT_CONNECT_TIMEOUT_PERIOD, DEFAULT_READ_TIMEOUT_PERIOD);
	}

	public Maneco(String authKey, int connectTimeout, int readTimeout) {

        SSLContext context = SslConfigurator.newInstance().createSSLContext();
        client = ClientBuilder.newBuilder().sslContext(context).build()
        				.property(ClientProperties.CONNECT_TIMEOUT, connectTimeout)
        				.property(ClientProperties.READ_TIMEOUT,    readTimeout);

		this.mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		this.customers    = new Customers(this);
		this.payments     = new Payments(this);
		this.cardPayments = new CardPayments(this);
		this.recursions   = new Recursions(this);
		this.events       = new Events(this);

        apiBaseUrl = DEFAULT_API_BASE_URL;

        if (authKey == null) authKey = "";
		this.authorizationKey = Base64.encodeAsString(authKey);

	}

	public void setConnectTimeout(int connectTimeout) {
		client.property(ClientProperties.CONNECT_TIMEOUT, connectTimeout);
	}

	public void setReadTimeout(int readTimeout) {
		client.property(ClientProperties.READ_TIMEOUT, readTimeout);
	}

	public void setBaseApiUrl(String url) {
		apiBaseUrl = url;
	}

	public <T> T get(RequestData<?> data, Class<T> responseType) throws ManecoException {

		WebTarget target = client.target(apiBaseUrl).path(data.pathTemplates()).resolveTemplates(data.urlKey());
        for (Map.Entry<String, List<String>> param : data.queryParams().entrySet()) {
            target = target.queryParam(param.getKey(), param.getValue().toArray());
        }

		Response response = null;
		try {
			response = setCommonClientParameter(target.request()).get();
		} catch (Exception e) {
			throw new ManecoRuntimeException(ManecoException.CONNECTION_ERROR, e);
		}
		return handleResponce(response, responseType);
	}

	public <T> T delete(RequestData<?> data, Class<T> responseType) throws ManecoException {

		WebTarget target = client.target(apiBaseUrl).path(data.pathTemplates()).resolveTemplates(data.urlKey());
        for (Map.Entry<String, List<String>> param : data.queryParams().entrySet()) {
            target = target.queryParam(param.getKey(), param.getValue().toArray());
        }

		Response response = null;
		try {
			response = setCommonClientParameter(target.request()).delete();
		} catch (Exception e) {
			throw new ManecoRuntimeException(ManecoException.CONNECTION_ERROR, e);
		}
		return handleResponce(response, responseType);
	}

	public <T> T post(RequestData<?> data, Class<T> responseType) throws ManecoException {

		WebTarget target = client.target(apiBaseUrl).path(data.pathTemplates()).resolveTemplates(data.urlKey());

		Response response = null;
		try {
			response = setCommonClientParameter(target.request())
							.post(Entity.entity(data.queryParams(), MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		} catch (Exception e) {
			throw new ManecoRuntimeException(ManecoException.CONNECTION_ERROR, e);
		}

		return handleResponce(response, responseType);
	}

	private Invocation.Builder setCommonClientParameter(Invocation.Builder builder) {
		return builder.header(HttpHeaders.AUTHORIZATION, authorizationKey)
					  .accept(MediaType.APPLICATION_JSON_TYPE)
					  .header(HttpHeaders.USER_AGENT, "maneco-connectmodule-java/" + API_VERSION);
	}

	private <T> T handleResponce(Response response, Class<T> responseType) throws ManecoException {
		int status = response.getStatus();
		if (status >= 400) {
			ErrorResponse errorData = bindResponse(response.readEntity(String.class), ErrorResponse.class);
			throw new ManecoResponseException(status, errorData);
		}
		return bindResponse(response.readEntity(String.class), responseType);
	}

    private <T> T bindResponse(String json, Class<T> responseType) {

		T res = null;
		try {
			res = mapper.readValue(json, responseType);
		} catch (IOException e) {
			throw new ManecoRuntimeException(ManecoException.JSON_ERROR,e);
		}

		return res;
    }

    public EventsResponse receiveWebhook(String requestBody) {
    	return bindResponse(requestBody, EventsResponse.class);
    }
}
