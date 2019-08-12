package com.example.androidstudio24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if(getIntent().hasExtra("pushInfo")) {
            TextView showPushInfo = (TextView) findViewById(R.id.showPushInfoTextView);
            String text = getIntent().getExtras().getString("pushInfo");
            showPushInfo.setText(text);
        }
    }
}
