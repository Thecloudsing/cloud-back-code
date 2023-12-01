package org.example.pojo;

import java.io.Serializable;

public class GameRoomData implements Serializable {
    private String RoomNumber;
    private final UserInterface game1;
    private UserInterface game2;

    public GameRoomData(UserInterface game1) {
        this.game1 = game1;
    }

    public String getRoomNumber() {
        return RoomNumber;
    }

    public boolean overflow() {
        return game2 != null;
    }
    public void leave() {
        this.game2 = null;
    }

    public void setGame2(UserInterface game2) {
        this.game2 = game2;
    }

    public UserInterface getGame1() {
        return game1;
    }

    public UserInterface getGame2() {
        return game2;
    }
}
