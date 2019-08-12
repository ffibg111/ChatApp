package com.example.androidstudio14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // initialize textView, radio group & buttons
    TextView resultTextView;
    TextView resultOpsTextView;
    RadioGroup groupMathOpsButton;
    RadioButton radioMathOpsButton;

    // initialize basic math operator buttons
    Button additionButton;
    Button subtractButton;
    Button multiplyButton;
    Button divideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set basic math operator buttons
        additionButton = findViewById(R.id.additionButton);
        subtractButton = findViewById(R.id.subtractButton);
        multiplyButton = findViewById(R.id.multiplyButton);
        divideButton = findViewById(R.id.divideButton);

        // set result textView for both final values and operator symbols
        resultTextView = findViewById(R.id.resultTextView);
        resultOpsTextView = findViewById(R.id.mathOpsTextView);

        // set text fields
        final EditText firstNumberEditText = findViewById(R.id.firstNumEditText);
        final EditText secondNumberEditText = findViewById(R.id.secondNumEditText);

        // construct and initializing radio button for auto-clicker
        groupMathOpsButton = findViewById(R.id.radioMathOpsGroup);
        int getRadioGroupId = groupMathOpsButton.getCheckedRadioButtonId();
        radioMathOpsButton = findViewById(getRadioGroupId);

        // android button auto clicker
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // perform auto-click on the default "checked" addition radio button
                radioMathOpsButton.performClick();
            }
        }, 001);

        additionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // filter input values
                int intFirstNumber = Integer.parseInt(firstNumberEditText.getText().toString());
                int intSecondNumberEditText = Integer.parseInt(secondNumberEditText.getText().toString());
                // perform addition
                int intResultTextView = intFirstNumber+intSecondNumberEditText;
                // set final value in result view
                resultTextView.setText(intResultTextView + "");
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // filter input values
                int intFirstNumber = Integer.parseInt(firstNumberEditText.getText().toString());
                int intSecondNumberEditText = Integer.parseInt(secondNumberEditText.getText().toString());
                // perform subtraction
                int intResultTextView = intFirstNumber-intSecondNumberEditText;
                // set final value in result view
                resultTextView.setText(intResultTextView + "");
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // filter input values
                int intFirstNumber = Integer.parseInt(firstNumberEditText.getText().toString());
                int intSecondNumberEditText = Integer.parseInt(secondNumberEditText.getText().toString());
                // perform multiplication
                int intResultTextView = intFirstNumber*intSecondNumberEditText;
                // set final value in result view
                resultTextView.setText(intResultTextView + "");
            }
        });

        // set onClick listener for Divide button
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // filter input values
                int intFirstNumber = Integer.parseInt(firstNumberEditText.getText().toString());
                int intSecondNumberEditText = Integer.parseInt(secondNumberEditText.getText().toString());

                // non zero check for division
                if(intSecondNumberEditText==0&&intFirstNumber>0
                    || intSecondNumberEditText==0&&intFirstNumber==0){
                    resultTextView.setText("Oops!");
                } else {
                    // perform division
                    int intResultTextView = intFirstNumber/intSecondNumberEditText;
                    // set final value in result view
                    resultTextView.setText(intResultTextView + ""); }
            }
        });
    }

    // create radio button onClick event
    public void onRadioButtonClicked(View view) {

        // hide clickable buttons
        additionButton.setVisibility(View.INVISIBLE);
        subtractButton.setVisibility(View.INVISIBLE);
        multiplyButton.setVisibility(View.INVISIBLE);
        divideButton.setVisibility(View.INVISIBLE);

        // declare checked radio button
        boolean checked = ((RadioButton) view).isChecked();

        // construct if radio button is clicked, set buttons visibility
        // and set math operators for each radio buttons
        switch(view.getId()) {
            case R.id.addRadioButton:
                additionButton.setVisibility(View.VISIBLE);
                if (checked)
                    resultOpsTextView.setText("+");
                break;
            case R.id.subRadioButton:
                subtractButton.setVisibility(View.VISIBLE);
                if (checked)
                    resultOpsTextView.setText("-");
                break;
            case R.id.multiplyRadioButton:
                multiplyButton.setVisibility(View.VISIBLE);
                if (checked)
                    resultOpsTextView.setText("x");
                break;
            case R.id.divideRadioButton:
                divideButton.setVisibility(View.VISIBLE);
                if (checked)
                    resultOpsTextView.setText("÷");
                break;
        }
    }
}
