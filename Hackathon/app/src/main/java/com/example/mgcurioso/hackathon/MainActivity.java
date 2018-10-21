package com.example.mgcurioso.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.mgcurioso.hackathon.fragments.StudentFragment;
import com.example.mgcurioso.hackathon.fragments.TasksFragment;
import com.example.mgcurioso.hackathon.interfaces.Titlable;
import com.example.mgcurioso.hackathon.items.Task;
import com.example.mgcurioso.hackathon.utils.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Api.OnRespondListener, StudentFragment.OnFragmentInteractionListener {

    private static Fragment CURR_FRAGMENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //! set Fragment here!
        // if fragment not set
        // use default fragment (should depend on role)
        setFragment(CURR_FRAGMENT != null ? CURR_FRAGMENT : new StudentFragment());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment newFragment = null;

        int id = item.getItemId();
        //! sample only
        if (id == R.id.nav_task) {
            newFragment = new TasksFragment();
            /*startActivity(new Intent(this, AddTask.class));*/
        } else if (id == R.id.nav_allowance) {
            newFragment = new StudentFragment();
        } else if (id == R.id.nav_achievements) {
            newFragment = new StudentFragment();
        } else if (id == R.id.nav_classes) {
            startActivity(new Intent(MainActivity.this, Classes.class));
            /* startActivity(new Intent(MainActivity.this, UsageStat.class)); */
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();

        } else {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }

        // set fragment here
        setFragment(newFragment);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    // methods
    private void setFragment(Fragment newFragment) {
        if (newFragment == null) {
            return;
        }

        // newFragment should implement Titlable first!
        if (!(newFragment instanceof Titlable)) {
            throw new RuntimeException(newFragment.toString() + " must implement Titlable");
        }

        final FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();

        // if no curr fragment, then add it
        // else, just replace it
        if (CURR_FRAGMENT == null) {
            transaction.add(R.id.main_root_layout, newFragment);
            Log.d("tagX", "if ");
        } else {
            transaction.replace(R.id.main_root_layout, newFragment);
            Log.d("tagX", "else ");
        }

        transaction.commit();

        // new then becomes the current! :)
        CURR_FRAGMENT = newFragment;

        // set dat title
        setTitle(((Titlable) newFragment).getTitle());
    }

    @Override
    public void onResponse(String tag, JSONObject response) throws JSONException {
        final ArrayList<Task> list = new ArrayList<>();

        if (tag.equals("fetchTasks")) {
            Log.d("tagX", "fetchTasks");
            final JSONArray jsonTasks = response.getJSONArray("tasks");
            for (int i = 0; i < jsonTasks.length(); i++) {
                final Task task = new Task(jsonTasks.getJSONObject(i));
                list.add(task);
                Log.d("tagX", "added task");
            }

            // put this to fragment
            // assert that curr frag is tasksFrag
            Log.d("tagX", "setToData");
            ((TasksFragment) CURR_FRAGMENT).setData(list);
            Log.d("tagX", "didSetToData");
        }
    }

    @Override
    public void onErrorResponse(String tag, VolleyError error) throws JSONException {
        Log.d("tagX", "onErrorResponse: " + error.getMessage());
    }

    @Override
    public void onException(JSONException e) {
        e.printStackTrace();
        Log.d("tagX", e.getMessage());
    }
}
