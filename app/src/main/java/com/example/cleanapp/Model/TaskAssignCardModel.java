package com.example.cleanapp.Model;

public class TaskAssignCardModel {
    String tenantName,TenantNumber,RoomName,RoomTaskDescription;

    //constructor

    public TaskAssignCardModel() {
    }
    //getter setter

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
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

    public String getRoomTaskDescription() {
        return RoomTaskDescription;
    }

    public void setRoomTaskDescription(String roomTaskDescription) {
        RoomTaskDescription = roomTaskDescription;
    }
}
