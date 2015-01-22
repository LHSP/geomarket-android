package com.geomarket.webRequests;

import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.geomarket.infrastructure.RespostaCallback;
import com.geomarket.webRequests.json.GetPropagandasJsonResult;
import com.geomarket.webRequests.json.JsonConverter;
import com.geomarket.webRequests.json.JsonResult;
import com.geomarket.webRequests.json.LoginJsonResult;
import com.geomarket.webRequests.json.MessageJsonResult;
import com.geomarket.webRequests.json.RegisterJsonResult;

import org.apache.http.client.methods.HttpPost;

import java.lang.reflect.Type;

/**
 * Created by Luiz on 03/12/2014.
 */
public class WebRequests {
    public static void Login(String uid, String senha, final RespostaCallback<JsonResult> respostaCallback){
        Looper mServiceLooper;
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                android.os.Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        RespostaCallback convertCallback = createRespostaCallback(respostaCallback, LoginJsonResult.class);

        HttpPost loginRequest = WebRoutes.postWebServiceLoginUrl(uid, senha);

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        PostWebRequestHandler mLoginHandler = new PostWebRequestHandler(mServiceLooper, loginRequest, convertCallback);

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mLoginHandler.obtainMessage();
        mLoginHandler.sendMessage(msg);
    }

    public static void Register(String senha, final RespostaCallback<JsonResult> respostaCallback){
        Looper mServiceLooper;
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                android.os.Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        RespostaCallback convertCallback = createRespostaCallback(respostaCallback, RegisterJsonResult.class);

        HttpPost registerRequest = WebRoutes.postWebServiceRegisterUrl(senha);

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        PostWebRequestHandler mRegisterHandler = new PostWebRequestHandler(mServiceLooper, registerRequest, convertCallback);

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mRegisterHandler.obtainMessage();
        mRegisterHandler.sendMessage(msg);
    }

    public static void GetPropagandas(String token, final RespostaCallback<JsonResult> respostaCallback){
        Looper mServiceLooper;
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                android.os.Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        RespostaCallback convertCallback = createRespostaCallback(respostaCallback, GetPropagandasJsonResult.class);

        HttpPost registerRequest = WebRoutes.postWebServiceGetPropagandasUrl(token);

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        PostWebRequestHandler mGetPropagandasHandler = new PostWebRequestHandler(mServiceLooper, registerRequest, convertCallback);

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mGetPropagandasHandler.obtainMessage();
        mGetPropagandasHandler.sendMessage(msg);
    }

    public static void assinaEstabelecimento(String tokenEstabelecimento, String token, final RespostaCallback<JsonResult> respostaCallback){
        Looper mServiceLooper;
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                android.os.Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        RespostaCallback convertCallback = createRespostaCallback(respostaCallback, MessageJsonResult.class);

        HttpPost assinaEstabeleicmentoRequest = WebRoutes.postWebServiceAssinaEstabelecimentoUrl(tokenEstabelecimento, token);

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        PostWebRequestHandler mAssinaEstabelecimentoHandler = new PostWebRequestHandler(mServiceLooper, assinaEstabeleicmentoRequest, convertCallback);

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mAssinaEstabelecimentoHandler.obtainMessage();
        mAssinaEstabelecimentoHandler.sendMessage(msg);
    }

    private static RespostaCallback<String> createRespostaCallback(final RespostaCallback<JsonResult> respostaCallback, final Type respostaCallbackType){

        RespostaCallback<String> convertCallback = new RespostaCallback<String>() {
            RespostaCallback innerCallback = respostaCallback;
            @Override
            public void call() {
                this.innerCallback.setException(this.getException());
                JsonResult registerJsonResult = JsonConverter.ToObject(this.getRespostaObject(), respostaCallbackType);
                this.innerCallback.setRespostaObject(registerJsonResult);
                this.innerCallback.call();
            }
        };

        return convertCallback;
    }
}
