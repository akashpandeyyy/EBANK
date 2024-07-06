package com.example.ebank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import java.util.Random;

public class SendmoneyActivity2 extends AppCompatActivity {

    TextView account_number, acc_balance;
    Button Check, Pay;
    EditText Reciver_account_no, Re_Reciver_account_no, User_pin, Amount;

    public DatabaseReference Userreference;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmoney2);

        //Recive current login user id from Login Activity
        Intent iNtent = getIntent();
        String login_id = iNtent.getStringExtra("log_id");
        //Function for database
        FetchDataFromFirebase(login_id);
        // Finding the views

        Pay = findViewById(R.id.Pay);

        account_number = findViewById(R.id.account_number);
        acc_balance = findViewById(R.id.acc_balance);
        Reciver_account_no = findViewById(R.id.Reciver_account_no);
        Re_Reciver_account_no = findViewById(R.id.Re_Reciver_account_no);
        User_pin = findViewById(R.id.User_pin);
        Amount = findViewById(R.id.Amount);
        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //string
                final String reaccount_number = Reciver_account_no.getText().toString().trim();
                final String re_account_number = Re_Reciver_account_no.getText().toString().trim();
                final String send_ammount = Amount.getText().toString().trim();
                final String m_pin = User_pin.getText().toString().trim();


                Toast.makeText(SendmoneyActivity2.this, "64", Toast.LENGTH_SHORT).show();
                //if for fill all field

                if (!reaccount_number.isEmpty() && !re_account_number.isEmpty() && !send_ammount.isEmpty() && !m_pin.isEmpty()) {

                    //macting account number to re enter acccount number
                    if (reaccount_number.equalsIgnoreCase(re_account_number)) {
                        // Get a reference to the Firebase Realtime Database
                        //Create a refrence of Database
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User_detail");
                        // Toast.makeText(Profile.this, "line 88", Toast.LENGTH_SHORT).show();
                        reference.child("account_no");

                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Toast.makeText(SendmoneyActivity2.this, "line 90", Toast.LENGTH_SHORT).show();

                                if (snapshot.hasChild(re_account_number)) {
                                    Toast.makeText(SendmoneyActivity2.this, "data exist ", Toast.LENGTH_SHORT).show();


                                    String passwordfromdb = snapshot.child(login_id).child("pin").getValue(String.class);
                                    Toast.makeText(SendmoneyActivity2.this, "79", Toast.LENGTH_SHORT).show();
                                    if (passwordfromdb.equals(m_pin)) {
                                        Toast.makeText(SendmoneyActivity2.this, "82", Toast.LENGTH_SHORT).show();
                                        String Fundfromdb = snapshot.child(login_id).child("balance").getValue(String.class);
                                        String Fundfromdb_re = snapshot.child(reaccount_number).child("balance").getValue(String.class);
                                        float fund = Float.valueOf(Fundfromdb);
                                        float fund_re = Float.valueOf(Fundfromdb_re);
                                        float send_fund = Float.parseFloat(send_ammount);
                                        if (fund >= send_fund) {


                                            int send_bal = Integer.parseInt(send_ammount);
                                            int se_cu_bal = (int) (fund - send_bal);
                                            int re_cu_bal = (int) (fund_re + send_bal);
                                            String re_Cu_balance = String.valueOf(re_cu_bal);
                                            String se_Cu_balance = String.valueOf(se_cu_bal);
                                            Toast.makeText(SendmoneyActivity2.this, "Hold a minute and don't quit the app", Toast.LENGTH_SHORT).show();
                                            //update the balance in a/c of reciver and sender
                                            //HashMap updatedata = new HashMap();
                                            //  updatedata.put("balance", se_Cu_balance);

                                            Toast.makeText(SendmoneyActivity2.this, "Please Wait it take some time ", Toast.LENGTH_SHORT).show();

                                            updateData(login_id, reaccount_number, re_Cu_balance, se_Cu_balance );
                                            usertransaction(login_id, reaccount_number, send_ammount); //call after getting the refrence of db otherwise pass the refrence

                                            String re_Fundfromdb = snapshot.child(login_id).child("balance").getValue(String.class);
                                            String re_Fundfromdb_re = snapshot.child(reaccount_number).child("balance").getValue(String.class);


                                            // here we call sucess failure activity
                                            if (!re_Fundfromdb.equalsIgnoreCase(Fundfromdb) && !re_Fundfromdb_re.equalsIgnoreCase(Fundfromdb_re))
                                            {
                                                Toast.makeText(SendmoneyActivity2.this, "Payment Fail", Toast.LENGTH_SHORT).show();
                                                // stsatr activity
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        startActivity(new Intent(SendmoneyActivity2.this, Failpayment.class));

                                                        finish();
                                                    }
                                                }, 1000);

                                            }
                                           else{

                                                // stsatr activity
                                                Intent intEnt = new Intent(SendmoneyActivity2.this, Sucess_payment.class);
                                                //pass currtent login userid to another activity
                                               intEnt.putExtra("send_ammount",send_ammount);

                                                startActivity(intEnt);

                                            }

                                        } else {
                                            Toast.makeText(SendmoneyActivity2.this, "Iffucient Fund", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(SendmoneyActivity2.this, "Incorrect Pin", Toast.LENGTH_SHORT).show();
                                    }


                                } else {
                                    Toast.makeText(SendmoneyActivity2.this, "User not exist ", Toast.LENGTH_SHORT).show();
                                }
                            }
