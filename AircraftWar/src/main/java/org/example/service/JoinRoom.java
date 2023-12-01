package org.example.service;

import com.alibaba.fastjson.JSONObject;
import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;
import org.example.agreement.TypeProcessing;
import org.example.data.GameData;
import org.example.pojo.GameRoomData;
import org.example.exception.ExceptionOutLog;
import org.example.flush.RoomFlush;
import org.example.pojo.GameStream;
import org.example.pojo.UserInterface;
import org.example.utils.Copy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Service("JoinRoom")
public class JoinRoom implements TypeProcessing {

    @Autowired
    private ExceptionOutLog exception;
    @Autowired
    private GameData gameData;

    @Override
    public void main(StreamingMedia streamingMedia) {
        this.joinRoom(streamingMedia);
    }

    private void joinRoom(StreamingMedia streamingMedia) {
        UserInterface userInterface = streamingMedia.getUserInterface();
        int roomNumber = streamingMedia.getGameStream().getRoomNumber();
        GameRoomData gameRoomData = this.gameData.getGameRoomDataMap().get(roomNumber);
        try {
            if (gameRoomData == null || gameRoomData.overflow()) {
                new RoomFlush(streamingMedia.getSocketValue()).join(TCP_Type_Client.JOIN_ROOM_OVERFLOW_FAILURE,null);
            } else {
                gameRoomData.setGame2(userInterface);
                GameStream gameStream = new GameStream();
                gameStream.setRoomNumber(gameRoomData.getRoomNumber());
                gameStream.setGameRoomData(Copy.copyObject(gameRoomData,gameRoomData.getClass()));
                new RoomFlush(streamingMedia.getSocketValue()).join(TCP_Type_Client.JOIN_ROOM_SUCCESS, streaming -> {
                    streaming.setMsg("加入成功");
                    streaming.setGameStream(gameStream);
                    return streaming;
                });
                new RoomFlush(gameRoomData.getGame1().getUuid()).join(TCP_Type_Client.JOIN_ROOM_SUCCESS, streaming -> {
                    streaming.setGameStream(gameStream);
                    return streaming;
                });
            }
        } catch (Exception e) {
            exception.print(this, e);
        }
    }
}
