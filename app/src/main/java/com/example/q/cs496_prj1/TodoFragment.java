package com.example.q.cs496_prj1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by q on 2016-06-30.
 */
public class TodoFragment extends Fragment {
    private final static int REQUEST_ADD = 1234;
    ArrayList<Todo> todo_all;
    ArrayList<Todo> todo_list;
    TodoAdapter todo_adapter;

    public void TodoFragment (){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /* Inflate view group and initialize memeber variables */
        View rootView = inflater.inflate(R.layout.todo_fragment, container, false);

        todo_list = new ArrayList<Todo>();
        todo_all = new ArrayList<Todo>();
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.todo_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        /* Bind click event to floating button */
        FloatingActionButton button = (FloatingActionButton) rootView.findViewById(R.id.todo_fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodoFragment.this.getActivity(), TodoAddActivity.class);
                startActivityForResult(intent, REQUEST_ADD);
            }
        });

        String jsonString = null;
        try {
            File todo_file = new File(getContext().getFilesDir(),
                    getString(R.string.todo_file_name));
            if (!todo_file.exists()) {
                todo_file.createNewFile();
                FileOutputStream fos = new FileOutputStream(todo_file);
                fos.write("[]".getBytes());
                fos.close();
            }
            InputStream is;
            is = getContext().openFileInput(getString(R.string.todo_file_name));
            if (is != null) {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String recieve = "";
                StringBuilder sb = new StringBuilder();

                while ((recieve = br.readLine()) != null) {
                    sb.append(recieve);
                }

                is.close();
                jsonString = sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Todo td = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jObj_temp = jsonArray.getJSONObject(i);
                String work = jObj_temp.getString("work");
                String due = jObj_temp.getString("due");
                boolean is_complete = jObj_temp.getBoolean("is_complete");
                Todo todo = new Todo(work, due, is_complete);
                todo_all.add(todo);
                if (!todo.get_is_complete())
                    todo_list.add(todo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        todo_adapter = new TodoAdapter(getActivity().getApplicationContext(), R.layout.todo_card, todo_list, todo_all);
        recyclerView.setAdapter(todo_adapter);

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_ADD) {
            if (resultCode == 1) {
                String todo = intent.getStringExtra("todo");
                String due = intent.getStringExtra("duedate");
                Todo todo_new = new Todo(todo, due);
                todo_list.add(todo_new);
                todo_all.add(todo_new);
                todo_adapter.notifyItemInserted(todo_list.size() - 1);
                Snackbar.make(getView(), "Add todo success", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }

    @Override
    public void onDestroyView() {

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < todo_all.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("work", todo_all.get(i).get_work());
                jsonObject.put("due", todo_all.get(i).get_due_date());
                jsonObject.put("is_complete", todo_all.get(i).get_is_complete());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
        }

        File todo_file = new File(getContext().getFilesDir(),
                getString(R.string.todo_file_name));
        try {
            if (!todo_file.exists())
                todo_file.createNewFile();

            FileOutputStream fos = new FileOutputStream(todo_file);
            fos.write(jsonArray.toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.onStop();
    }

}
