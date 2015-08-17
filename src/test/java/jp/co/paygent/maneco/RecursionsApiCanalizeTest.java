package jp.co.paygent.maneco;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.core.Response;

import jp.co.paygent.maneco.Maneco;
import jp.co.paygent.maneco.data.RecursionsListResponse;
import jp.co.paygent.maneco.data.RecursionsResponse;
import jp.co.paygent.maneco.exception.ManecoException;
import jp.co.paygent.maneco.exception.ManecoResponseException;
import jp.co.paygent.maneco.exception.ManecoRuntimeException;
import mockit.Mock;
import mockit.MockUp;

import org.glassfish.jersey.client.JerseyInvocation;
import org.junit.Test;

public class RecursionsApiCanalizeTest {

	@Test
	public void test_継続課金照会_複数件() {

		Maneco api = new Maneco("omiya");
		api.setBaseApiUrl("http://localhost:8080/maneco-api");

		System.out.println("*======================================================*");
		System.out.println("* 継続課金照会(複数件)");
		System.out.println("*======================================================*");

		try {
			RecursionsListResponse res = api.recursions.allRequest()
													   .count(20)
//													   .offset(2)
													   .createdTimeFrom("20150301")
													   .createdTimeTo("20150501")
//													   .customerId("cus_dummy0123456")
//													   .status("valid")
													   .execute();
			System.out.println("Result:" + res.result);
			for (RecursionsResponse recursion : res.data) {
				System.out.println(recursion.toJsonString(false));
			}

			assertTrue("継続課金照会(複数件)完了", true);

		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("継続課金照会(複数件)：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("継続課金照会(複数件)：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("継続課金照会(複数件)：異常終了", false);
		}
	}

	@Test
	public void test_継続課金の作成・照会・削除() {

		Maneco api = new Maneco("omiya");
		api.setBaseApiUrl("http://localhost:8080/maneco-api");

		String recursionId = "";

		System.out.println("*======================================================*");
		System.out.println("* 継続課金作成");
		System.out.println("*======================================================*");

		try {
			RecursionsResponse res = api.recursions.createRequest()
																   .customerId("cus_uk9erGCz9njO")
																   .amount(1000)
																   .currency("JPY")
																   .cycle("3")
																   .timing("29")
																   .firstExecuted((new SimpleDateFormat("yyyyMMdd")).format(Calendar.getInstance().getTime()))
																   .endScheduled("20151231")
																   .description("接続モジュールでの試行")
																   .execute();

			System.out.println("ResponceObject:" + res.toJsonString(true));
			recursionId = res.recursionId;

			assertTrue("継続課金作成完了", true);

		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("継続課金作成：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("継続課金作成：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("継続課金作成：異常終了", false);
		}

		System.out.println("*======================================================*");
		System.out.println("* 継続課金照会(1件)");
		System.out.println("*======================================================*");

		try {
			RecursionsResponse res = api.recursions.retrieveRequest().id(recursionId).execute();
			System.out.println("Responce:" + res.toJsonString(true));

			RecursionsResponse res2 = api.recursions.retrieve(recursionId);
			System.out.println("Responce:" + res2.toJsonString(true));

			assertTrue("継続課金照会(1件)完了", true);

		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("継続課金照会(1件)：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("継続課金照会(1件)：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("継続課金照会(1件)：異常終了", false);
		}

		System.out.println("*======================================================*");
		System.out.println("* 継続課金削除");
		System.out.println("*======================================================*");
		try {
			RecursionsResponse res = api.recursions.deleteRequest().id(recursionId).execute();

			System.out.println("ResponceObject:" + res.toJsonString(true));

			assertTrue("継続課金削除完了", true);

		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("継続課金削除：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("継続課金削除：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("継続課金削除：異常終了", false);
		}

	}

	@Test
	public void test_継続課金の再開_Mocked() {
		new MockUp<JerseyInvocation>() {
			@Mock
			public Response invoke() {
				return new CommunicationErrorTest.DummyResponse(200, "{\"recursion_id\": \"rec_gRmxHexmJLOx\",\"object\": \"recursion\",\"livemode\": true,\"created_time\": \"20150520113219\",\"status\": \"valid\",\"amount\": 1000,\"currency\": \"JPY\",\"cycle\": \"3\",\"timing\": \"29\",\"first_executed\": \"20150520\",\"last_executed\": \"20150520\",\"next_scheduled\": \"20150831\",\"resumable_until\": null,\"end_scheduled\": \"20151231\",\"end\": null,\"description\": \"イベント発生日修正の確認\",\"customer_id\": \"cus_Ye8BtmY0o0mC\",\"error_cd\": null,\"error_msg\": null}");
			}
		};

		Maneco api = new Maneco("omiya");
		api.setBaseApiUrl("http://localhost:8080/maneco-api");

		System.out.println("*======================================================*");
		System.out.println("* 継続課金再開");
		System.out.println("*======================================================*");
		try {
			RecursionsResponse res = api.recursions.resumeRequest().id("rec_L5c1sQgBP03V")
																   .retry(true)
																   .execute();

			System.out.println(res.toJsonString(true));

		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			fail();
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			fail();
		} catch (ManecoException e) {
			e.printStackTrace();
			fail();
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
