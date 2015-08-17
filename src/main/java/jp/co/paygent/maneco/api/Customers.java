package jp.co.paygent.maneco.api;

import jp.co.paygent.maneco.Maneco;
import jp.co.paygent.maneco.data.CustomerIdRequest;
import jp.co.paygent.maneco.data.CustomersCreateRequest;
import jp.co.paygent.maneco.data.CustomersListRequest;
import jp.co.paygent.maneco.data.CustomersListResponse;
import jp.co.paygent.maneco.data.CustomersResponse;
import jp.co.paygent.maneco.data.CustomersUpdateRequest;
import jp.co.paygent.maneco.data.RequestData;
import jp.co.paygent.maneco.exception.ManecoException;


public class Customers {

	private final Maneco maneco;

	public Customers(Maneco maneco) {
		this.maneco = maneco;
	}

	public CustomersCreateRequest<CustomersResponse> createRequest() {
		final CustomersCreateRequest<CustomersResponse> request = new CustomersCreateRequest<CustomersResponse>();
        request.setExecutor(new RequestData.Executable<CustomersResponse>() {
            public CustomersResponse execute() throws ManecoException {
                return Customers.this.create(request);
            }
        });
		return request;
	}

    private CustomersResponse create(CustomersCreateRequest<CustomersResponse> request) throws ManecoException {
		CustomersResponse res = maneco.post(request, CustomersResponse.class);
		return res;
    }

	public CustomersUpdateRequest<CustomersResponse> updateRequest() {
		final CustomersUpdateRequest<CustomersResponse> request = new CustomersUpdateRequest<CustomersResponse>();
        request.setExecutor(new RequestData.Executable<CustomersResponse>() {
            public CustomersResponse execute() throws ManecoException {
                return Customers.this.update(request);
            }
        });
		return request;
	}

    private CustomersResponse update(CustomersUpdateRequest<CustomersResponse> request) throws ManecoException {
		CustomersResponse res = maneco.post(request, CustomersResponse.class);
		return res;
    }

	public CustomerIdRequest<CustomersResponse> deleteRequest() {
		final CustomerIdRequest<CustomersResponse> request = new CustomerIdRequest<CustomersResponse>();
        request.setExecutor(new RequestData.Executable<CustomersResponse>() {
            public CustomersResponse execute() throws ManecoException {
                return Customers.this.delete(request);
            }
        });
		return request;
	}

    private CustomersResponse delete(CustomerIdRequest<CustomersResponse> request) throws ManecoException {
		CustomersResponse res = maneco.delete(request, CustomersResponse.class);
		return res;
    }

	public CustomerIdRequest<CustomersResponse> retrieveRequest() {
		final CustomerIdRequest<CustomersResponse> request = new CustomerIdRequest<CustomersResponse>();
        request.setExecutor(new RequestData.Executable<CustomersResponse>() {
            public CustomersResponse execute() throws ManecoException {
                return Customers.this.retrieve(request);
            }
        });
		return request;
	}

    private CustomersResponse retrieve(CustomerIdRequest<CustomersResponse> request) throws ManecoException {
		CustomersResponse res = maneco.get(request, CustomersResponse.class);
		return res;
    }

    public CustomersResponse retrieve(String id) throws ManecoException {
    	return this.retrieveRequest().id(id).execute();
    }

	public CustomersListRequest<CustomersListResponse> allRequest() {
		final CustomersListRequest<CustomersListResponse> request = new CustomersListRequest<CustomersListResponse>();
        request.setExecutor(new RequestData.Executable<CustomersListResponse>() {
            public CustomersListResponse execute() throws ManecoException {
                return Customers.this.all(request);
            }
        });
		return request;
	}

    private CustomersListResponse all(CustomersListRequest<CustomersListResponse> request) throws ManecoException {
		CustomersListResponse res = maneco.get(request, CustomersListResponse.class);
		return res;
    }

}
