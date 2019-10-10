package com.example.androidbasicappassignment10_07_19;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button copyBtn = findViewById(R.id.backToMainScrnButton);
        final ImageView mainImageView = findViewById(R.id.mainImageView);
        final TextView headerTxtView = findViewById(R.id.mainHeadLineTextView);
        final TextView contentTxtView = findViewById(R.id.mainContentTextView);
        copyBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScreenCopy.class);
                Bundle extras = new Bundle();
                String imageUrl = ImageConnection.getURLForResource(R.drawable.turkey);
                extras.putString("image", imageUrl);
                extras.putString("headerMsg", headerTxtView.getText().toString());
                extras.putString("contentMsg",contentTxtView.getText().toString());
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}
