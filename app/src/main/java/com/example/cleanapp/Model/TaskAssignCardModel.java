package com.example.cleanapp.Model;

public class TaskAssignCardModel {
    String TenantName, TenantNumber, RoomName, roomDescription;

    //constructor

    public TaskAssignCardModel() {
    }
    //getter setter


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