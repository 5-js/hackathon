package com.example.mgcurioso.hackathon;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.mgcurioso.hackathon.config.UrlsList;
import com.example.mgcurioso.hackathon.items.User;
import com.example.mgcurioso.hackathon.utils.Api;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements Api.OnRespondListener {

    private TextInputEditText txtUsername;
    private TextInputEditText txtPassword;
    private CardView cardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User user = null;
        try {
            user = User.getSavedUser(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (user != null) {
            next();
            return;
        }

        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.login_txt_username);
        txtPassword = findViewById(R.id.login_txt_password);
        cardBtn = findViewById(R.id.login_card_btn);

        cardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final JSONObject params = new JSONObject();

                try {
                    params.put("email", txtUsername.getText().toString());
                    params.put("password", txtPassword.getText().toString());

                    Api.post(LoginActivity.this).setUrl(UrlsList.LOGIN_URL).request(params);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        if (User.didLogout(this)) {
            Snackbar.make(cardBtn, "Logout successfully.", Snackbar.LENGTH_LONG).show();
        }
    }

    public void next() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    // OnRespondListener
    @Override
    public void onResponse(String tag, JSONObject response) throws JSONException {
        final User user = new User(response.getJSONObject("user"));
        User.saveUser(this, user);

        next();
    }

    @Override
    public void onErrorResponse(String tag, VolleyError error) throws JSONException {
        Snackbar.make(cardBtn, "Unable to login.", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onException(JSONException e) {
        Log.d("tagX", e.getMessage());
        Snackbar.make(cardBtn, "An error occurred.", Snackbar.LENGTH_LONG).show();
    }
}
