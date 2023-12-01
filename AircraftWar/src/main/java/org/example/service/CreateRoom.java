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

@Service("CreateRoom")
public class CreateRoom implements TypeProcessing {

    @Autowired
    private GameData gameData;

    @Override
    public void main(StreamingMedia streamingMedia) throws IOException {
        this.createRoom(streamingMedia);
    }

    private void createRoom(StreamingMedia streamingMedia) throws IOException {
        UserInterface userInterface = streamingMedia.getUserInterface();
        GameStream gameStream = streamingMedia.getGameStream();
        int roomNumber = gameStream.getRoomNumber();
        Map<Integer, GameRoomData> gameRoomDataMap = gameData.getGameRoomDataMap();
        if (gameRoomDataMap.containsKey(roomNumber))
            new RoomFlush(streamingMedia.getSocketValue()).create(TCP_Type_Client.CREATE_ROOM_CODE_FAILURE, null);
        else {
            GameRoomData gameRoomData = new GameRoomData(userInterface,roomNumber);
            gameRoomDataMap.put(roomNumber, gameRoomData);
            gameStream.setGameRoomData(gameRoomData);
            new RoomFlush(streamingMedia.getSocketValue()).create(TCP_Type_Client.CREATE_ROOM_SUCCESS, streaming -> {
                streaming.setGameStream(gameStream);
                return streaming;
            });
        }
    }
}
