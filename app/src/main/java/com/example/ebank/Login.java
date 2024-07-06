package com.example.ebank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;
import com.example.ebank.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button Register2, Login2;
    EditText User_id, User_pin2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        User_id = findViewById(R.id.User_id);
        User_pin2 = findViewById(R.id.User_pin2);
        Register2 = findViewById(R.id.Register2);
        Register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        Login2 = findViewById(R.id.Login2);
        Login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Userid = User_id.getText().toString().trim();
                final String Userpin = User_pin2.getText().toString().trim();
                DatabaseReference Userreference = FirebaseDatabase.getInstance().getReference().child("User_detail");

                Query checkUser_detail = Userreference.orderByChild("account_no").equalTo(Userid);
                checkUser_detail.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String passwordfromdb = snapshot.child(Userid).child("pin").getValue(String.class);
                            if (passwordfromdb.equals(Userpin)) {
                                Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                //pass currtent login userid to another activity
                                intent.putExtra("Userid",Userid);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Login.this, "User not found click to register ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Login.this, "Database Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
