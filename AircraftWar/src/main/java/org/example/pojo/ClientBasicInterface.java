package org.example.pojo;

import org.example.controller.StreamingMediaProcessing;
import org.example.exception.ExceptionOutLog;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientBasicInterface {

    public ClientBasicInterface(String name,
                                Socket socket,
                                ObjectInputStream objectInputStream,
                                ObjectOutputStream objectOutputStream,
                                StreamingMediaProcessing streamingMediaProcessing) {
        this.name = name;
        this.socket = socket;
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
        this.streamingMediaProcessing = streamingMediaProcessing;
    }

    private final String name;
    private final Socket socket;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;
    private final StreamingMediaProcessing streamingMediaProcessing;

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public StreamingMediaProcessing getStreamingMediaProcessing() {
        return streamingMediaProcessing;
    }

    public void close() {
        try {
            if (objectInputStream != null)
                objectInputStream.close();
            if (objectOutputStream != null)
                objectOutputStream.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            new ExceptionOutLog().print(getClass().getSimpleName(),e);
        }
    }
}
