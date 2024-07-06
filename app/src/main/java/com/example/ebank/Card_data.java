package com.example.ebank;

public class Card_data {

    public String  card_num;

   public String card_cvv;

    public String card_exp;


    // constructor
    public Card_data(String card_num, String card_cvv, String card_exp) {
        this.card_num = card_num;
        this.card_cvv = card_cvv;
        this.card_exp = card_exp;
    }

    public Card_data() {

    }

    //getter &  setter

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getCard_cvv() {
        return card_cvv;
    }

    public void setCard_cvv(String card_cvv) {
        this.card_cvv = card_cvv;
    }

    public String getCard_exp() {
        return card_exp;
    }

    public void setCard_exp(String card_exp) {
        this.card_exp = card_exp;
    }
}
