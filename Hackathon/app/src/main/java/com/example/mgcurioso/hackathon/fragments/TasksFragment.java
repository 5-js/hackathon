package com.example.mgcurioso.hackathon.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mgcurioso.hackathon.R;
import com.example.mgcurioso.hackathon.interfaces.Titlable;
import com.example.mgcurioso.hackathon.utils.Api;

import static com.example.mgcurioso.hackathon.config.UrlsList.TASKS_URL;

public class TasksFragment extends Fragment implements Titlable {

    final int USER_ID = 2;
    public TasksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        //Toast.makeText(view.getContext(), "asd", Toast.LENGTH_LONG).show();
        ListView tasks_listView = view.findViewById(R.id.task_listView);

        // get tasks on db
        Api.get(getContext()).setUrl(TASKS_URL + USER_ID).request();

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public String getTitle() {
        return "Tasks";
    }
}
