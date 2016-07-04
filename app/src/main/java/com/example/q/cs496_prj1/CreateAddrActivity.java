package com.example.q.cs496_prj1;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;

public class CreateAddrActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_addr);
        Button bt1 = (Button) findViewById(R.id.AddBtn1);
        EditText ed1 = (EditText) findViewById(R.id.add_num);
        ed1.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String name = ((EditText) findViewById(R.id.add_name)).getText().toString();
                String num = ((EditText) findViewById(R.id.add_num)).getText().toString();
                if (name.length() == 0 || num.length() == 0){
                    Snackbar.make(v, "Please fill the name or phone number", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    return;
                }
                JSONObject jObj = new JSONObject();
                try {
                    jObj.put("name", name);
                    jObj.put("addr", num);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Bundle extra = new Bundle();
                Intent intent = new Intent();
                extra.putString("name", name);
                extra.putString("num", num);
                intent.putExtras(extra);
                /* Write to file */
                setResult (1, intent);
                finish();
            }
        });
    }
}