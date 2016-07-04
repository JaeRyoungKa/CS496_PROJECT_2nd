package com.example.q.cs496_prj1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by q on 2016-06-30.
 */
public class TodoAddActivity  extends FragmentActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_add);

    }

    public void todo_send(View v) {
        String todo = ((EditText) findViewById(R.id.todo)).getText().toString();
        SimpleDateFormat asdf = new SimpleDateFormat("yyyy/dd/MM");
        CalendarView calendar = (CalendarView) findViewById(R.id.duedate);
        String dd = asdf.format(new Date(calendar.getDate()));
        if (todo.length() == 0 || dd.length() == 0){
            Snackbar.make(v, "Please fill out the requisite.", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            return;
        }
        JSONObject jObj = new JSONObject();
        try {
            jObj.put("todo", todo);
            jObj.put("duedate", dd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Bundle extra = new Bundle();
        Intent intent = new Intent();
        extra.putString("todo", todo);
        extra.putString("duedate", dd);
        intent.putExtras(extra);
                /* Write to file */
        setResult (1, intent);
        finish();
    }

    public void todo_cancel(View v) {
        this.onBackPressed();
    }
}