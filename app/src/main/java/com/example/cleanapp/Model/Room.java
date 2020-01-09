package com.example.cleanapp.Model;

public class Room {

    String roomName, roomDescription;

    public Room() {
    }

    public Room(String room, String description) {
        this.roomName = room;
        this.roomDescription = description;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomdescription() {
        return roomDescription;
    }

    public void setRoomdescription(String roomdescription) {
        this.roomDescription = roomdescription;
    }
}
