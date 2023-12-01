package org.example.flush;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;

import java.io.IOException;

public class GameFlush extends OutputStreaming {
    public GameFlush(String value) {
        super(value);
    }
    private StreamingMedia streamingMedia;
    public void gameStatus(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {

        if (typeClient ==  TCP_Type_Client.GAME_INITIALIZE)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.GAME_INITIALIZE);
            streamingMedia.setMsg("初始化游戏");
        }
        else if (typeClient == TCP_Type_Client.GAME_RUNNING)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.GAME_RUNNING);
            streamingMedia.setMsg("运行游戏");
        }
        else if (typeClient == TCP_Type_Client.GAME_END)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.GAME_END);
            streamingMedia.setMsg("游戏结束");
        } else
            return;
        if (streamingMediaInterface != null)
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        super.sendData(streamingMedia);
    }
}
