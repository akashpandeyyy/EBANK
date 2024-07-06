package com.example.ebank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Sucess_payment extends AppCompatActivity {

    Button Done;
    TextView Tranction_ammount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess_payment);

        Tranction_ammount = findViewById(R.id.Tranction_ammount);
        Done = findViewById(R.id.Done);

        Intent intenT = getIntent();
        String send_ammount = intenT.getStringExtra("send_ammount");

        Tranction_ammount.setText(send_ammount);
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sucess_payment.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}