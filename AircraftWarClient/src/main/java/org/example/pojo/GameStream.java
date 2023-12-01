package org.example.pojo;


import java.io.Serializable;

public class GameStream implements Serializable {
    private static final long serialVersionUID = 1L;

    private String RoomNumber;

    private GameRoomData gameRoomData;
    public String getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        RoomNumber = roomNumber;
    }

    public GameRoomData getGameRoomData() {
        return gameRoomData;
    }

    public void setGameRoomData(GameRoomData gameRoomData) {
        this.gameRoomData = gameRoomData;
    }
}
