package jp.co.paygent.maneco.api;

import jp.co.paygent.maneco.Maneco;
import jp.co.paygent.maneco.data.PaymentsIdRequest;
import jp.co.paygent.maneco.data.PaymentsListRequest;
import jp.co.paygent.maneco.data.PaymentsListResponse;
import jp.co.paygent.maneco.data.PaymentsResponse;
import jp.co.paygent.maneco.data.RequestData;
import jp.co.paygent.maneco.exception.ManecoException;

public class Payments {

	private final Maneco maneco;

	public Payments(Maneco maneco) {
		this.maneco = maneco;
	}

	public PaymentsIdRequest<PaymentsResponse> retrieveRequest() {
		final PaymentsIdRequest<PaymentsResponse> request = new PaymentsIdRequest<PaymentsResponse>();
        request.setExecutor(new RequestData.Executable<PaymentsResponse>() {
            public PaymentsResponse execute() throws ManecoException {
                return Payments.this.retrieve(request);
            }
        });
		return request;
	}

    private PaymentsResponse retrieve(PaymentsIdRequest<PaymentsResponse> request) throws ManecoException {
    	return maneco.get(request, PaymentsResponse.class);
    }

    public PaymentsResponse retrieve(String id) throws ManecoException {
    	return this.retrieveRequest().id(id).execute();
    }

	public PaymentsListRequest<PaymentsListResponse> allRequest() {
		final PaymentsListRequest<PaymentsListResponse> request = new PaymentsListRequest<PaymentsListResponse>();
        request.setExecutor(new RequestData.Executable<PaymentsListResponse>() {
            public PaymentsListResponse execute() throws ManecoException {
                return Payments.this.all(request);
            }
        });
		return request;
	}

    private PaymentsListResponse all(PaymentsListRequest<PaymentsListResponse> request) throws ManecoException {
		return maneco.get(request, PaymentsListResponse.class);
    }

}
