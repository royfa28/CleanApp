package com.example.cleanapp.Model;

public class UserModel {
    Boolean userLvl;
    String userMail;
    String userPhone;
    String userKey;
    String userFullName;

    //Constructor
    public UserModel() {
    }

    public UserModel(String userMail, String userPhone) {
        this.userMail = userMail;
        this.userPhone = userPhone;

    }

    //GETTER SETTER
    public Boolean getUserLvl() {
        return userLvl;
    }

    public void setUserLvl(Boolean userLvl) {
        this.userLvl = userLvl;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
}
