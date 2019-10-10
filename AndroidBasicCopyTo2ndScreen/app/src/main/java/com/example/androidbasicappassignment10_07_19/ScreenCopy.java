package com.example.androidbasicappassignment10_07_19;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class ScreenCopy extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_copy);
        Button backToMainBtn = findViewById(R.id.backToMainScrnButton);
        ImageView secondScrnImageView = findViewById(R.id.secondImageView);
        final TextView headerTxtView = findViewById(R.id.secondScrnHeadLineTextView);
        final TextView contentTxtView = findViewById(R.id.secondScrnContentTextView);
        // Get the transferred data from source activity.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String headerMsg = extras.getString("headerMsg");
        String contentMsg = extras.getString("contentMsg");
        String image = extras.getString("image");
        String imageUrl = ImageConnection.getURLForResource(R.drawable.turkey);
        // Load image
        Glide.with(secondScrnImageView.getContext())
                .load(imageUrl)
                .into(secondScrnImageView);
        headerTxtView.setText(headerMsg);
        contentTxtView.setText(contentMsg);
        backToMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
