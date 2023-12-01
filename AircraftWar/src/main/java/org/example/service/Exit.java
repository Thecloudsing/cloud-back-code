package org.example.service;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;
import org.example.agreement.TypeProcessing;
import org.example.controller.StreamingMediaProcessing;
import org.example.exception.ExceptionOutLog;
import org.example.flush.ExitFlush;
import org.example.pojo.ClientBasicInterface;
import org.example.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service("Exit")
public class Exit implements TypeProcessing {

    @Override
    public void main(StreamingMedia streamingMedia) throws IOException {
        this.exit(streamingMedia);
    }

    private void exit(StreamingMedia streamingMedia) throws IOException {
        String ID = null;
        Map<String, String> uuidSocketValue = StreamingMediaProcessing.UUID_SOCKET_VALUE;
        String uuid = streamingMedia.getUUID();
        String socketValue = streamingMedia.getSocketValue();
        if (!StringUtil.isEmpty(uuid)) {
            ID = uuid;
        }
        if (!StringUtil.isEmpty(socketValue)) {
            ID = socketValue;
        }
        if (StringUtil.isEmpty(ID)) return;
        Map<String, ClientBasicInterface> clientBasicInterfaceMap = StreamingMediaProcessing.CLIENT_BASIC_INTERFACE_MAP;
        new ExitFlush(ID).exit(TCP_Type_Client.Exit_APP, streaming -> {
            streaming.setStatusCode(200);
            return streaming;
        });
        clientBasicInterfaceMap.get(socketValue).getStreamingMediaProcessing().setLoop(false);
        clientBasicInterfaceMap.get(socketValue).close();
        clientBasicInterfaceMap.remove(socketValue);
        uuidSocketValue.remove(uuid);

    }
}
