package com.example.ebank;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ebank.Loginregister;
import com.example.ebank.Register;
import com.example.ebank.Login;
import com.example.ebank.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    ImageButton logout;
    TextView User_name1, User_fathername1, User_phone_no1, User_email1, User_dob1, User_address1, User_gender1, User_addhar1, account_no1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //Recive current login user id from Login Activity
        Intent iNtent=getIntent();
        String login_id=iNtent.getStringExtra("log_id");
        // Finding the views
        User_name1 = findViewById(R.id.User_name1);
        User_fathername1 = findViewById(R.id.User_Fathername1);
        User_dob1 = findViewById(R.id.User_dob1);
        User_phone_no1 = findViewById(R.id.User_phone_no1);
        account_no1 = findViewById(R.id.account_no1);
        User_email1 = findViewById(R.id.User_email1);
        User_addhar1 = findViewById(R.id.User_addhar1);
        User_gender1 = findViewById(R.id.User_gender1);
        User_address1 = findViewById(R.id.User_address1);
        // Function for fetching data from db
        FetchDataFromFirebase(login_id);
        //  Logout button
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
                @Override
                   public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Loginregister.class);
                startActivity(intent);
                    finish();
                Toast.makeText(Profile.this, "Logout ", Toast.LENGTH_SHORT).show();
               finish();
                 }
         });
    }
    public void FetchDataFromFirebase(String login_id) {
  DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User_detail");
         reference.orderByChild("account_no");
         reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(Profile.this, "line 94", Toast.LENGTH_SHORT).show();
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String accountNo = userSnapshot.getKey();
                    if (accountNo.equals(login_id)) {
                         String name = userSnapshot.child("name").getValue(String.class);
                        String fathername = userSnapshot.child("father_name").getValue(String.class);
                        String mobile_no = userSnapshot.child("phone_no").getValue(String.class);
                        String email = userSnapshot.child("email").getValue(String.class);
                        String account_no = userSnapshot.child("account_no").getValue(String.class);
                        String dob = userSnapshot.child("dob").getValue(String.class);
                        String address = userSnapshot.child("address").getValue(String.class);
                        String gender = userSnapshot.child("gender").getValue(String.class);
                        String addhar = userSnapshot.child("addhar").getValue(String.class);
                        User_name1.setText(name);
                        Toast.makeText(Profile.this, "line 143", Toast.LENGTH_SHORT).show();
                        User_fathername1.setText(fathername);
                        User_dob1.setText(dob);
                        User_phone_no1.setText(mobile_no);
                        account_no1.setText(account_no);
                        User_email1.setText(email);
                        User_addhar1.setText(addhar);
                        User_gender1.setText(gender);
                        User_address1.setText(address);
                        break; // Exit the loop since we found the desired record
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Getting data failed
                Toast.makeText(Profile.this, "Failed to load data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}