//


                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                // Getting data failed
                                Toast.makeText(SendmoneyActivity2.this, "Failed to load data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        Toast.makeText(SendmoneyActivity2.this, "account no. and Re account no. not match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SendmoneyActivity2.this, "Fill all field For making payment", Toast.LENGTH_SHORT).show();
                }


            }

        });
    }


    //Toast.makeText(SendmoneyActivity2.this, "Data Loaded in Firebase DB", Toast.LENGTH_SHORT).show();
    //Switch to another activity
    //  Intent intent=new Intent(Re.this, MainActivity.class);
    //startActivity(intent);


    //}


    public void FetchDataFromFirebase(String login_id) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User_detail");
        reference.orderByChild("account_no");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String accountNo = userSnapshot.getKey();
                    if (accountNo.equals(login_id)) {
                        // Toast.makeText(SendmoneyActivity2.this, "line 110", Toast.LENGTH_SHORT).show();
                        String acc_number = userSnapshot.child("account_no").getValue(String.class);
                        String account_balance = userSnapshot.child("balance").getValue(String.class);

                        float balance = Float.parseFloat(account_balance);


                        acc_balance.setText("Account balance\n₹" + balance);
                        account_number.setText("Account Number\n" + acc_number);

                        break; // Exit the loop since we found the desired record
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Getting data failed
                Toast.makeText(SendmoneyActivity2.this, "Failed to Show Account detail Please try again later: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    // Function For Usertranctions details
    public void usertransaction(String login_id, String re_acc_no, String amount) {

        // firebase database
        // FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference Userreference = FirebaseDatabase.getInstance().getReference().child("User_detail").child(login_id).child("Transction");

        //Transction id genrator using random function
        Random random;
        random = new Random();
        StringBuilder sb = new StringBuilder();

        // Generate the first 12 digits randomly
        for (int i = 0; i < 12; i++) {
            sb.append(random.nextInt(10));
        }

        // Append a random non-zero digit for the 13th digit
        sb.append(random.nextInt(9) + 1);

        long randomNumber = Long.parseLong(sb.toString());
      //  System.out.println("Random 13-digit number: " + randomNumber);


        // Output the generated random number
        String Transction_id = String.valueOf(randomNumber);
        // Get current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define date time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format and print current date and time
        String formattedDateTime = currentDateTime.format(formatter);
        String Transction_time = String.valueOf(formattedDateTime);

        // Transction details
        SendmoneyActivity2 transction = new SendmoneyActivity2();
        String Transction_to = re_acc_no;
        String Transction_from = login_id;
        String Transction_amount = (amount+"\t-");



        Transctions transction_data = new Transctions(Transction_time, Transction_to, Transction_from, Transction_amount,Transction_id); // Assuming User is your data model class



        // Push the data to Firebase Realtime Database
        Userreference.child(Transction_id).setValue(transction_data);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User_detail").child(login_id).child("Transction");
        reference.child(Transction_id).setValue(transction_data);

        //for recivert
        Transction_amount = ( amount+" \t+");
        Transctions reciver_transction_data = new Transctions(Transction_time, Transction_to, Transction_from, Transction_amount,Transction_id); // Assuming User is your data model class


        DatabaseReference re_reference = FirebaseDatabase.getInstance().getReference().child("User_detail").child(re_acc_no).child("Transction");
        re_reference.child(Transction_id).setValue(reciver_transction_data);

    }


    //login_id,reaccount_number,re_Cu_balance,se_Cu_balance


    public void updateData(String login_id, String reAccountNumber, String re_Cu_balance, String seCuBalance) {
        // Get instance of Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Reference to the database node where data will be updated
        DatabaseReference myRef = database.getReference("User_detail").child(login_id);
        DatabaseReference myRref = database.getReference("User_detail").child(reAccountNumber);

        HashMap hashMap = new HashMap();
        HashMap hashMap1 = new HashMap();
        hashMap.put("balance", re_Cu_balance);
        hashMap1.put("balance", seCuBalance);
        myRef.updateChildren(hashMap1);
        myRref.updateChildren(hashMap);
                /*.addOnSuccessListener(new OnSuccessListener<Void>() {

            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(SendmoneyActivity2.this, "Sender balance updated successfully", Toast.LENGTH_SHORT).show();

                // stsatr activity
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SendmoneyActivity2.this, Sucess_payment.class));

                        finish();
                    }
                }, 5000);


            }


        });*/


    }
}