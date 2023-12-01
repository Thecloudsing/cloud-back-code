package org.example.service;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;
import org.example.agreement.TypeProcessing;
import org.example.flush.GameFlush;
import org.example.game_service.GameController;
import org.example.pojo.GameStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("Game")
public class Game implements TypeProcessing {

    @Autowired
    private GameController gameController;

    @Override
    public void main(StreamingMedia streamingMedia) throws IOException {
        this.initialize(streamingMedia);
    }

    private void initialize(StreamingMedia streamingMedia) throws IOException {
        String uuid = streamingMedia.getUUID();
        String socketValue = streamingMedia.getSocketValue();
        GameStream gameStream = new GameStream();
        gameStream.setGameUUID(GameController.GameID);
        new GameFlush(uuid).gameStatus(TCP_Type_Client.GAME_INITIALIZE,(streaming) -> {
            streaming.setGameStream(gameStream);
            return streaming;
        });
    }


}
