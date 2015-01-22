package com.geomarket.webRequests;

import android.net.Uri;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URI;

/**
 * Created by Luiz on 27/11/2014.
 */
public class WebRoutes {

    public static final Uri WEB_SERVICE_ROOT_URI = new Uri.Builder().scheme("http").authority("geomarket-web.herokuapp.com").build();

    public static Uri getWebServiceLoginUri(String username_or_email, String login_password){
        return WEB_SERVICE_ROOT_URI.buildUpon()
                .path("api/login_usuario.json")
                .appendQueryParameter("username_or_email", username_or_email)
                .appendQueryParameter("login_password", login_password)
                .build();
    }

    public static HttpPost postWebServiceLoginUrl(String uid, String senha){
        URI loginUri = URI.create(WEB_SERVICE_ROOT_URI.buildUpon().path("api/login_usuario.json").build().toString());
        HttpPost postRequest = new HttpPost(loginUri);
        JSONObject object = new JSONObject();
        try {
            object.put("uid", uid);
            object.put("password", senha);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            postRequest.setEntity(new StringEntity(object.toString(), "UTF8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        postRequest.setHeader("Content-type", "application/json");

        return postRequest;
    }

    public static HttpPost postWebServiceRegisterUrl(String senha) {
        URI loginUri = URI.create(WEB_SERVICE_ROOT_URI.buildUpon().path("api/registra_usuario.json").build().toString());
        HttpPost postRequest = new HttpPost(loginUri);
        JSONObject object = new JSONObject();
        try {
            JSONObject usuario = new JSONObject();
            usuario.put("password", senha);
            usuario.put("password_confirmation", senha);
            object.put("usuario", usuario);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            postRequest.setEntity(new StringEntity(object.toString(), "UTF8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        postRequest.setHeader("Content-type", "application/json");

        return postRequest;
    }

    public static HttpPost postWebServiceGetPropagandasUrl(String token) {
        URI loginUri = URI.create(WEB_SERVICE_ROOT_URI.buildUpon().path("api/propagandas_usuario.json").build().toString());
        HttpPost postRequest = new HttpPost(loginUri);
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            postRequest.setEntity(new StringEntity(object.toString(), "UTF8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        postRequest.setHeader("Content-type", "application/json");

        return postRequest;
    }

    public static HttpPost postWebServiceAssinaEstabelecimentoUrl(String estab_token, String token) {
        URI loginUri = URI.create(WEB_SERVICE_ROOT_URI.buildUpon().path("api/assina_estabelecimento.json").build().toString());
        HttpPost postRequest = new HttpPost(loginUri);
        JSONObject object = new JSONObject();
        try {
            object.put("token", token);
            object.put("estab_token", estab_token);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            postRequest.setEntity(new StringEntity(object.toString(), "UTF8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        postRequest.setHeader("Content-type", "application/json");

        return postRequest;
    }

    public static final String EXCEPTION_ON_REQUEST = "ExceptionOnRequest";
}
