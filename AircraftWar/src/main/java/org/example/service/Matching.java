package org.example.service;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;
import org.example.agreement.TypeProcessing;
import org.example.async.AssignGameData;
import org.example.data.GameData;
import org.example.exception.ExceptionOutLog;
import org.example.flush.AssignFlush;
import org.example.pojo.GameRoomData;
import org.example.pojo.GameStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("Matching")
public class Matching implements TypeProcessing {
    @Override
    public void main(StreamingMedia streamingMedia) throws IOException {
        this.match(streamingMedia);
    }

    @Autowired
    private GameData gameData;
    @Autowired
    private AssignGameData assignGameData;
    private void match(StreamingMedia streamingMedia) throws IOException {
        String uuid = streamingMedia.getUUID();
        GameStream gameStream = streamingMedia.getGameStream();
        int roomNumber = gameStream.getRoomNumber();
        GameRoomData gameRoomData = this.gameData.getGameRoomDataMap().get(roomNumber);
        if (!gameRoomData.overflow()) {
            new AssignFlush(uuid).match(TCP_Type_Client.MATCH_FAILURE, streaming -> {
                streaming.setStatusCode(400);
                return streaming;
            });
            return;
        } else {
            assignGameData.setGameRoomDataMap(roomNumber,gameRoomData);
            new AssignFlush(uuid).matching(TCP_Type_Client.GAME_INITIALIZE, streaming -> {
                streaming.setStatusCode(200);
                return streaming;
            });
        }
    }
}
