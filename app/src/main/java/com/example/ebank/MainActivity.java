package com.example.ebank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ebank.Profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    Button Send_money, Account, cards, Travel;
    ImageButton Profile;
    TextView name,morning_ev,ac_number,accountbal;
    public    DatabaseReference Userreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Recive current login user id from Login Activity
        Intent iNtent=getIntent();
        String log_id=iNtent.getStringExtra("Userid");
        name= findViewById(R.id.name);
        morning_ev = findViewById(R.id.morning_ev);
        ac_number = findViewById(R.id.ac_number);
        accountbal = findViewById(R.id.accountbal);
        //function for greating masseage
        greetingMessage();
        //fetch data from firebase
        FetchDataFromFirebase(log_id);
        Account = findViewById(R.id.Account);
        Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                //pass currtent login userid to another activity
                intent.putExtra("log_id",log_id);
                startActivity(intent);
            }
        });
        cards = findViewById(R.id.cards);
        cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CardActivity.class);
                //pass currtent login userid to another activity
                intent.putExtra("log_id",log_id);
                startActivity(intent);
            }
        });
        Travel = findViewById(R.id.Travel);
        Travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TravelActivity.class);
                //pass currtent login userid to another activity
                intent.putExtra("log_id",log_id);
                startActivity(intent);
            }
        });
        Profile = findViewById(R.id.Profile);
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(com.example.ebank.MainActivity.this, "line 97", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Profile.class);
                //pass currtent login userid to another activity
                intent.putExtra("log_id",log_id);
                startActivity(intent);
            }
        });
        Send_money = findViewById(R.id.Send_money);
        Send_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SendmoneyActivity2.class);
                //pass currtent login userid to another activity
                intent.putExtra("log_id",log_id);
                startActivity(intent);
            }
        });
    }
    public void greetingMessage() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define date time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Format and print current date and time
        String formattedDateTime = currentDateTime.format(formatter);
        String Transction_time = String.valueOf(formattedDateTime);
        System.out.println("Current Date and Time: " + Transction_time);
        int hour = currentDateTime.getHour();
        if (hour >= 5 && hour < 12) {
            //System.out.println("Good Morning!");
            morning_ev.setText("Good Morning!");
        } else if (hour >= 12 && hour < 17) {
           // System.out.println("Good Afternoon!");
            morning_ev.setText("Good Afternoon");
        } else {
           // System.out.println("Good Evening!");
            morning_ev.setText("Good Evening!");
        }
    }
    public void FetchDataFromFirebase(String log_id ) {
        Userreference = FirebaseDatabase.getInstance().getReference().child("User_detail");
        Userreference.orderByChild("account_no");
        Userreference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String accountNo = userSnapshot.getKey();
                    if (accountNo.equals(log_id))
                    {
                        String acc_number = userSnapshot.child("account_no").getValue(String.class);
                        String account_balance = userSnapshot.child("balance").getValue(String.class);
                        String account_name = userSnapshot.child("account_holder_name").getValue(String.class);
                        float balance = Float.parseFloat(account_balance);
                        accountbal.setText("Account balance\n₹" + balance);
                        ac_number.setText( acc_number);
                        name.setText( account_name);
                        break; // Exit the loop since we found the desired record
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Getting data failed
                Toast.makeText(MainActivity.this, "Failed to Show Account detail Please try again later: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}