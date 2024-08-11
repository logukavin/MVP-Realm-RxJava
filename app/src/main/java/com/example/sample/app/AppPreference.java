package com.example.sample.app;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {
    private static final String APP_PREFERENCE = "SCHIMAG_APP_PREFERENCES";
    private static final String USER_ID = "USER_ID";

    private SharedPreferences preferences;

    public AppPreference(Context context) {
        preferences = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
    }

    public void clearPreferences() {
        preferences.edit().clear().apply();
    }

    public String getUserId() {
        return preferences.getString(USER_ID, "");
    }

    public void putUserId(String id) {
        preferences.edit().putString(USER_ID, id).apply();
    }

}


