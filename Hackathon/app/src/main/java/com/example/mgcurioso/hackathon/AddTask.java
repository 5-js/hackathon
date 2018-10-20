package com.example.mgcurioso.hackathon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

import com.android.volley.VolleyError;
import com.example.mgcurioso.hackathon.config.UrlsList;
import com.example.mgcurioso.hackathon.fragments.StudentFragment;
import com.example.mgcurioso.hackathon.interfaces.Titlable;
import com.example.mgcurioso.hackathon.utils.Api;

import org.json.JSONException;
import org.json.JSONObject;

public class AddTask extends AppCompatActivity implements Api.OnRespondListener, View.OnClickListener {

    CardView     btnDatePicker, btnTimePicker;
    TextInputEditText taskName, taskDesc, taskDate, taskTime;

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

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
