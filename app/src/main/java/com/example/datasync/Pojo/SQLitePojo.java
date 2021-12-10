package com.example.datasync.Pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SQLitePojo {

    @SerializedName("contactList")
    @Expose
    private List<Contact> contactList = null;

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public class Contact {

        @SerializedName("contactName")
        @Expose
        private String contactName;
        @SerializedName("emailId")
        @Expose
        private String emailId;
        @SerializedName("mobileNo")
        @Expose
        private String mobileNo;

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
}