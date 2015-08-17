package jp.co.paygent.maneco;

import static org.junit.Assert.*;
import jp.co.paygent.maneco.Maneco;
import jp.co.paygent.maneco.data.CustomersListResponse;
import jp.co.paygent.maneco.data.CustomersResponse;
import jp.co.paygent.maneco.exception.ManecoException;
import jp.co.paygent.maneco.exception.ManecoResponseException;
import jp.co.paygent.maneco.exception.ManecoRuntimeException;

import org.junit.Test;

public class CustomersApiCanalizeTest {

	@Test
	public void test_顧客照会_複数件() {
		Maneco api = new Maneco("omiya");
		api.setBaseApiUrl("http://localhost:8080/maneco-api");

		System.out.println("*======================================================*");
		System.out.println("* 顧客照会(複数件)");
		System.out.println("*======================================================*");

		try {
			CustomersListResponse res = api.customers.allRequest()
													.count(20)
													.offset(5)
													.createdTimeFrom("20150301")
													.createdTimeTo("20150501")
													.execute();
			System.out.println("Object:" + res.object);
			System.out.println("Result:" + res.result);
			System.out.println("Data:");
			for (CustomersResponse cust : res.data) {
				System.out.println(cust.toJsonString(false));
			}
			assertTrue("顧客照会(複数件)完了", true);
		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("顧客照会(複数件)：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("顧客照会(複数件)：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("顧客照会(複数件)：異常終了", false);
		}

	}

	@Test
	public void test_顧客作成・照会・更新・削除() {
		Maneco api = new Maneco("omiya");
		api.setBaseApiUrl("http://localhost:8080/maneco-api");

		String customerId = "";

		System.out.println("*======================================================*");
		System.out.println("* 顧客作成");
		System.out.println("*======================================================*");

		try {
			CustomersResponse res = api.customers.createRequest()
//																 .card("tkn_dummy0123456")
																 .cardNumber("4980058555001000")
																 .cardExpireYear("22")
																 .cardExpireMonth("11")
																 .cardCvc("333")
																 .cardName("TEST MODULE")
																 .email("Mod@test.com")
																 .description("接続モジュールの試し")
																 .execute();

			System.out.println(res.toJsonString(true));
			customerId = res.customerId;
			assertTrue("顧客作成完了", true);

		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("顧客作成：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("顧客作成：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("顧客作成：異常終了", false);
		}

		System.out.println("*======================================================*");
		System.out.println("* 顧客照会(1件)");
		System.out.println("*======================================================*");

		try {
			CustomersResponse res = api.customers.retrieveRequest().id(customerId).execute();
			System.out.println(res.toJsonString(true));

			CustomersResponse res2 = api.customers.retrieve(customerId);
			System.out.println(res2.toJsonString(true));

			assertTrue("顧客照会(1件)完了", true);

		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("顧客照会(1件)：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("顧客照会(1件)：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("顧客照会(1件)：異常終了", false);
		}


		System.out.println("*======================================================*");
		System.out.println("* 顧客更新");
		System.out.println("*======================================================*");
		try {
			CustomersResponse res = api.customers.updateRequest().id(customerId)
//																 .card("tkn_dummy0123456")
																 .cardNumber("4980058567001000")
																 .cardExpireYear("23")
																 .cardExpireMonth("12")
																 .cardCvc("444")
																 .cardName("UPDATE CARD")
																 .email("update@test.com")
																 .description("接続モジュールの試し:更新")
																 .execute();

			System.out.println(res.toJsonString(true));
			assertTrue("顧客更新完了", true);

		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("顧客更新：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("顧客更新：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("顧客更新：異常終了", false);
		}


		System.out.println("*======================================================*");
		System.out.println("* 顧客削除");
		System.out.println("*======================================================*");
		try {
			CustomersResponse res = api.customers.deleteRequest().id(customerId).execute();

			System.out.println(res.toJsonString(true));
			assertTrue("顧客削除完了", true);
		} catch (ManecoResponseException e) {
			System.out.println("API ERROR!!!");
			System.out.println("HTTP status code : " + e.getHttpStatusCode());
			System.out.println("ErrorCode : " + e.getCode());
			System.out.println("Detail    : " + e.getErrorResponse().errorMsg);
			System.out.println("Parameter : " + arrayToStr(e.getErrorParameter()));
			assertTrue("顧客削除：異常応答", false);
		} catch (ManecoRuntimeException e) {
			e.printStackTrace();
			assertTrue("顧客削除：異常終了", false);
		} catch (ManecoException e) {
			e.printStackTrace();
			assertTrue("顧客削除：異常終了", false);
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
