package com.geomarket.infrastructure;

/**
 * Created by Luiz on 02/12/2014.
 */
public abstract class Callback<T> {

    protected T result;
    protected Exception exception;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public abstract void call();
}
