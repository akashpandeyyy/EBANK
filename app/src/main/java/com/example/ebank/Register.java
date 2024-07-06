package com.example.ebank;

import static android.app.ProgressDialog.show;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.lang.ref.Cleaner;
import java.time.LocalDate;

import java.util.Random;
public class Register extends AppCompatActivity {
    Button Login3,Register3;
    CountryCodePicker ccp;
   private EditText User_name,User_fathername,User_phone_no,User_pin,User_email,User_dob,User_address,User_gender,User_addhar;
   public    DatabaseReference Userreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Find id of Edittext field
        User_name=findViewById(R.id.User_name);
        User_fathername=findViewById(R.id.User_Fathername);
        User_dob=findViewById(R.id.User_dob);
        User_phone_no=findViewById(R.id.User_phone_no);
        User_pin=findViewById(R.id.User_pin);
        User_email=findViewById(R.id.User_email);
        User_addhar=findViewById(R.id.User_addhar);
        User_gender=findViewById(R.id.User_gender);
        User_address=findViewById(R.id.User_address);
        ccp=(CountryCodePicker)findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(User_phone_no);
        Login3=findViewById(R.id.Login3);
        Login3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
        Register3=findViewById(R.id.Register3);
        Register3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                Userreference = FirebaseDatabase.getInstance().getReference().child("User_detail");
               String account_holder_name = User_name.getText().toString().trim();
               String father_name  = User_fathername.getText().toString().trim();
               String dob = User_dob.getText().toString().trim();
               String phone_no =User_phone_no.getText().toString().trim();
               String pin =  User_pin.getText().toString().trim();
               String email = User_email.getText().toString().trim();
               String address = User_address.getText().toString().trim();
               String addhar = User_addhar.getText().toString().trim();
               String gender= User_gender.getText().toString().trim();
             //  int phone_num= Integer.parseInt(phone_no);
               String  account_no= ("459"+phone_no).toString().trim();
                String balance= ("5000").toString().trim();
                if (!account_holder_name.isEmpty() && !father_name.isEmpty()  && !dob.isEmpty()  && !phone_no.isEmpty() && !pin.isEmpty()  && !email.isEmpty()  && !address.isEmpty()  && !addhar.isEmpty()  && !gender.isEmpty())
                {
                    // Create a new User object
                    Userdata data=new Userdata(account_holder_name,account_no,balance,father_name,dob,phone_no,pin,email,address,addhar,gender); // Assuming User is your data model class
                    // Push the data to Firebase Realtime Database
                    Userreference.child(account_no).setValue(data);
                    // genrating cards value and number
                    // firebase database
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("User_detail").child(account_no).child("card");
                    //Transction id genrator using random function
                    Random random = new Random();
                    int randomNumber6 = 100000 + random.nextInt(900000);
                    int randomNumber3 = 100 + random.nextInt(900);
                    //System.out.println("Random 6-digit number: " + card_number);
                    String card_number= String.valueOf(randomNumber6);
                    String card_cvv= String.valueOf(randomNumber3);
                    LocalDate currentDate = LocalDate.now();
                    int currentYear = currentDate.getYear();
                    int card_exp = currentYear+5;
                    String card_expp= String.valueOf(card_exp);
                    Card_data carddata=new Card_data(card_number,card_cvv,card_expp);
                    // Push the data to Firebase Realtime Database
                    //reference.orderByChild("account_no");
                    reference.setValue(carddata);
                    Toast.makeText(Register.this, "Data Loaded in Firebase DB", Toast.LENGTH_SHORT).show();
                    //Switch to another activity
                    Intent intent=new Intent(Register.this,ValidationActivity.class);
                    intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                    startActivity(intent);
                } else {
                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}