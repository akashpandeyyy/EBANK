package com.example.ebank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.*;
import android.content.Intent;


public class SlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view to the splash screen layout
        setContentView(R.layout.activity_slash);

        // Start the main activity after a short delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SlashActivity.this, Loginregister.class));

                finish();
            }
        }, 5000); // Delay for 5 seconds
    }
}