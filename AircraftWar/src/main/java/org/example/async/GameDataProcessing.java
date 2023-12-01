package org.example.async;

import com.alibaba.fastjson.JSONObject;
import org.example.pojo.GameData;
import org.example.exception.ExceptionOutLog;
import org.example.game_service.GameController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

@Service
public class GameDataProcessing implements Runnable {

    public GameDataProcessing() throws SocketException {
        new Thread(this).start();
    }

    @Autowired
    private ExceptionOutLog exception;
    @Autowired
    private GameController gameController;

    DatagramSocket datagramSocket = new DatagramSocket(23445);
    @Value("${udp_port}")
    private int port;
    @Override
    public void run() {
        int length;
        byte[] data;
        byte[] bytes = new byte[1024];
        while (true) {
            try {
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                datagramSocket.receive(datagramPacket);
                length = datagramPacket.getLength();
                data = datagramPacket.getData();
                String strStream = new String(data, 0, length);
                GameData gameData = JSONObject.parseObject(strStream, GameData.class);
                gameController.forward(gameData,datagramSocket,datagramPacket);
//                System.out.println(strStream);
            } catch (IOException e) {
                exception.print(this, e);
            }
        }
    }
}
