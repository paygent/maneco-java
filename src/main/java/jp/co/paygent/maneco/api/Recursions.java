package jp.co.paygent.maneco.api;

import jp.co.paygent.maneco.Maneco;
import jp.co.paygent.maneco.data.RecursionsCreateRequest;
import jp.co.paygent.maneco.data.RecursionsIdRequest;
import jp.co.paygent.maneco.data.RecursionsListRequest;
import jp.co.paygent.maneco.data.RecursionsListResponse;
import jp.co.paygent.maneco.data.RecursionsResponse;
import jp.co.paygent.maneco.data.RecursionsResumeRequest;
import jp.co.paygent.maneco.data.RequestData;
import jp.co.paygent.maneco.exception.ManecoException;

public class Recursions {

	private final Maneco maneco;

	public Recursions(Maneco maneco) {
		this.maneco = maneco;
	}

	public RecursionsCreateRequest<RecursionsResponse> createRequest() {
		final RecursionsCreateRequest<RecursionsResponse> request = new RecursionsCreateRequest<RecursionsResponse>();
        request.setExecutor(new RequestData.Executable<RecursionsResponse>() {
            public RecursionsResponse execute() throws ManecoException {
                return Recursions.this.create(request);
            }
        });
		return request;
	}

    private RecursionsResponse create(RecursionsCreateRequest<RecursionsResponse> request) throws ManecoException {
		return maneco.post(request, RecursionsResponse.class);
    }

	public RecursionsIdRequest<RecursionsResponse> deleteRequest() {
		final RecursionsIdRequest<RecursionsResponse> request = new RecursionsIdRequest<RecursionsResponse>();
        request.setExecutor(new RequestData.Executable<RecursionsResponse>() {
            public RecursionsResponse execute() throws ManecoException {
                return Recursions.this.delete(request);
            }
        });
		return request;
	}

    private RecursionsResponse delete(RecursionsIdRequest<RecursionsResponse> request) throws ManecoException {
    	RecursionsResponse res = maneco.delete(request, RecursionsResponse.class);
		return res;
    }

	public RecursionsResumeRequest<RecursionsResponse> resumeRequest() {
		final RecursionsResumeRequest<RecursionsResponse> request = new RecursionsResumeRequest<RecursionsResponse>();
        request.setExecutor(new RequestData.Executable<RecursionsResponse>() {
            public RecursionsResponse execute() throws ManecoException {
                return Recursions.this.resume(request);
            }
        });
		return request;
	}

    private RecursionsResponse resume(RecursionsResumeRequest<RecursionsResponse> request) throws ManecoException {
    	RecursionsResponse res = maneco.post(request, RecursionsResponse.class);
		return res;
    }

	public RecursionsIdRequest<RecursionsResponse> retrieveRequest() {
		final RecursionsIdRequest<RecursionsResponse> request = new RecursionsIdRequest<RecursionsResponse>();
        request.setExecutor(new RequestData.Executable<RecursionsResponse>() {
            public RecursionsResponse execute() throws ManecoException {
                return Recursions.this.retrieve(request);
            }
        });
		return request;
	}

    private RecursionsResponse retrieve(RecursionsIdRequest<RecursionsResponse> request) throws ManecoException {
    	return maneco.get(request, RecursionsResponse.class);
    }

    public RecursionsResponse retrieve(String id) throws ManecoException {
    	return this.retrieveRequest().id(id).execute();
    }

	public RecursionsListRequest<RecursionsListResponse> allRequest() {
		final RecursionsListRequest<RecursionsListResponse> request = new RecursionsListRequest<RecursionsListResponse>();
        request.setExecutor(new RequestData.Executable<RecursionsListResponse>() {
            public RecursionsListResponse execute() throws ManecoException {
                return Recursions.this.all(request);
            }
        });
		return request;
	}

    private RecursionsListResponse all(RecursionsListRequest<RecursionsListResponse> request) throws ManecoException {
		return maneco.get(request, RecursionsListResponse.class);
    }
}
