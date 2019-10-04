package com.example.datafireanyapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    Button showDataBtn;
    public static TextView showDataTxtView;
    GetData process = new GetData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showDataBtn = findViewById(R.id.showDataBtn);
        showDataTxtView = findViewById(R.id.showDataTxtView);
        showDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process.refresh();
            }
        });
    }
}