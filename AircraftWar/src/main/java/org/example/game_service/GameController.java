package org.example.game_service;

import org.example.pojo.GameData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class GameController {
    protected Map<String,GameInitialize> gameInitializeMap = new HashMap<>();

    @Autowired
    private GameRunning gameRunning;
    @Autowired
    private GameEnd gameEnd;

    public static String GameID;

    public GameController() {
        GameID = UUID.randomUUID().toString();
        gameInitializeMap.put(GameID,
                new GameInitialize(GameID,true));
    }

    public void addGame(String gameID) {
        gameInitializeMap.put(gameID,new GameInitialize(gameID));
    }

    public void forward(GameData gameData, DatagramSocket datagramSocket, DatagramPacket datagramPacket) {
        GameInitialize gameInitialize = gameInitializeMap.get(gameData.getGameID());
        if (gameInitialize != null)
            gameInitialize.receive(gameData,datagramSocket,datagramPacket);
    }

}
