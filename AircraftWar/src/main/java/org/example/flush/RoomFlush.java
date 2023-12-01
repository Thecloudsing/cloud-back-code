package org.example.flush;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;

import java.io.IOException;


public class RoomFlush extends OutputStreaming {
    public RoomFlush(String value) {
        super(value);
    }

    private StreamingMedia streamingMedia;
    public void destruction(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient == TCP_Type_Client.EXIT_ROOM_DESTRUCTION)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.EXIT_ROOM_DESTRUCTION);
            streamingMedia.setMsg("房间被销毁");
        }
        else if (typeClient == TCP_Type_Client.EXIT_ROOM_SUCCESS)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.EXIT_ROOM_SUCCESS);
            streamingMedia.setMsg("好友退出房间");
        }
        if (streamingMediaInterface != null)
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        super.sendData(streamingMedia);
    }

    public void join(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient == TCP_Type_Client.JOIN_ROOM_SUCCESS)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.JOIN_ROOM_SUCCESS);
            streamingMedia.setMsg("好友加入房间");
        }
        else if (typeClient == TCP_Type_Client.JOIN_ROOM_OVERFLOW_FAILURE)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.JOIN_ROOM_OVERFLOW_FAILURE);
            streamingMedia.setMsg("房间已满，无法加入");
        }
        if (streamingMediaInterface != null)
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        super.sendData(streamingMedia);
    }

    public void create(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient == TCP_Type_Client.CREATE_ROOM_SUCCESS)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.CREATE_ROOM_SUCCESS);
            streamingMedia.setMsg("创建房间成功");
        }
        else if (typeClient == TCP_Type_Client.CREATE_ROOM_CODE_FAILURE)
        {
            streamingMedia = new StreamingMedia(TCP_Type_Client.CREATE_ROOM_CODE_FAILURE);
            streamingMedia.setMsg("房间码存在，无法创建");
        }
        if (streamingMediaInterface != null)
            streamingMedia = streamingMediaInterface.streaming(streamingMedia);
        super.sendData(streamingMedia);
    }
}
