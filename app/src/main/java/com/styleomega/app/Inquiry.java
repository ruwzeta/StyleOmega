package com.styleomega.app; // Changed package

import java.io.Serializable;

/**
 * Created by User on 9/18/2017.
 */

public class Inquiry implements Serializable {
    private int InquiryId;
    private int Adid;
    private String prodName;
    private String username;
    private String InqDetails;

    public int getInquiryId() {
        return InquiryId;
    }

    public void setInquiryId(int inquiryId) {
        InquiryId = inquiryId;
    }

    public int getAdid() {
        return Adid;
    }

    public void setAdid(int adid) {
        Adid = adid;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInqDetails() {
        return InqDetails;
    }

    public void setInqDetails(String inqDetails) {
        InqDetails = inqDetails;
    }
}
