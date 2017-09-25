package com.example.user.cb006789styleomega;

import java.io.Serializable;

/**
 * Created by User on 9/18/2017.
 */

public class CreditCardDet implements Serializable {
    private int tabledreditid;
    private String creditcadno;
    private String creditowner;
    private String expirydate;
    private String cardverification;
    private String shippingaddress;

    public int getTabledreditid() {
        return tabledreditid;
    }

    public void setTabledreditid(int tabledreditid) {
        this.tabledreditid = tabledreditid;
    }

    public String getCreditcadno() {
        return creditcadno;
    }

    public void setCreditcadno(String creditcadno) {
        this.creditcadno = creditcadno;
    }

    public String getCreditowner() {
        return creditowner;
    }

    public void setCreditowner(String creditowner) {
        this.creditowner = creditowner;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getCardverification() {
        return cardverification;
    }

    public void setCardverification(String cardverification) {
        this.cardverification = cardverification;
    }

    public String getShippingaddress() {
        return shippingaddress;
    }

    public void setShippingaddress(String shippingaddress) {
        this.shippingaddress = shippingaddress;
    }
}
