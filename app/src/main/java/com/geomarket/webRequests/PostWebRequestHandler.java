package com.geomarket.webRequests;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.geomarket.infrastructure.RespostaCallback;

import org.apache.http.client.methods.HttpPost;

/**
 * Created by Luiz on 27/11/2014.
 */
public class PostWebRequestHandler extends Handler {
    private final RespostaCallback respostaCallback;
    private final HttpPost postRequest;

    public PostWebRequestHandler(Looper looper, HttpPost postRequest, RespostaCallback respostaCallback){
        super(looper);
        this.respostaCallback = respostaCallback;
        this.postRequest = postRequest;
    }

    @Override
    public void handleMessage(Message msg) {
        synchronized (this) {
            try {
                new PostWebRequestTask(postRequest, respostaCallback).execute().get();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
