package com.geomarket.infrastructure;

/**
 * Created by Luiz on 02/12/2014.
 */
public class Resposta<T> {
    private boolean success;
    private T object;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
