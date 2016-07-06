package com.example.q.cs496_prj1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by q on 2016-07-07.
 */
public class URL extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        TextView textview = (TextView) findViewById(R.id.url);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        textview.setText(url);
    }
}
