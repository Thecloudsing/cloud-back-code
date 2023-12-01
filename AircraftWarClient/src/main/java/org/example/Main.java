package org.example;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Server;
import org.example.pojo.Authentication;
import org.example.pojo.UserInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 23444);
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
//        StreamingMedia streamingMedia = new StreamingMedia(TCP_Type_Server.GET_REGISTER_CODE);
//        StreamingMedia streamingMedia = new StreamingMedia(TCP_Type_Server.GET_FORGET_CODE);
        StreamingMedia streamingMedia = new StreamingMedia(TCP_Type_Server.LOGIN);
        Authentication authentication = new Authentication();
        streamingMedia.setUsername("扶苏");
        authentication.setAccount("1756174331@qq.com");
        authentication.setCode("672334");
        authentication.setPassword("331520");
        streamingMedia.setAuthentication(authentication);
        oos.writeObject(streamingMedia);

        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        StreamingMedia o = (StreamingMedia) objectInputStream.readObject();

        System.out.println("============================");
        System.out.println(o);


        StreamingMedia streamingMedia1 = new StreamingMedia(TCP_Type_Server.EXIT);
        streamingMedia1.setUUID(o.getUUID());
        oos.writeObject(streamingMedia1);


        StreamingMedia exit = (StreamingMedia) objectInputStream.readObject();

        System.out.println(exit);
    }
}