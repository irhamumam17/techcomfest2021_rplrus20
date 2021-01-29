package com.example.donacare;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    String PREFERENCE = "pref";
    SharedPreferences.Editor editor;

    public boolean getStatus(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return preferences.getBoolean("status", false);
    }

    public void setStatus(Context context, boolean login) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putBoolean("status", login);
        editor.apply();
    }

    public String getUsername(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return preferences.getString("username", "");
    }

    public void setUsername(Context context, String username) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("username", username);
        editor.apply();
    }

    public String getName(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return preferences.getString("name", "");
    }

    public void setName(Context context, String name) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("name", name);
        editor.apply();
    }

    public String getAddress(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return preferences.getString("address", "");
    }

    public void setAddress(Context context, String address) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("address", address);
        editor.apply();
    }

    public String getEmail(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return preferences.getString("email", "");
    }

    public void setEmail(Context context, String email) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("email", email);
        editor.apply();
    }

    public String getPhone(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        return preferences.getString("phone", "");
    }

    public void setPhone(Context context, String phone) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("phone", phone);
        editor.apply();
    }
}
