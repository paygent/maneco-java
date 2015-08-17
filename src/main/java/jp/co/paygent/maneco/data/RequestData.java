package jp.co.paygent.maneco.data;

import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import jp.co.paygent.maneco.exception.ManecoException;
import jp.co.paygent.maneco.exception.ManecoRuntimeException;

public abstract class RequestData<O> extends BaseData {

    private Executable<O> executor;

    protected RequestData() {
        executor = null;
    }

    public O execute() throws ManecoException {
        if (executor == null) {
            throw new ManecoRuntimeException(ManecoException.UNKNOWN_ERROR);
        }
        return executor.execute();
    }

    public void setExecutor(Executable<O> executor) {
        this.executor = executor;
    }

    public abstract String pathTemplates();

    public abstract MultivaluedMap<String, String> queryParams();

    public abstract Map<String, Object> urlKey() throws ManecoException;

    public static abstract interface Executable<T> {
        public T execute() throws ManecoException;
    }

}
