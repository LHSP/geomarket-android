package com.geomarket.webRequests;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.geomarket.infrastructure.RespostaCallback;

/**
 * Created by Luiz on 27/11/2014.
 */
public class GetWebRequestHandler extends Handler {
    private final RespostaCallback respostaCallback;
    private final Uri geturi;

    public GetWebRequestHandler(Looper looper, Uri getUri, RespostaCallback respostaCallback){
        super(looper);
        this.respostaCallback = respostaCallback;
        this.geturi = getUri;
    }

    @Override
    public void handleMessage(Message msg) {
        synchronized (this) {
            try {
                new GetWebRequestTask(geturi, respostaCallback).execute().get();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
