package jp.co.paygent.maneco;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Link.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import jp.co.paygent.maneco.Maneco;
import jp.co.paygent.maneco.exception.ManecoException;
import jp.co.paygent.maneco.exception.ManecoResponseException;
import jp.co.paygent.maneco.exception.ManecoRuntimeException;
import mockit.Mock;
import mockit.MockUp;

import org.glassfish.jersey.client.JerseyInvocation;
import org.junit.Test;

public class CommunicationErrorTest {

	@Test
	public void test_HTTPStatus500応答かつエラーJSON構築に失敗する場合_Mocked(){
		new MockUp<JerseyInvocation>() {
			@Mock
			public Response invoke() {
				return new DummyResponse(500, "{\"object\": \"error\",");
			}
		};

		Maneco api = new Maneco("omiya");
		api.setBaseApiUrl("http://localhost:8080/maneco-api");

		try {
			api.payments.retrieve("TEST");
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			assertTrue("GET：異常終了", true);
		} catch (ManecoException e) {
			fail();
		}

		try {
			api.customers.createRequest().execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			assertTrue("POST：異常終了", true);
		} catch (ManecoException e) {
			fail();
		}

		try {
			api.customers.deleteRequest().id("TEST").execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			assertTrue("DELETE：異常終了", true);
		} catch (ManecoException e) {
			fail();
		}
	}

	@Test
	public void test_JSONが完全ではない応答を戻した場合_Mocked(){
		new MockUp<JerseyInvocation>() {
			@Mock
			public Response invoke() {
				return new DummyResponse(500, "{\"payment_id\": \"pay_tiCtXcuMtla1\",\"object\": \"card-payment\",\"livemode\": true,\"create_time\": \"20150421173704\",\"status\": \"failed\",\"amount\": 1000");
			}
		};

		Maneco api = new Maneco("omiya");
		api.setBaseApiUrl("http://localhost:8080/maneco-api");

		try {
			api.payments.retrieve("TEST");
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			assertThat("GET：異常終了", e.getCode(), is(ManecoException.JSON_ERROR));
		} catch (ManecoException e) {
			fail();
		}

		try {
			api.customers.createRequest().execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			assertThat("POST：異常終了", e.getCode(), is(ManecoException.JSON_ERROR));
		} catch (ManecoException e) {
			fail();
		}

		try {
			api.customers.deleteRequest().id("TEST").execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			assertThat("DELETE：異常終了", e.getCode(), is(ManecoException.JSON_ERROR));
		} catch (ManecoException e) {
			fail();
		}
	}

	@Test
	public void test_タイムアウト_リードタイムアウトを1ミリ秒に設定して実施(){

		Maneco api = new Maneco("omiya",35000,1);
		api.setBaseApiUrl("http://localhost:8080/maneco-api");

		try {
			api.payments.retrieve("TEST");
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			assertThat("GET：異常終了", e.getCode(), is(ManecoException.CONNECTION_ERROR));
		} catch (ManecoException e) {
			fail();
		}

		try {
			api.customers.createRequest().execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			assertThat("POST：異常終了", e.getCode(), is(ManecoException.CONNECTION_ERROR));
		} catch (ManecoException e) {
			fail();
		}

		api = new Maneco("omiya");
		api.setBaseApiUrl("http://localhost:8080/maneco-api");
		api.setConnectTimeout(1);
		api.setReadTimeout(1);

		try {
			api.customers.deleteRequest().id("TEST").execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			assertThat("DELETE：異常終了", e.getCode(), is(ManecoException.CONNECTION_ERROR));
		} catch (ManecoException e) {
			fail();
		}
	}

	@Test
	public void test_URLが異なっている(){


		Maneco api = new Maneco("omiya");

		api.setBaseApiUrl("http://www.google.com/ja");


		try {
			api.payments.retrieve("TEST");
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			assertThat("GET：異常終了", e.getCode(), is(ManecoException.JSON_ERROR));
		} catch (ManecoException e) {
			fail();
		}

		try {
			api.customers.createRequest().execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			assertThat("POST：異常終了", e.getCode(), is(ManecoException.JSON_ERROR));
		} catch (ManecoException e) {
			fail();
		}

		try {
			api.customers.deleteRequest().id("TEST").execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			assertThat("DELETE：異常終了", e.getCode(), is(ManecoException.JSON_ERROR));
		} catch (ManecoException e) {
			fail();
		}
	}

