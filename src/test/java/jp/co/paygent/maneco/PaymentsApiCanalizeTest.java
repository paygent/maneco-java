package jp.co.paygent.maneco;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import jp.co.paygent.maneco.Maneco;
import jp.co.paygent.maneco.data.CardPaymentsResponse;
import jp.co.paygent.maneco.data.EventData;
import jp.co.paygent.maneco.data.PaymentsListResponse;
import jp.co.paygent.maneco.data.PaymentsResponse;
import jp.co.paygent.maneco.exception.ManecoException;
import jp.co.paygent.maneco.exception.ManecoResponseException;
import jp.co.paygent.maneco.exception.ManecoRuntimeException;

import org.junit.Test;

public class PaymentsApiCanalizeTest {

	@Test
	public void test_決済照会() {

		Maneco api = new Maneco("omiya");
		api.setBaseApiUrl("http://localhost:8080/maneco-api");

		String paymentsId = createPayment(api,"照会用決済1");

		System.out.println("*======================================================*");
		System.out.println("* 決済照会(1件)");
		System.out.println("*======================================================*");

		try {
			PaymentsResponse res = api.payments.retrieve(paymentsId);
			System.out.println(res.toJsonString(true));

			PaymentsResponse res2 = api.payments.retrieveRequest().id(paymentsId).execute();
			System.out.println(res2.toJsonString(true));

			assertTrue("決済照会(1件)完了", true);

		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("決済照会(1件)：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("決済照会(1件)：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("決済照会(1件)：異常終了", false);
		}


		createPayment(api,"照会用決済2");

		System.out.println("*======================================================*");
		System.out.println("* 決済照会(複数件)");
		System.out.println("*======================================================*");
		try {
			PaymentsListResponse res = api.payments.allRequest()
												   .offset(2)
												   .count(20)
												   .createdTimeFrom("20150519")
												   .createdTimeTo((new SimpleDateFormat("yyyyMMdd")).format(Calendar.getInstance().getTime()))
//												   .customerId("cus_dummy0123456")
//												   .recursionId("rec_dummy0123456")
//												   .status("captured")
												   .execute();

			System.out.println("Object:" + res.object);
			System.out.println("Result:" + res.result);
			for (EventData payments : res.data) {
				System.out.println(payments.toJsonString(false));
			}

			assertTrue("決済照会(複数件)完了", true);

		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("決済照会(複数件)：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("決済照会(複数件)：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("決済照会(複数件)：異常終了", false);
		}
	}

	/**
	 * @param api
	 * @return
	 */
	private String createPayment(Maneco api,String description) {

		String paymentId = "";

		System.out.println("*------------------------------------------------------*");
		System.out.println("* カード決済申込(都度課金)");
		System.out.println("*------------------------------------------------------*");

		try {
			CardPaymentsResponse res =
			api.cardPayments.createRequest().amount(1000)
											.cardNumber("4980058555001000")
											.cardExpireYear("22")
											.cardExpireMonth("11")
											.cardCvc("333")
											.cardName("TEST MODULE")
											.capture(false)
											.currency("JPY")
											.description(description)
											.expireDays(20)
											.execute();
			paymentId = res.paymentId;

		} catch (ManecoException e) {
			e.printStackTrace();
			fail();
		}

		return paymentId;
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
