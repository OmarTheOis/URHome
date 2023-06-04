package com.example.urhome;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class profile_page extends AppCompatActivity {
      //private static final IMAGE_PICK_CODE =1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
         final ImageView img = findViewById(R.id.pickimg);
        final Button pic = findViewById(R.id.button6);

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(B)
            }
        });

    }
}