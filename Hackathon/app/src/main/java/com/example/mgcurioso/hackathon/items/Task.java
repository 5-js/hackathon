package com.example.mgcurioso.hackathon.items;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mgcurioso.hackathon.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public final class Task {
    private int id;
    private int status;
    private String title;
    private String content;
    private String dueDate;
    private String createdAt;
    private String updatedAt;
    private JSONObject json;

    public Task(JSONObject json) throws JSONException {
        this.json = json;
        this.id = json.getInt("id");
        this.title = json.getString("title");
        this.status = json.getInt("status");
        this.dueDate= json.getString("dueDate");
        this.createdAt = json.getString("created_at");
        this.updatedAt = json.getString("updated_at");
        this.content = json.getString("content");
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public JSONObject getJson() {
        return json;
    }

    // static
    public static class TaskArrayAdapter extends ArrayAdapter<Task> {
        public TaskArrayAdapter(@NonNull Context context, int resource, @NonNull List<Task> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;

            if (view == null) {
                final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_task_view, null);
            }

            final Task task = getItem(position);

            final TextView txtTitle = view.findViewById(R.id.item_txt_title);
            final TextView txtContent = view.findViewById(R.id.item_txt_content);

            txtTitle.setText(task.getTitle());
            txtContent.setText(task.getContent());

            return view;
        }
    }
}
