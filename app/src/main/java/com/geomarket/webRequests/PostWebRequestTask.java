package com.geomarket.webRequests;

import android.os.AsyncTask;

import com.geomarket.infrastructure.Helpers;
import com.geomarket.infrastructure.RespostaCallback;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;

/**
 * Created by Luiz on 27/11/2014.
 */
public class PostWebRequestTask extends AsyncTask<Void, Void, Boolean> {

    private final RespostaCallback<String> respostaCallback;
    private final HttpPost postRequest;

    public PostWebRequestTask(HttpPost postRequest, RespostaCallback<String> respostaCallback){
        this.postRequest = postRequest;
        this.respostaCallback = respostaCallback;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        boolean success = true;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            // Execute the request
            HttpResponse response;
            try {
                response = httpclient.execute(postRequest);
                result = response.getStatusLine().toString();

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // A Simple JSON Response Read
                    InputStream instream = entity.getContent();
                    result = Helpers.convertStreamToString(instream);
                    instream.close();
                }
                success = true;

            } catch (Exception e) {
                respostaCallback.setException(e);
                result = WebRoutes.EXCEPTION_ON_REQUEST;
                success = false;
            }
        } catch (Exception e) {
            respostaCallback.setException(e);
            success = false;
        }

        respostaCallback.setRespostaObject(result);
        respostaCallback.setRespostaSuccess(success);

        return success;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        super.onPostExecute(success);
        respostaCallback.call();
    }
}
