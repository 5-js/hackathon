package com.example.mgcurioso.hackathon.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mgcurioso.hackathon.R;
import com.example.mgcurioso.hackathon.interfaces.Titlable;
import com.example.mgcurioso.hackathon.items.Task;
import com.example.mgcurioso.hackathon.utils.Api;

import java.util.ArrayList;

import static com.example.mgcurioso.hackathon.config.UrlsList.TASKS_URL;

public class TasksFragment extends Fragment implements Titlable {

    private static final int USER_ID = 2;

    public TasksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        // get tasks on db
        Api.get(getContext()).setTag("fetchTasks").setUrl(TASKS_URL + USER_ID).request();

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public String getTitle() {
        return "Tasks";
    }

    public void setData(ArrayList list) {
        final View view = getView();

        if (view == null) {
            return;
        }

        Log.d("tagX", "has view");

        final ListView listView = view.findViewById(R.id.tasks_listView);

        // if no adapter, set it
        if (listView.getAdapter() == null) {
            listView.setAdapter(new Task.TaskArrayAdapter(getContext(), android.R.layout.simple_list_item_1, list));
        }

        // update
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetChanged();
    }
}
