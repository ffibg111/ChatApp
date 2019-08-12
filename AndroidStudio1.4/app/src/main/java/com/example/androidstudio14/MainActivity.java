package com.example.androidstudio14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                EditText firstNumberEditText = findViewById(R.id.firstNumEditText);
                EditText secondNumberEditText = findViewById(R.id.secondNumEditText);
                TextView resultTextView = findViewById(R.id.resultTextView);

                int intFirstNumber = Integer.parseInt(firstNumberEditText.getText().toString());
                int intSecondNumberEditText = Integer.parseInt(secondNumberEditText.getText().toString());
                int intResultTextView = intFirstNumber + intSecondNumberEditText;

                resultTextView.setText(intResultTextView + "");

            }
        });

    }
}
