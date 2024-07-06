package com.example.ebank;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.common.subtyping.qual.Bottom;

import java.util.HashMap;

public class change_pin extends AppCompatActivity {


    EditText re_pin;
    Button Change_pin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);


        // find the all id
        Change_pin = findViewById(R.id.Change_pin);
        re_pin = findViewById(R.id.re_pin);
        Intent itent=getIntent();
        String loggin_id=itent.getStringExtra("login_id");
        Change_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String new_pin = re_pin.getText().toString().trim();

                if (!new_pin.isEmpty()) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();


                    // Reference to the database node where data will be updated
                    DatabaseReference myRef = database.getReference("User_detail").child(loggin_id);


                    HashMap hashMap = new HashMap();

                    hashMap.put("pin", new_pin);
                    myRef.updateChildren(hashMap);
                    Toast.makeText(change_pin.this, "Pin Change Sucessfully....!!", Toast.LENGTH_SHORT).show();

                    // Start back activity after a short delay
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(change_pin.this, CardActivity.class));
                            finish();
                        }
                    }, 5000); // Delay for 5 seconds
                } else {
                    Toast.makeText(change_pin.this, "Fill all the field", Toast.LENGTH_SHORT).show();
                }


          }

        });




    }


}
