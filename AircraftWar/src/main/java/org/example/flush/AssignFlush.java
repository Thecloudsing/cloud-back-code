package org.example.flush;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;

import java.io.IOException;

public class AssignFlush extends OutputStreaming {

    public AssignFlush(String value) {
        super(value);
    }

    private StreamingMedia streamingMedia;

    public void match(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient == TCP_Type_Client.MATCH_FAILURE)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.MATCH_FAILURE);
            streamingMedia.setMsg("匹配失败");
        }
        if (streamingMediaInterface != null) {
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        }
        super.sendData(streamingMedia);
    }

    public void matching(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient == TCP_Type_Client.MATCH_ING)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.MATCH_ING);
            streamingMedia.setMsg("匹配中");
        }
        if (streamingMediaInterface != null) {
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        }
        super.sendData(streamingMedia);
    }

    public void assign(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient == TCP_Type_Client.MATCH_SUCCESS)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.MATCH_SUCCESS);
            streamingMedia.setMsg("匹配成功");
        }
        if (streamingMediaInterface != null) {
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        }
        super.sendData(streamingMedia);
    }

    public void exit(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient == TCP_Type_Client.MATCH_EXIT)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.MATCH_EXIT);
            streamingMedia.setMsg("退出匹配成功");
        }
        if (streamingMediaInterface != null) {
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        }
        super.sendData(streamingMedia);
    }
}
