package com.geomarket.infrastructure;

import java.lang.reflect.Type;

/**
 * Created by Luiz on 27/11/2014.
 */
public abstract class RespostaCallback<T> extends Callback<Resposta<T>> {
    public RespostaCallback() {
        this.setResult(new Resposta<T>());
    }

    public void setRespostaObject(T object){
        if(this.getResult() == null) this.setResult(new Resposta<T>());
        this.getResult().setObject(object);
    }

    public void setRespostaSuccess(boolean success){
        if(this.getResult() == null) this.setResult(new Resposta<T>());
        this.getResult().setSuccess(success);
    }

    public boolean getSuccess() {
        Resposta<T> result = this.getResult();
        return result != null && result.isSuccess();
    }

    public T getRespostaObject() {
        Resposta<T> result = this.getResult();
        if(result == null) return null;
        return result.getObject();
    }
}
