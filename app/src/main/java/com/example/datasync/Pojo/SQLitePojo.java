package com.example.datasync.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SQLitePojo {

    @SerializedName("contactList")
    @Expose
    private List<Contact> contactList = null;

    public SQLitePojo(List<Contact> contactList) {
        this.contactList = contactList;
    }

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

        public Contact(String contactName, String emailId, String mobileNo) {
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

}
