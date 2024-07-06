package com.example.ebank;

import static com.google.android.material.color.utilities.MaterialDynamicColors.error;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.ClosedSubscriberGroupInfo;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashMap;

import kotlin.random.Random;

public class CardActivity extends AppCompatActivity {
    Button Back1,Change_pin;
    TextView card_number,card_cvv,card_exp,cardholder_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        //Recive current login user id from Login Activity
        Intent iNtent=getIntent();
        String login_id=iNtent.getStringExtra("log_id");


        //Find the id
        Back1=findViewById(R.id.Back1);
        Change_pin=findViewById(R.id.Change_Pin);

        card_number =findViewById(R.id.card_number);
        card_cvv=findViewById(R.id.card_cvv);
        card_exp=findViewById(R.id.card_exp);
        cardholder_name=findViewById(R.id.cardholder_name);

        Back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CardActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



       Change_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardActivity.this, change_pin.class);
                intent.putExtra("login_id",login_id);

                startActivity(intent);
                finish();
            }

        });




        FetchDataFromFirebase(login_id);

    }

    public void FetchDataFromFirebase(String login_id) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User_detail").child(login_id);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(CardActivity.this, "75", Toast.LENGTH_SHORT).show();
                if (snapshot.exists()) {
                    // Toast.makeText(CardActivity.this, "line 104", Toast.LENGTH_SHORT).show();
                    String name=snapshot.child("name").getValue(String.class);
                    String cardNum = snapshot.child("card").child("card_num").getValue(String.class);
                    String cardCvv = snapshot.child("card").child("card_cvv").getValue(String.class);
                    String cardexp = snapshot.child("card").child("card_exp").getValue(String.class);

                    try {
                   //     Toast.makeText(CardActivity.this, "109", Toast.LENGTH_SHORT).show();
                        card_number.setText("4048 54"+cardNum);
                    //    Toast.makeText(CardActivity.this, "111", Toast.LENGTH_SHORT).show();
                        card_cvv.setText("CVV "+cardCvv);
                        card_exp.setText("MM/YY  "+"12/"+cardexp);
                        cardholder_name.setText(name);
                    } catch (Exception e) {

                        Toast.makeText(CardActivity.this, "No data load for display", Toast.LENGTH_SHORT).show();

                    }
                } else {
////                    // Data does not exist
                    Toast.makeText(CardActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                }



            }
            @Override
            public void onCancelled (@NonNull DatabaseError error){
                Toast.makeText(CardActivity.this, "Failed to Show Account detail Please try again later: " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });






    }




}





