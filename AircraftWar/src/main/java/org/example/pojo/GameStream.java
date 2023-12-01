package org.example.pojo;

import java.io.Serializable;

public class GameStream implements Serializable {
    private static final long serialVersionUID = 1L;

    private int RoomNumber;

    private GameRoomData gameRoomData;
    private String GameUUID;
    private String clientIP;
    private int clientPort;

    private String serverIP;
    private int serverPort;

    public int getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        RoomNumber = roomNumber;
    }

    public GameRoomData getGameRoomData() {
        return gameRoomData;
    }

    public void setGameRoomData(GameRoomData gameRoomData) {
        this.gameRoomData = gameRoomData;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getGameUUID() {
        return GameUUID;
    }

    public void setGameUUID(String gameUUID) {
        GameUUID = gameUUID;
    }
}
