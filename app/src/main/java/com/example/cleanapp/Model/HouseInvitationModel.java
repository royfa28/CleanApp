package com.example.cleanapp.Model;

public class HouseInvitationModel {

    //owner info
    String idHouse,idOwner;

    //boolean isRead
    Boolean isRead =false;

    //constructor
    public HouseInvitationModel() {
    }

    //GETTER SETTER

    public String getIdHouse() {
        return idHouse;
    }

    public void setIdHouse(String idHouse) {
        this.idHouse = idHouse;
    }

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public Boolean getisRead() {
        return isRead;
    }

    public void setisRead(Boolean read) {
        isRead = read;
    }
}
