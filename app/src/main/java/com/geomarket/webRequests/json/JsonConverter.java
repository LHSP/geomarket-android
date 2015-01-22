package com.geomarket.webRequests.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by carambola-acer on 26/08/2014.
 */
public class JsonConverter {
    public static <T> T ToObject(String json, Class tClass) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(json, (Class<T>) tClass);
    }

    public static <T> T ToObject(String json, java.lang.reflect.Type tType) {
        try {
            GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                                                                //"2015-01-15T01:00:00.000Z"
            Gson gson = builder.create();
            return gson.fromJson(json, tType);
        }
        catch(Exception ex){
            return null;
        }
    }

    public static <T> String ToJson(T obj) {
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return gson.toJson(obj);
        }
        catch(Exception ex) {
            return null;
        }
    }

}
