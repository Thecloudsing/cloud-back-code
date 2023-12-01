package org.example.flush;

import com.alibaba.fastjson.JSONObject;
import org.example.agreement.StreamingMedia;
import org.example.controller.StreamingMediaProcessing;
import org.example.pojo.ClientBasicInterface;

import java.io.IOException;
import java.io.ObjectOutputStream;

public abstract class OutputStreaming {

    private ObjectOutputStream objectOutputStream;

    public OutputStreaming(String value) {
        if (value.contains("Socket")) {
            ClientBasicInterface clientBasicInterface = StreamingMediaProcessing.CLIENT_BASIC_INTERFACE_MAP.get(value);
            if (clientBasicInterface == null) return;
            this.objectOutputStream = clientBasicInterface.getObjectOutputStream();
        } else {
            String socketValue = StreamingMediaProcessing.UUID_SOCKET_VALUE.get(value);
            ClientBasicInterface clientBasicInterface = StreamingMediaProcessing.CLIENT_BASIC_INTERFACE_MAP.get(socketValue);
            if (clientBasicInterface == null) return;
            this.objectOutputStream = clientBasicInterface.getObjectOutputStream();
        }
    }

    public StreamingMedia setStreamingInterface(StreamingMediaInterface streamingMediaInterface, StreamingMedia streamingMedia) {
        if (streamingMediaInterface != null)
            return streamingMediaInterface.streaming(streamingMedia);
        return streamingMedia;
    }
    public void sendData(StreamingMedia streamingMedia) throws IOException {
        if (objectOutputStream == null) return;
        objectOutputStream.writeObject(streamingMedia);
//        objectOutputStream.writeObject(JSONObject.toJSONString(streamingMedia));
    }
}
