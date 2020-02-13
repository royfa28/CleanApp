package com.example.cleanapp.Model;

public class TaskAssignCardModel {
    String TenantName, TenantNumber, RoomName, roomDescription, roomID;
    Boolean isDone;

    //constructor

    public TaskAssignCardModel() {
    }
    //getter setter


    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public Boolean getisDone() {
        return isDone;
    }

    public void setisDone(Boolean done) {
        isDone = done;
    }

    public String getTenantName() {
        return TenantName;
    }

    public void setTenantName(String tenantName) {
        TenantName = tenantName;
    }

    public String getTenantNumber() {
        return TenantNumber;
    }

    public void setTenantNumber(String tenantNumber) {
        TenantNumber = tenantNumber;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }
}