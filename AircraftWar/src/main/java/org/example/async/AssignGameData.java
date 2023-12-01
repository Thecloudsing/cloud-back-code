package org.example.async;

import org.example.agreement.TCP_Type_Client;
import org.example.exception.ExceptionOutLog;
import org.example.flush.AssignFlush;
import org.example.game_service.GameController;
import org.example.pojo.GameRoomData;
import org.example.pojo.GameStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class AssignGameData implements Runnable {

    @Autowired
    private GameController gameController;

    @Autowired
    private ExceptionOutLog exception;
    private final Map<Integer, GameRoomData> gameRoomDataMap = new HashMap<>();

    public AssignGameData() {
        new Thread(this).start();
    }
    public void setGameRoomDataMap(int RoomNumber,GameRoomData gameRoomData) {
        this.gameRoomDataMap.put(RoomNumber,gameRoomData);
    }
    @Value("${server_ip}")
    private String serverIP;
    @Value("${udp_port}")
    private int udp_port;


    @Override
    public void run() {
        try {
            while (true) {
                if (gameRoomDataMap.size() >= 1) {
                    final Integer[] tempKey = new Integer[gameRoomDataMap.size()];
                    final int[] i = new int[1];
                    gameRoomDataMap.forEach((k, v) -> {
                        tempKey[i[0]] = k;
                        String GameID = UUID.randomUUID().toString();
                        gameController.addGame(GameID);
                        GameStream gameStream = new GameStream();
                        gameStream.setServerIP(this.serverIP);
                        gameStream.setServerPort(this.udp_port);
                        gameStream.setGameUUID(GameID);
                        i[0]++;
                        try {
                            sendMsg(v,gameStream);
                        } catch (IOException e) {
                            exception.print(this,e);
                        }
                    });
                    for (Integer roomNumber: tempKey)
                        if (roomNumber != null)
                            gameRoomDataMap.remove(roomNumber);
                } else {
                    Thread.sleep(10 * 1000L);
                }
            }
        } catch (Exception e) {
            exception.print(this,e);
        }
    }

    private void sendMsg(GameRoomData gameRoomData, GameStream gameStream) throws IOException {
        new AssignFlush(gameRoomData.getGame1().getUuid()).assign(TCP_Type_Client.MATCH_SUCCESS, streamingMedia -> {
            streamingMedia.setStatusCode(200);
            streamingMedia.setGameStream(gameStream);
            return streamingMedia;
        });
        new AssignFlush(gameRoomData.getGame2().getUuid()).assign(TCP_Type_Client.MATCH_SUCCESS, streamingMedia -> {
            streamingMedia.setStatusCode(200);
            streamingMedia.setGameStream(gameStream);
            return streamingMedia;
        });
    }
}
