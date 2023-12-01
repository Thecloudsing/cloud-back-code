package org.example.data;

import org.example.pojo.GameRoomData;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameData {
    private final Map<Integer, GameRoomData> gameRoomDataMap = new HashMap<>();

    public Map<Integer, GameRoomData> getGameRoomDataMap() {
        return gameRoomDataMap;
    }
}
