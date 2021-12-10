package com.example.datasync.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainModel {
    @SerializedName("contactList")
    @Expose
    private List<ModelContact> contactList = null;

    public List<ModelContact> getContactList() {
        return contactList;
    }

    public void setContactList(List<ModelContact> contactList) {
        this.contactList = contactList;
    }
}
