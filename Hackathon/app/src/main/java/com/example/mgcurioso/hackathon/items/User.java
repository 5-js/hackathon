package com.example.mgcurioso.hackathon.items;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mgcurioso.hackathon.config.PrefsList;

import org.json.JSONException;
import org.json.JSONObject;

public final class User {
    private int id;
    private String name;
    private String email;
    private String mobile;
    private String avatar;
    private int role;
    private String date;
    private String time;
    private String datetime;
    private String humanTime;
    private JSONObject json;

    public User(JSONObject json) throws JSONException {
        this.json = json;
        this.id = json.getInt("id");
        this.name = json.getString("name");
        this.email = json.getString("email");
        this.mobile = json.getString("mobile");
        this.avatar = json.getString("avatar");
        this.avatar = json.getString("avatar");
        this.role = json.getInt("role");
        this.date = json.getString("date");
        this.time = json.getString("time");
        this.datetime = json.getString("datetime");
        this.humanTime = json.getString("human_time");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getRole() {
        return role;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getHumanTime() {
        return humanTime;
    }

    public JSONObject getJson() {
        return json;
    }

    // static
    public static void saveUser(Context context, String jsonUser) throws JSONException {
        saveUser(context, new User(new JSONObject(jsonUser)));
    }

    public static void saveUser(Context context, User user) {
        final SharedPreferences preferences = context.getSharedPreferences(PrefsList.PREF_APP, Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit = preferences.edit();

        edit.putInt(PrefsList.PREF_USER_ID, user.getId());
        edit.putString(PrefsList.PREF_USER_JSON, user.getJson().toString());
        edit.putBoolean(PrefsList.PREF_DID_LOG_IN, true);

        edit.apply();
    }

    public static User getSavedUser(Context context) throws JSONException {
        final SharedPreferences preferences = context.getSharedPreferences(PrefsList.PREF_APP, Context.MODE_PRIVATE);
        final String stringUser = preferences.getString(PrefsList.PREF_USER_JSON, null);

        if (stringUser == null) {
            return null;
        }

        return new User(new JSONObject(stringUser));
    }

    public static void removeSavedUser(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PrefsList.PREF_APP, Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit = preferences.edit();

        edit.remove(PrefsList.PREF_USER_JSON);
        edit.remove(PrefsList.PREF_USER_ID);
        edit.remove(PrefsList.PREF_USER_ID);

        edit.remove(PrefsList.PREF_USER_ID);
        edit.remove(PrefsList.PREF_USER_JSON);
        edit.remove(PrefsList.PREF_DID_LOG_IN);
        edit.putBoolean(PrefsList.PREF_DID_LOG_OUT, true);

        edit.apply();
    }

    public static boolean didLogin(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PrefsList.PREF_APP, Context.MODE_PRIVATE);
        final boolean didLogin = preferences.getBoolean(PrefsList.PREF_DID_LOG_IN, false);

        // remove didLogin
        preferences.edit().remove(PrefsList.PREF_DID_LOG_IN).apply();

        return didLogin;
    }

    public static boolean didLogout(Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(PrefsList.PREF_APP, Context.MODE_PRIVATE);
        final boolean didLogout = preferences.getBoolean(PrefsList.PREF_DID_LOG_OUT, false);

        // remove didLogout
        preferences.edit().remove(PrefsList.PREF_DID_LOG_OUT).apply();

        return didLogout;
    }
}
