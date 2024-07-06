package com.example.ebank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TravelActivity extends AppCompatActivity {
    ImageButton rail,flight;
    Button Back2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        rail = findViewById(R.id.rail);
        rail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.irctc.co.in/nget/train-search"));
                startActivity(intent);
            }
        });
        flight = findViewById(R.id.flight);
        flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.air.irctc.co.in/"));
                startActivity(intent);
            }
        });
        Back2=findViewById(R.id.Back2);
        Back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TravelActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}