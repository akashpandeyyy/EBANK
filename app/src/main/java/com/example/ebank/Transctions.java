package com.example.ebank;

public class Transctions {
    public String transction_time;
    public String transction_to;
    public String transction_from;
    public String transction_amount;
    public String transction_id;
//Constructor
    public Transctions(String transction_time, String transction_to, String transction_from, String transction_amount, String transction_id) {
        this.transction_time = transction_time;
        this.transction_to = transction_to;
        this.transction_from = transction_from;
        this.transction_amount = transction_amount;
        this.transction_id=transction_id;
    }
    //Empty Constructor
    public Transctions() {}
    public String getTransction_amount() {
        return transction_amount;
    }
    public void setTransction_amount(String transction_amount) {this.transction_amount = transction_amount;}
    public String getTransction_time() {
        return transction_time;
    }
    public void setTransction_time(String transction_time) {this.transction_time = transction_time;}
    public String getTransction_to() {
        return transction_to;
    }
    public void setTransction_to(String transction_to) {
        this.transction_to = transction_to;
    }
    public String getTransction_from() {
        return transction_from;
    }
    public void setTransction_from(String transction_from) {this.transction_from = transction_from;}
    public String getTransction_id() {
        return transction_id;
    }
    public void setTransction_id(String transction_id) {
        this.transction_id = transction_id;
    }}