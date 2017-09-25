package com.example.user.cb006789styleomega;

import java.io.Serializable;

/**
 * Created by User on 9/18/2017.
 */

public class Cart implements Serializable {
    private int checkId;

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getItemprice() {
        return itemprice;
    }

    public void setItemprice(double itemprice) {
        this.itemprice = itemprice;
    }

    private int itemid;
    private String itemname;
    private String username;

    public Cart() {
    }

    private double itemprice;

}
