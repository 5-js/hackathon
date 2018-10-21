package com.example.mgcurioso.hackathon;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.mgcurioso.hackathon.utils.Api;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.mgcurioso.hackathon.config.UrlsList.BASE_URL;

public class Classes extends AppCompatActivity implements Api.OnRespondListener{
    ListView classes_listView;

    Context context;

    String classes_URL = BASE_URL + "api/classes";
    String[] values = new String[] {
        "Science", "Math", "Filipino"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        classes_listView = findViewById(R.id.classes_listView);
        ArrayList<String> classItems = new ArrayList<String>();
        context = this;

        final ArrayList<String> list = new ArrayList<String>();
        Collections.addAll(list, values);

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        classes_listView.setAdapter(adapter);

        classes_listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Api.get(context);
                    }
                }
        );
    }


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
