package com.bignerdranch.android.sportsgbt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton buttonImage1, buttonImage2, buttonImage3, buttonImage4, buttonImage5, buttonImage6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize ImageButtons
        buttonImage1 = findViewById(R.id.buttonImage1);
        buttonImage2 = findViewById(R.id.buttonImage2);
        buttonImage3 = findViewById(R.id.buttonImage3);
        buttonImage4 = findViewById(R.id.buttonImage4);
        buttonImage5 = findViewById(R.id.buttonImage5);
        buttonImage6 = findViewById(R.id.buttonImage6);

        // Set click listeners for each ImageButton
        buttonImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sportsPersonalityName = ((TextView) findViewById(R.id.textView1)).getText().toString();
                startChatActivity(sportsPersonalityName);
            }
        });
    }
    // Method to start the chat activity
    private void startChatActivity(String sportsPersonalityName) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("SPORTS_PERSONALITY", sportsPersonalityName);
        startActivity(intent);
    }
}