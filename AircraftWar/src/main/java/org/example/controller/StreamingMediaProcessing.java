package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Server;
import org.example.exception.ExceptionOutLog;
import org.example.pojo.ClientBasicInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class StreamingMediaProcessing extends Thread {

    ArithmeticController arithmeticController;
    public static final Map<String, ClientBasicInterface> CLIENT_BASIC_INTERFACE_MAP = new HashMap<>();

    public static final Map<String,String> UUID_SOCKET_VALUE = new HashMap<>();

    private final ClientBasicInterface CLIENT_BASIC_INTERFACE;
    public StreamingMediaProcessing(Socket socket, ArithmeticController arithmeticController) throws IOException {
        ClientBasicInterface clientBasicInterface = new ClientBasicInterface(
                socket.toString(),
                socket,
                new ObjectInputStream(socket.getInputStream()),
                new ObjectOutputStream(socket.getOutputStream()),
                this);
        this.arithmeticController = arithmeticController;
        this.CLIENT_BASIC_INTERFACE = clientBasicInterface;
        StreamingMediaProcessing.CLIENT_BASIC_INTERFACE_MAP.put(socket.toString(),clientBasicInterface);
    }

    private boolean loop = true;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public void run() {

        while (this.loop) {
            StreamingMedia streamingMedia = null;
            try {
                streamingMedia = (StreamingMedia) this.CLIENT_BASIC_INTERFACE.getObjectInputStream().readObject();
                TCP_Type_Server type = streamingMedia.getTypeServer();
                streamingMedia.setSocketValue(this.CLIENT_BASIC_INTERFACE.getSocket().toString());
                System.out.println(JSONObject.toJSONString(streamingMedia));
                this.arithmeticController.logic(type, streamingMedia);
            } catch (Exception e) {
                new ExceptionOutLog().print(getClass().getSimpleName(),e);
                if (e.getClass().getSimpleName().equals("SocketException")) {
                    this.CLIENT_BASIC_INTERFACE.close();
                    StreamingMediaProcessing.CLIENT_BASIC_INTERFACE_MAP.remove(this.CLIENT_BASIC_INTERFACE.getName());
                    this.loop = false;
                    break;
                }
            }
        }
    }
}
