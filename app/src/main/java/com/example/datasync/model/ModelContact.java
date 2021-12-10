package com.example.datasync.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelContact {
    @SerializedName("contactName")
    @Expose
    private String contactName;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;

    public ModelContact(String contactName, String emailId, String mobileNo) {
        this.contactName = contactName;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
    }



    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

}
