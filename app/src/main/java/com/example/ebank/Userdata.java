package com.example.ebank;

public class Userdata {
     private String account_holder_name;
     private String father_name;
     private String dob;
    private String phone_no;
    private String pin;
    private String email;
    private String address;
    private String addhar;
    public String account_no;

    public String balance;
    private String gender;




    // Constructor
    public Userdata(String account_holder_name, String account_no,String balance,String father_name, String dob, String phone_no, String pin, String email, String address, String addhar, String gender) {
        this.account_holder_name = account_holder_name;
        this.account_no = account_no;
        this.father_name = father_name;
        this.dob = dob;
        this.phone_no = phone_no;
        this.pin = pin;
        this.balance=balance;
        this.email = email;
        this.address = address;
        this.addhar = addhar;
        this.gender = gender;
    }

    public Userdata() {

    }

 // Getter and setter

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
    public String getName() {
        return account_holder_name;
    }
    public void setName(String name) {
        this.account_holder_name = name;
    }
    public String getAccount_no() {
        return account_no;
    }
    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }
    public String getFather_name() {
        return father_name;
    }
    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public String getPhone_no() {
        return phone_no;
    }
    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddhar() {
        return addhar;
    }
    public void setAddhar(String addhar) {
        this.addhar = addhar;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}