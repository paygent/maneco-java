package jp.co.paygent.maneco;

import static org.junit.Assert.*;
import jp.co.paygent.maneco.Maneco;
import jp.co.paygent.maneco.data.CardPaymentsResponse;
import jp.co.paygent.maneco.exception.ManecoException;
import jp.co.paygent.maneco.exception.ManecoResponseException;
import jp.co.paygent.maneco.exception.ManecoRuntimeException;

import org.junit.Test;

public class CardPaymentsApiCanalizeTest {

	@Test
	public void test_カード決済申込・売上・払戻し() {

		String paymentsId = "";

		Maneco api = new Maneco("omiya");
		api.setBaseApiUrl("http://localhost:8080/maneco-api");

		System.out.println("*======================================================*");
		System.out.println("* カード決済申込(都度課金)");
		System.out.println("*======================================================*");

		try {
			CardPaymentsResponse res =
			api.cardPayments.createRequest()
//											.card("tkn_dummy0123456")
											.amount(1000)
											.cardNumber("4980058555001000")
											.cardExpireYear("22")
											.cardExpireMonth("11")
											.cardCvc("333")
											.cardName("TEST MODULE")
//											.customerId("cus_dummy0123456")
											.capture(false)
											.currency("JPY")
											.description("接続モジュールからの作成")
											.expireDays(20)
											.execute();
			System.out.println("ResponceObject:" + res.toJsonString(true));
			paymentsId = res.paymentId;

			assertTrue("カード決済申込(都度課金)完了", true);

		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("カード決済申込(都度課金)：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("カード決済申込(都度課金)：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("カード決済申込(都度課金)：異常終了", false);
		}

		System.out.println("*======================================================*");
		System.out.println("* カード決済売上");
		System.out.println("*======================================================*");

		try {
			CardPaymentsResponse res = api.cardPayments.captureRequest().id(paymentsId).execute();
			System.out.println("ResponceObject:" + res.toJsonString(true));
			assertTrue("カード決済売上完了", true);
		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("カード決済売上：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("カード決済売上：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("カード決済売上：異常終了", false);
		}


		System.out.println("*======================================================*");
		System.out.println("* カード決済払戻し");
		System.out.println("*======================================================*");

		try {
			CardPaymentsResponse res =
			api.cardPayments.refundRequest().id(paymentsId)
											.amount(700)
											.execute();
			System.out.println("ResponceObject:" + res.toJsonString(true));
			assertTrue("カード決済払戻し完了", true);
		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getMessage());
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("カード決済払戻し：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("カード決済払戻し：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("カード決済払戻し：異常終了", false);
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
