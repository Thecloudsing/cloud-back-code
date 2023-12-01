package org.example.flush;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;

import java.io.IOException;

public class FriendFlush extends OutputStreaming {
    public FriendFlush(String value) {
        super(value);
    }

    private StreamingMedia streamingMedia;
    public void friendStatus(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient ==  TCP_Type_Client.FRIEND_GET_SUCCESS)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.FRIEND_GET_SUCCESS);
            streamingMedia.setMsg("获取好友列表");
        }
        else if (typeClient == TCP_Type_Client.FRIEND_ADD_SUCCESS)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.FRIEND_ADD_SUCCESS);
            streamingMedia.setMsg("添加好友成功");
        }
        else
            return;
        if (streamingMediaInterface != null)
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        super.sendData(streamingMedia);
    }
}
