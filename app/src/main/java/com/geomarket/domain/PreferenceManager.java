package com.geomarket.domain;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Luiz on 08/01/2015.
 */
public class PreferenceManager {
    public static Context context;
    public static String PREFERENCE_FILE_NAME = "GEOMARKET_PREFS";

    public static boolean SaveString(String preferenceName, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE).edit();
        try {
            editor.putString(preferenceName, value);
            editor.commit();
            return true;
        }
        catch (Exception ex){
            Log.d("PreferenceManager", "Failed to Save Preference " + preferenceName + ":" + value, ex);
            return false;
        }
    }

    public static String LoadString(String preferenceName){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(preferenceName, "");
    }
}
