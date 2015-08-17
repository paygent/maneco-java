package jp.co.paygent.maneco;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import jp.co.paygent.maneco.Maneco;
import jp.co.paygent.maneco.data.EventsListResponse;
import jp.co.paygent.maneco.data.EventsResponse;
import jp.co.paygent.maneco.exception.ManecoException;
import jp.co.paygent.maneco.exception.ManecoResponseException;
import jp.co.paygent.maneco.exception.ManecoRuntimeException;

import org.junit.Test;

public class EventsApiCanalizeTest {

	@Test
	public void test_イベント照会() {

		Maneco api = new Maneco("omiya");
		api.setBaseApiUrl("http://localhost:8080/maneco-api");

		System.out.println("*======================================================*");
		System.out.println("* イベント照会(1件)");
		System.out.println("*======================================================*");

		try {
			EventsResponse res = api.events.retrieveRequest().id("evt_uM4GP1JxPngQ").execute();
			System.out.println("Response:"+res.toJsonString(true));

			EventsResponse res2 = api.events.retrieve("evt_uM4GP1JxPngQ");
			System.out.println("Response:"+res2.toJsonString(true));

			assertTrue("イベント照会(1件)完了", true);

		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getMessage());
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("イベント照会(1件)：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("イベント照会(1件)：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("イベント照会(1件)：異常終了", false);
		}

		System.out.println("*======================================================*");
		System.out.println("* イベント照会(複数件)");
		System.out.println("*======================================================*");

		try {
			EventsListResponse res = api.events.allRequest()
													.count(20)
													.offset(2)
													.createdTimeFrom("20150401")
													.createdTimeTo((new SimpleDateFormat("yyyyMMdd")).format(Calendar.getInstance().getTime()))
													.type("card.captured")
													.execute();
			System.out.println("Object:" + res.object);
			System.out.println("Result:" + res.result);
			System.out.println("Data:");
			for (EventsResponse event : res.data) {
				System.out.println(event.toJsonString(false));
			}

			assertTrue("イベント照会(複数件)完了", true);

		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("イベント照会(複数件)：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("イベント照会(複数件)：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("イベント照会(複数件)：異常終了", false);
		}

		System.out.println("*======================================================*");
		System.out.println("* Webhook受信");
		System.out.println("*======================================================*");

		try {
			EventsResponse noticeEvent = api.receiveWebhook("{\"event_id\":\"evt_xc4NDRNZBX7k\",\"object\":\"event\",\"livemode\":true,\"created_time\":\"20150522103458\",\"type\":\"card.succeeded\",\"detail\":{\"payment_id\":\"pay_S4G18HBgfijD\",\"object\":\"card-payment\",\"livemode\":true,\"create_time\":\"20150522103458\",\"status\":\"authorized\",\"amount\":1000,\"currency\":\"JPY\",\"customer_id\":null,\"recursion_id\":null,\"description\":\"照会用決済1\",\"authorized_until\":\"20150611\",\"refunded_amount\":0,\"refundable_until\":null,\"card\":{\"object\":\"card\",\"expire_year\":\"22\",\"expire_month\":\"11\",\"fingerprint\":\"ef7368bbbbbdf8733ffec8ecbd59d4e400b3398c956d845f9cd868a3e858d4d5\",\"name\":\"TEST MODULE\",\"brand\":\"Visa\",\"last4_digits\":\"1000\",\"created_time\":\"20150522103452\"},\"refunds\":[],\"fees\":[]}}");
			System.out.println(noticeEvent.toJsonString(true));
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
