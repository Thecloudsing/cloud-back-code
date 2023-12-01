package org.example.service;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;
import org.example.agreement.TypeProcessing;
import org.example.data.GameData;
import org.example.pojo.GameRoomData;
import org.example.exception.ExceptionOutLog;
import org.example.flush.RoomFlush;
import org.example.pojo.GameStream;
import org.example.pojo.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service("ExitRoom")
public class ExitRoom implements TypeProcessing {

    @Autowired
    private ExceptionOutLog exception;
    @Autowired
    private GameData gameData;
    @Override
    public void main(StreamingMedia streamingMedia) throws IOException {
        this.exitRoom(streamingMedia);
    }

    private void exitRoom(StreamingMedia streamingMedia) throws IOException {
        UserInterface user = streamingMedia.getUserInterface();
        int roomNumber = streamingMedia.getGameStream().getRoomNumber();
        Map<Integer, GameRoomData> gameRoomDataMap = this.gameData.getGameRoomDataMap();
        GameRoomData gameRoomData = gameRoomDataMap.get(roomNumber);
        if (gameRoomData.getGame1() == user) {
            gameRoomDataMap.remove(roomNumber);
            if (gameRoomData.overflow())
                new RoomFlush(gameRoomData.getGame2().getUuid()).destruction(TCP_Type_Client.EXIT_ROOM_DESTRUCTION, null);
        } else {
            gameRoomData.leave();
            new RoomFlush(gameRoomData.getGame1().getUuid()).destruction(TCP_Type_Client.EXIT_ROOM_SUCCESS, streaming -> {
                GameStream gameStream = new GameStream();
                gameStream.setRoomNumber(roomNumber);
                gameStream.setGameRoomData(gameRoomData);
                streaming.setGameStream(gameStream);
                return streaming;
            });
        }
    }
}
