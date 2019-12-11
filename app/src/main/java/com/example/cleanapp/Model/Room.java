package com.example.cleanapp.Model;

public class Room {

    String roomName, description;

    public Room() {
    }

    public Room(String room, String description) {
        this.roomName = room;
        this.description = description;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
