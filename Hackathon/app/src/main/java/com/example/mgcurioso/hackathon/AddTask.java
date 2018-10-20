package com.example.mgcurioso.hackathon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.VolleyError;
import com.example.mgcurioso.hackathon.config.UrlsList;
import com.example.mgcurioso.hackathon.fragments.StudentFragment;
import com.example.mgcurioso.hackathon.interfaces.Titlable;
import com.example.mgcurioso.hackathon.utils.Api;

import org.json.JSONException;
import org.json.JSONObject;

public class AddTask extends AppCompatActivity implements Api.OnRespondListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    public void addTask() {
        Api.post(this)
                .setUrl(UrlsList.TASKS_URL)
                .request();
    }

    // OnRespondListener
    @Override
    public void onResponse(String tag, JSONObject response) throws JSONException {

    }

    @Override
    public void onErrorResponse(String tag, VolleyError error) throws JSONException {

    }

    @Override
    public void onException(JSONException e) {

    }
}
