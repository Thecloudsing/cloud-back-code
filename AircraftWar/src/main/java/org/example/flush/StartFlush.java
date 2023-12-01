package org.example.flush;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;

import java.io.IOException;

public class StartFlush extends OutputStreaming {

    public StartFlush(String value) {
        super(value);
    }
    private StreamingMedia streamingMedia;
    public void start(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient == TCP_Type_Client.GAME_INITIALIZE)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.GAME_INITIALIZE);
            streamingMedia.setMsg("开始游戏");
        }
        else if (typeClient == TCP_Type_Client.GAME_RUNNING)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.GAME_RUNNING);
            streamingMedia.setMsg("游戏中");
        }
        if (streamingMediaInterface != null)
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        super.sendData(streamingMedia);
    }

    public void exit(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient == TCP_Type_Client.GAME_END)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.GAME_END);
            streamingMedia.setMsg("游戏结束");
        }
        if (streamingMediaInterface != null)
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        super.sendData(streamingMedia);
    }
}
