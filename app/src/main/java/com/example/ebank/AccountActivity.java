package com.example.ebank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountActivity extends AppCompatActivity {
    Button Back;

    TextView number,bal,greetwithname;

    RecyclerView history;
    myadapter adapter;
    public DatabaseReference Userreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Back=findViewById(R.id.Back);
        number=findViewById(R.id.number);
        bal=findViewById(R.id.bal);
        greetwithname=findViewById(R.id.greetwithname);
        Intent iNtnent = getIntent();
        String loggin_id = iNtnent.getStringExtra("log_id");
        FetchDataFromFirebase(loggin_id);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AccountActivity.this,MainActivity.class);
                startActivity(intent);
            }

        });

        history=(RecyclerView)findViewById(R.id.history);
        history.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions <model> options=
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User_detail").child(loggin_id).child("Transction"),model.class)
                        .build();


        adapter=new myadapter(options);
        history.setAdapter(adapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    public void FetchDataFromFirebase(String loggin_id) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User_detail");
        reference.orderByChild("account_no");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String accountNo = userSnapshot.getKey();
                    if (accountNo.equals(loggin_id)) {
                         Toast.makeText(AccountActivity.this, "line 101", Toast.LENGTH_SHORT).show();
                        String name = userSnapshot.child("name").getValue(String.class);
                        String account_no = userSnapshot.child("account_no").getValue(String.class);
                        String balance = userSnapshot.child("balance").getValue(String.class);
                        float ac_bal= Float.parseFloat(balance);
                        greetwithname.setText(" Hello "+name);
                        number.setText(account_no);
                        bal.setText(" ₹ "+ac_bal);
                        break; // Exit the loop since we found the desired record
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Getting data failed
                Toast.makeText(AccountActivity.this, "Failed to load data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}