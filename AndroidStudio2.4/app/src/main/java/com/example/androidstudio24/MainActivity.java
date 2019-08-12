package com.example.androidstudio24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pass string to new second activity
        Button secondActivity = (Button) findViewById(R.id.secondActButton);
        secondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),SecondActivity.class);
                startIntent.putExtra("pushInfo", "Hello IBG Fast Track!");
                startActivity(startIntent);
            }
        });

        // launch activity out side of our app
        Button google = (Button) findViewById(R.id.googleButton);
        google.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String google = "http://www.google.com";
                Uri googleURL = Uri.parse(google);
                Intent gotoGoogle = new Intent(Intent.ACTION_VIEW, googleURL);
                if (gotoGoogle.resolveActivity(getPackageManager()) != null ) {
                    startActivity(gotoGoogle);
                }
            }
        });

        Button yahoo = (Button) findViewById(R.id.yahooButton);
        yahoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yahoo = "http://www.yahoo.com";
                Uri yahooURL = Uri.parse(yahoo);
                Intent gotoYahoo = new Intent(Intent.ACTION_VIEW, yahooURL);
                if (gotoYahoo.resolveActivity(getPackageManager()) != null ) {
                    startActivity(gotoYahoo);
                }
            }

        });
    }
}
