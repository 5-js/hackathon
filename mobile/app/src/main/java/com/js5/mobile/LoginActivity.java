package com.js5.mobile;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.js5.mobile.config.UrlsList;
import com.js5.mobile.utils.Api;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements Api.OnRespondListener {

    private Button button;
    private TextInputEditText txtUsername;
    private TextInputEditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.login_btn);
        txtUsername = findViewById(R.id.login_txt_username);
        txtPassword = findViewById(R.id.login_txt_password);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject params = new JSONObject();

                try {
                    params.put("username", txtUsername.getText());
                    params.put("password", txtPassword.getText());

                    Api.get(view.getContext())
                            .setUrl(UrlsList.LOGIN_URL)
                            .request(params);
                } catch (Exception e) {
                    Snackbar.make(view, "Unable to login.", Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }

    // OnRespondListener
    @Override
    public void onResponse(String tag, JSONObject response) throws JSONException {
        Log.d("tagx", response.toString());

        // once logged, redirect to MainActivity
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onErrorResponse(String tag, VolleyError error) throws JSONException {
        Snackbar.make(button, "Unable to login.", Snackbar.LENGTH_LONG).show();
        Log.d("tagx", error.getMessage());
    }

    @Override
    public void onException(JSONException e) {
        Snackbar.make(button, "An error occurred.", Snackbar.LENGTH_LONG).show();
        Log.d("tagx", "Error ex");
    }
}
