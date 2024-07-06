package com.example.ebank;
public class model {
    String Transction_time, Transction_to, Transction_from, Transction_amount,Transction_id,Transction_idd;
    //Constructor Empty
    public model() {
    }
    //Constructor
    public model(String transction_time, String transction_to, String transction_from, String transction_amount, String transction_id) {
        Transction_time = transction_time;
        Transction_to = transction_to;
        Transction_from = transction_from;
        Transction_amount = transction_amount;
        Transction_id = transction_id;
        Transction_idd=Transction_idd;
    }
    //Getter setter
    public String getTransction_time() {
        return Transction_time;
    }
    public String getTransction_idd() {
        return Transction_idd;
    }

    public void setTransction_idd(String transction_idd) {
        Transction_idd = transction_idd;
    }
    public void setTransction_time(String transction_time) {
        Transction_time = transction_time;
    }
    public String getTransction_to() {
        return Transction_to;
    }
    public void setTransction_to(String transction_to) {
        Transction_to = transction_to;
    }
    public String getTransction_from() {
        return Transction_from;
    }
    public void setTransction_from(String transction_from) {
        Transction_from = transction_from;
    }
    public String getTransction_amount() {
        return Transction_amount;
    }
    public void setTransction_amount(String transction_amount) {Transction_amount = transction_amount;}
    public String getTransction_id() {
        return Transction_id;
    }

    public void setTransction_id(String transction_id) {
        Transction_id = transction_id;
    }
}
