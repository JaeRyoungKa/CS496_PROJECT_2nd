package com.example.q.cs496_prj1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by q on 2016-06-29.
 */
public class Tab2_expand extends FragmentActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_expand);

        Intent intent=new Intent(this.getIntent());
        String s=intent.getStringExtra("address");
        ImageView imageview=(ImageView) findViewById(R.id.imageView);
        if (s.equals("1")) imageview.setImageResource(R.drawable.image1);
        if (s.equals("2")) imageview.setImageResource(R.drawable.image2);
        if (s.equals("3")) imageview.setImageResource(R.drawable.image3);
        if (s.equals("4")) imageview.setImageResource(R.drawable.image4);
        if (s.equals("5")) imageview.setImageResource(R.drawable.image5);
    }

    public void back(View v) {
        this.onBackPressed();
    }
}
