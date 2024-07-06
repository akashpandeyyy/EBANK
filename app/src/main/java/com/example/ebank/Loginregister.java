package com.example.ebank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Loginregister extends AppCompatActivity {

    Button Login,Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginregister);
       Login=findViewById(R.id.Login);
       Login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(Loginregister.this,Login.class);
               startActivity(intent);
           }
       });
        Register=findViewById(R.id.Register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Loginregister.this,Register.class);
                startActivity(intent);
            }
        });

    }
}

