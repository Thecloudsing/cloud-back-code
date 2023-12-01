package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


@Controller
public class SocketController implements Runnable {

    @Autowired
    private ArithmeticController arithmeticController;
    @Value("${tcp_port}")
    private int port;

    @PostConstruct
    private void init() {
        new Thread(this).start();
    }


    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            System.out.println("开始接收服务...");
            while (true) {
                Socket accept = serverSocket.accept();
                System.out.println(accept);
                new StreamingMediaProcessing(accept, this.arithmeticController).start();
            }
        } catch (IOException e) {

        }
    }
}
