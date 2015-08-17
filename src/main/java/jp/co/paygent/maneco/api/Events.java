package jp.co.paygent.maneco.api;

import jp.co.paygent.maneco.Maneco;
import jp.co.paygent.maneco.data.EventsIdRequest;
import jp.co.paygent.maneco.data.EventsListRequest;
import jp.co.paygent.maneco.data.EventsListResponse;
import jp.co.paygent.maneco.data.EventsResponse;
import jp.co.paygent.maneco.data.RequestData;
import jp.co.paygent.maneco.exception.ManecoException;

public class Events {

	private final Maneco maneco;

	public Events(Maneco maneco) {
		this.maneco = maneco;
	}

	public EventsIdRequest<EventsResponse> retrieveRequest() {
		final EventsIdRequest<EventsResponse> request = new EventsIdRequest<EventsResponse>();
        request.setExecutor(new RequestData.Executable<EventsResponse>() {
            public EventsResponse execute() throws ManecoException {
                return Events.this.retrieve(request);
            }
        });
		return request;
	}

    private EventsResponse retrieve(EventsIdRequest<EventsResponse> request) throws ManecoException {
    	return maneco.get(request, EventsResponse.class);
    }

    public EventsResponse retrieve(String id) throws ManecoException {
    	return this.retrieveRequest().id(id).execute();
    }

	public EventsListRequest<EventsListResponse> allRequest() {
		final EventsListRequest<EventsListResponse> request = new EventsListRequest<EventsListResponse>();
        request.setExecutor(new RequestData.Executable<EventsListResponse>() {
            public EventsListResponse execute() throws ManecoException {
                return Events.this.all(request);
            }
        });
		return request;
	}

    private EventsListResponse all(EventsListRequest<EventsListResponse> request) throws ManecoException {
		return maneco.get(request, EventsListResponse.class);
    }

}
