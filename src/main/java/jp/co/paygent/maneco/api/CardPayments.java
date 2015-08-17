package jp.co.paygent.maneco.api;

import jp.co.paygent.maneco.Maneco;
import jp.co.paygent.maneco.data.CardPaymentsCaptureRequest;
import jp.co.paygent.maneco.data.CardPaymentsCreateRequest;
import jp.co.paygent.maneco.data.CardPaymentsRefundRequest;
import jp.co.paygent.maneco.data.CardPaymentsResponse;
import jp.co.paygent.maneco.data.RequestData;
import jp.co.paygent.maneco.exception.ManecoException;

public class CardPayments {

	private final Maneco maneco;

	public CardPayments(Maneco maneco) {
		this.maneco = maneco;
	}

	public CardPaymentsCreateRequest<CardPaymentsResponse> createRequest() {
		final CardPaymentsCreateRequest<CardPaymentsResponse> request = new CardPaymentsCreateRequest<CardPaymentsResponse>();
        request.setExecutor(new RequestData.Executable<CardPaymentsResponse>() {
            public CardPaymentsResponse execute() throws ManecoException {
                return CardPayments.this.create(request);
            }
        });
		return request;
	}

    private CardPaymentsResponse create(CardPaymentsCreateRequest<CardPaymentsResponse> request) throws ManecoException {
		return maneco.post(request, CardPaymentsResponse.class);
    }

	public CardPaymentsCaptureRequest<CardPaymentsResponse> captureRequest() {
		final CardPaymentsCaptureRequest<CardPaymentsResponse> request = new CardPaymentsCaptureRequest<CardPaymentsResponse>();
        request.setExecutor(new RequestData.Executable<CardPaymentsResponse>() {
            public CardPaymentsResponse execute() throws ManecoException {
                return CardPayments.this.capture(request);
            }
        });
		return request;
	}

    private CardPaymentsResponse capture(CardPaymentsCaptureRequest<CardPaymentsResponse> request) throws ManecoException {
		return maneco.post(request, CardPaymentsResponse.class);
    }

	public CardPaymentsRefundRequest<CardPaymentsResponse> refundRequest() {
		final CardPaymentsRefundRequest<CardPaymentsResponse> request = new CardPaymentsRefundRequest<CardPaymentsResponse>();
        request.setExecutor(new RequestData.Executable<CardPaymentsResponse>() {
            public CardPaymentsResponse execute() throws ManecoException {
                return CardPayments.this.refund(request);
            }
        });
		return request;
	}

    private CardPaymentsResponse refund(CardPaymentsRefundRequest<CardPaymentsResponse> request) throws ManecoException {
		return maneco.post(request, CardPaymentsResponse.class);
    }

}
