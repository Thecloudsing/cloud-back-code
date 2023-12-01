package org.example.flush;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;

import java.io.IOException;


public class ExitFlush extends OutputStreaming {
    public ExitFlush(String value) {
        super(value);
    }
    private StreamingMedia streamingMedia;
    public void exit(TCP_Type_Client typeClient, StreamingMediaInterface streamingMediaInterface) throws IOException {
        if (typeClient == TCP_Type_Client.Exit_APP) {
            streamingMedia = new StreamingMedia(TCP_Type_Client.Exit_APP);
            streamingMedia.setMsg("退出程序");
        }
        streamingMedia = super.setStreamingInterface(streamingMediaInterface,streamingMedia);
        super.sendData(streamingMedia);
    }
}
