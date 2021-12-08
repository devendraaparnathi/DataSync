package com.example.datasync;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDataPojo {

    @SerializedName("contactId")
    @Expose
    private Integer contactId;
    @SerializedName("contactName")
    @Expose
    private String contactName;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;

    public UserDataPojo(Integer contactId, String contactName, String emailId, String mobileNo) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
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