package org.example.game_service;

import org.example.agreement.TCP_Type_Client;
import org.example.flush.GameFlush;
import org.example.mapper.FriendList;
import org.example.mapper.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.function.BiConsumer;

@Service
public class GameEnd {

    @Autowired
    private GameController gameController;

    @Autowired
    private UserInterface userInterface;
    public GameEnd() {
        GameInitialize.gameEnd = this;
    }

    protected void settlement(String gameID, Map<String,Integer> leagueTables,Map<String,String> gamePlayUsernameID) {
        List<Map.Entry<String,Integer>> entryList = new ArrayList<>(leagueTables.entrySet());
        Collections.sort(entryList, (o1, o2) -> o2.getValue() - o1.getValue());
        int i = 0;
        for (Map.Entry<String,Integer> entry: entryList) {
            String uuid = gamePlayUsernameID.get(entry.getKey());
            userInterface.setIntegral(userInterface.getUser_uuid(uuid).getId(),50 - i*20);
            try {
                new GameFlush(uuid).gameStatus(TCP_Type_Client.GAME_END,null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (i < 4) i++;
        }
        this.delGame(gameID);
    }

    private void delGame(String gameID) {
        gameController.gameInitializeMap.remove(gameID);
    }

    private void callback() {

    }
}