	@Test
	public void test_エラーレスポンス応答() {

		Maneco api = new Maneco("omiya");
		api.setBaseApiUrl("http://localhost:8080/maneco-api");

		try {
			api.payments.retrieve("NonExistID");
			fail();
		} catch (ManecoResponseException e) {
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("Object    : " + e.getErrorResponse().object);
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getMessage());
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertThat("エラーレスポンス", e.getCode(), is("NF00001"));
		} catch (ManecoRuntimeException e) {
			fail();
		} catch (ManecoException e) {
			fail();
		}

	}

	@Test
	public void test_URLキー未設定() {

		Maneco api = new Maneco("omiya");
		api.setBaseApiUrl("http://localhost:8080/maneco-api");

		try {
			api.cardPayments.captureRequest().execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			fail();
		} catch (ManecoException e) {
			if (!e.getCode().equals(ManecoException.INPUT_ERROR)) fail();
		}

		try {
			api.cardPayments.refundRequest().execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			fail();
		} catch (ManecoException e) {
			if (!e.getCode().equals(ManecoException.INPUT_ERROR)) fail();
		}

		try {
			api.customers.updateRequest().execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			fail();
		} catch (ManecoException e) {
			if (!e.getCode().equals(ManecoException.INPUT_ERROR)) fail();
		}

		try {
			api.customers.deleteRequest().execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			fail();
		} catch (ManecoException e) {
			if (!e.getCode().equals(ManecoException.INPUT_ERROR)) fail();
		}

		try {
			api.customers.retrieveRequest().execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			fail();
		} catch (ManecoException e) {
			if (!e.getCode().equals(ManecoException.INPUT_ERROR)) fail();
		}

		try {
			api.recursions.resumeRequest().execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			fail();
		} catch (ManecoException e) {
			if (!e.getCode().equals(ManecoException.INPUT_ERROR)) fail();
		}

		try {
			api.recursions.deleteRequest().execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			fail();
		} catch (ManecoException e) {
			if (!e.getCode().equals(ManecoException.INPUT_ERROR)) fail();
		}

		try {
			api.recursions.retrieveRequest().execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			fail();
		} catch (ManecoException e) {
			if (!e.getCode().equals(ManecoException.INPUT_ERROR)) fail();
		}

		try {
			api.payments.retrieveRequest().execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			fail();
		} catch (ManecoException e) {
			if (!e.getCode().equals(ManecoException.INPUT_ERROR)) fail();
		}

		try {
			api.events.retrieveRequest().execute();
			fail();
		} catch (ManecoResponseException e) {
			fail();
		} catch (ManecoRuntimeException e) {
			fail();
		} catch (ManecoException e) {
			if (!e.getCode().equals(ManecoException.INPUT_ERROR)) fail();
		}

	}

	public static class DummyResponse extends Response {

		String response;
		int httpStatus;

		public DummyResponse(int status,String res) {
			response = res;
			httpStatus = status;
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T> T readEntity(Class<T> entityType) throws ProcessingException,IllegalStateException {
			return (T) response;
		}

		@Override
		public int getStatus() {
			return httpStatus;
		}

		@Override
		public <T> T readEntity(GenericType<T> paramGenericType,
				Annotation[] paramArrayOfAnnotation) {
			return null;
		}

		@Override
		public <T> T readEntity(Class<T> paramClass,
				Annotation[] paramArrayOfAnnotation) {
			return null;
		}

		@Override
		public <T> T readEntity(GenericType<T> paramGenericType) {
			return null;
		}

		@Override
		public boolean hasLink(String paramString) {
			return false;
		}

		@Override
		public boolean hasEntity() {
			return false;
		}

		@Override
		public MultivaluedMap<String, String> getStringHeaders() {
			return null;
		}

		@Override
		public StatusType getStatusInfo() {
			return null;
		}

		@Override
		public MultivaluedMap<String, Object> getMetadata() {
			return null;
		}

		@Override
		public MediaType getMediaType() {
			return null;
		}

		@Override
		public URI getLocation() {
			return null;
		}

		@Override
		public Set<Link> getLinks() {
			return null;
		}

		@Override
		public Builder getLinkBuilder(String paramString) {
			return null;
		}

		@Override
		public Link getLink(String paramString) {
			return null;
		}

		@Override
		public int getLength() {
			return 0;
		}

		@Override
		public Date getLastModified() {
			return null;
		}

		@Override
		public Locale getLanguage() {
			return null;
		}

		@Override
		public String getHeaderString(String paramString) {
			return null;
		}

		@Override
		public EntityTag getEntityTag() {
			return null;
		}

		@Override
		public Object getEntity() {
			return null;
		}

		@Override
		public Date getDate() {
			return null;
		}

		@Override
		public Map<String, NewCookie> getCookies() {
			return null;
		}

		@Override
		public Set<String> getAllowedMethods() {
			return null;
		}

		@Override
		public void close() {
		}

		@Override
		public boolean bufferEntity() {
			return false;
		}

	}

	private String arrayToStr(String[] array) {
		if (array.length == 0) return "";

		StringBuilder sb = new StringBuilder("[");
		for (String str : array) {
			sb.append(str).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");

		return sb.toString();
	}

}
