package com.example.cleanapp.Model;

public class User {
    Boolean userLvl;
    String userMail;
    String userPhone;

    //Constructor
    public User() {
    }

    public User(String userMail, String userPhone) {
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

}
