package com.example.mgcurioso.hackathon;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.mgcurioso.hackathon.config.UrlsList;
import com.example.mgcurioso.hackathon.utils.Api;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class AddTask extends AppCompatActivity implements Api.OnRespondListener, View.OnClickListener {

    final int USER_ID = 1;

    Button btnDatePicker, btnTimePicker;
    TextInputEditText taskName, taskDesc, taskDate, taskTime;
    Context context;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        context = this;

        btnDatePicker=findViewById(R.id.browseDate);
        btnTimePicker=findViewById(R.id.browseTime);


        taskName=(TextInputEditText)findViewById(R.id.task_name);
        taskDesc=(TextInputEditText)findViewById(R.id.task_desc);
        taskDate=(TextInputEditText)findViewById(R.id.task_date);
        taskTime=(TextInputEditText)findViewById(R.id.task_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        /*
            -- Things to needed in DB --
            taskName
            taskDesc
            taskDate and taskTime (Parse in Unix Timestamp ? I don't know)
            classroomId (came from the user session)
        */


    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        taskDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            taskTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    public void addTask(View view) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("title", taskName.getText().toString());
            obj.put("content", taskDesc.getText().toString());
            obj.put("date", taskDate.getText().toString());
            obj.put("time", taskTime.getText().toString());
            obj.put("user_id", USER_ID);
            obj.put("class_id", null);
            Api.post(this).setUrl(UrlsList.TASKS_URL).request(obj).setTag("addTaskTag");

        } catch (Throwable tx) {
            Log.e("tagX", "Could not parse malformed JSON.");
        }

    }

    // OnRespondListener
    @Override
    public void onResponse(String tag, JSONObject response) throws JSONException {
        /*if(tag.equals("addTaskTag")){
        }*/
        Toast.makeText(context, "Successfully added \"" + response.getJSONObject("task").getString("title") + "\" task", Toast.LENGTH_SHORT).show();
        Log.d("tagX", "onResponse: " + response);
    }

    @Override
    public void onErrorResponse(String tag, VolleyError error) throws JSONException {
        Log.d("tagX", "onErrorResponse: " + error.toString());
    }

    @Override
    public void onException(JSONException e) {
        e.printStackTrace();
    }
}
