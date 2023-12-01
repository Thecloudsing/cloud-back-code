package org.example.game_service;

import com.alibaba.fastjson.JSONObject;
import org.example.pojo.GameData;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

public class GameInitialize implements Runnable {
    private final Random random = new Random();

    private int s = 300;
    private boolean isTime = false;
    private int onlineGamePlay = 0;
    private final int numberOfPlayers = 4;

    private final int bulletSize = 10;

    private final long initializeTime = new Date().getTime();

    private static final List<Point> initCoordinate = new ArrayList<>();


    private final boolean[] canWeControlIt = new boolean[numberOfPlayers];
    private final boolean[] gamePlayStatus = new boolean[numberOfPlayers];

    static {
        initCoordinate.add(new Point(375,250));
        initCoordinate.add(new Point(1125,250));
        initCoordinate.add(new Point(375,750));
        initCoordinate.add(new Point(1125,750));
    }
    {
        for (int i = 0; i < numberOfPlayers; i++) {
            canWeControlIt[i] = true;
        }
    }
    //1500 * 1000
    private final Point centerOfCircle = new Point(1500 / 2, 1000 / 2);

    private final List<String> gamePlayerID = new ArrayList<>();
    private final Map<String,String> gamePlayerUsernameID = new HashMap<>();
    private boolean initStatus = false;
    private boolean threadStatus = true;

    private final String gameID;

    public GameInitialize(String gameID, boolean isTime) {
        this.isTime = isTime;
        this.gameID = gameID;
    }

    public GameInitialize(String gameID, int s) {
        this.s = s;
        this.gameID = gameID;
    }

    public GameInitialize(String gameID) {
        this.gameID = gameID;
    }

    private void init() {
        Collections.shuffle(initCoordinate);
        for (int i = 0; i < onlineGamePlay; i++) {
            gameData.getAircraftPoints()[i] = initCoordinate.get(i);
            gameData.getAircraftDegrees()[i] = gameRunning.getDegrees(centerOfCircle,initCoordinate.get(i));
        }
        new Thread(this).start();
    }
    private void randomInit(int i) {
        gameData.getAircraftPoints()[i] = initCoordinate.get(random.nextInt(3));
        gameData.getAircraftDegrees()[i] = gameRunning.getDegrees(centerOfCircle,initCoordinate.get(random.nextInt(3)));
    }
    private final String[] names = new String[4];
    private final GameData gameData = new GameData();

    protected static GameRunning gameRunning;
    protected static GameEnd gameEnd;
    private boolean status = true;
    private final long[] lastTime = new long[numberOfPlayers];
    private final Integer[] canWeControlItTime = new Integer[numberOfPlayers];
    private final DatagramSocket[] datagramSockets = new DatagramSocket[numberOfPlayers];
    private final DatagramPacket[] datagramPackets = new DatagramPacket[numberOfPlayers];
    private final Point[] mouseLastPoints = new Point[numberOfPlayers];
    private final int[] aircraftLastDegrees = new int[numberOfPlayers];
    private final Point[][] bulletInitializePoints = new Point[numberOfPlayers][bulletSize];
    private final Point[][] bulletInitializeSpeed = new Point[numberOfPlayers][bulletSize];
    private final Point[] bulletInitializeLastSpeed = new Point[numberOfPlayers];
    private final int[][] bulletInitializeDegrees = new int[numberOfPlayers][bulletSize];
    private final Integer[][] bulletInitializeCount = new Integer[numberOfPlayers][bulletSize];

    private long count = 0;
    public void receive(GameData gameData, DatagramSocket datagramSocket, DatagramPacket datagramPacket) {
        if (onlineGamePlay == numberOfPlayers) return;
//        if (gamePlayerID.size() < numberOfPlayers) {
        if (!gamePlayerID.contains(gameData.getGamePlayerID())) {
            gamePlayerID.add(gameData.getGamePlayerID());
            int index = gamePlayerID.indexOf(gameData.getGamePlayerID());
            gamePlayerUsernameID.put(gameData.getName(),gameData.getGamePlayerID());
            names[index] = gameData.getName();
            gameData.getLeagueTables().put(gameData.getName(), 0);
            datagramSockets[index] = datagramSocket;
            datagramPackets[index] = datagramPacket;
            randomInit(index);
            gamePlayStatus[index] = gameData.isStatus();
            count++;
            onlineGamePlay++;
        }
//            return;
//        }
        if (!threadStatus) {
            synchronized (this) {
                this.notify();
                threadStatus = true;
            }
        }
        if (onlineGamePlay == 0) return;
        if (!initStatus) {
            init();
            initStatus = true;
        }
        int index = gamePlayerID.indexOf(gameData.getGamePlayerID());
        if (canWeControlIt[index]) {
            mouseLastPoints[index] = gameData.getMouseLastPoint();
            lastTime[index] = new Date().getTime();
            gamePlayStatus[index] = gameData.isStatus();
        }
        for (int i = 0, j = 0;i < bulletSize && j < gameData.getBulletInitializePoints().length; i++) {
            if (bulletInitializePoints[index][i] == null) {
                if (gameData.getBulletInitializePoints()[j]) {
                    bulletInitializeCount[index][i] = 0;
                    bulletInitializePoints[index][i] = this.gameData.getAircraftPoints()[index];
                    j++;
                }
            }
        }
    }

    private void rules() {
        for (int i = 0; i < onlineGamePlay; i++) {
            for (int j = 0; j < bulletSize; j++) {
                for (int k = 0; k < onlineGamePlay; k++) {
                    if (i == k) continue;
                    if (bulletInitializePoints[i][j] == null) continue;
                    if (gameData.getAircraftPoints()[k] == null) continue;
                    if (bulletInitializePoints[i][j].x > (gameData.getAircraftPoints()[k].x) &&
                            bulletInitializePoints[i][j].x < (gameData.getAircraftPoints()[k].x + 102) &&
                            bulletInitializePoints[i][j].y > (gameData.getAircraftPoints()[k].y) &&
                            bulletInitializePoints[i][j].y < (gameData.getAircraftPoints()[k].y + 102)) {
                        gameData.getAircraftDestructionS()[k] = gameData.getAircraftPoints()[k];
                        gameData.getAircraftPoints()[k].x = -1000;
                        gameData.getAircraftPoints()[k].y = -1000;
                        mouseLastPoints[k].x = -1000;
                        mouseLastPoints[k].y = -1000;
                        bulletInitializeCount[i][j] = null;
                        bulletInitializePoints[i][j] = null;
                        bulletInitializeSpeed[i][j] = null;
                        canWeControlIt[k] = false;
                        canWeControlItTime[k] = 0;
                        gameData.getBulletPoints()[i][j] = null;
                        Integer league;
                        if ((league = gameData.getLeagueTables().get(names[i])) == null)
                            league = 0;
                        gameData.getLeagueTables().put(names[i],league + 50);
                    }
                }
            }
            if (canWeControlItTime[i] == null) continue;
            canWeControlItTime[i]++;
            if (canWeControlItTime[i] > 30) {
                canWeControlItTime[i] = null;
                canWeControlIt[i] = true;
                gameData.getAircraftDestructionS()[i] = null;
                gameData.getAircraftPoints()[i].x = random.nextInt(1400);
                gameData.getAircraftPoints()[i].y = random.nextInt(900);
            }
        }
    }

    private void processed() {
        for (int i = 0; i < onlineGamePlay; i++) {
            if (mouseLastPoints[i] == null)
                continue;
            Integer degrees = gameRunning.getDegrees(gameData.getAircraftPoints()[i], mouseLastPoints[i]);
            if (degrees != null) {
                aircraftLastDegrees[i] = degrees;
            } else {
                degrees = aircraftLastDegrees[i];
            }
            Point bulletSpeed = gameRunning.getAircraftAndBulletSpeed(gameData.getAircraftPoints()[i], mouseLastPoints[i]);
            gameData.getAircraftDegrees()[i] = degrees;
            if (bulletSpeed != null) {
                bulletInitializeLastSpeed[i] = bulletSpeed;
            } else {
                bulletSpeed = bulletInitializeLastSpeed[i];
            }
            for (int j = 0; j < bulletSize; j++) {
                if (bulletInitializePoints[i][j] == null) continue;
                if (bulletInitializeSpeed[i][j] == null)
                    bulletInitializeSpeed[i][j] = bulletSpeed;
                if (bulletInitializeDegrees[i][j] == 0)
                    bulletInitializeDegrees[i][j] = degrees;
                gameData.getBulletPoints()[i][j] = new Point(
                        bulletInitializePoints[i][j].x + bulletInitializeSpeed[i][j].x,
                        bulletInitializePoints[i][j].y + bulletInitializeSpeed[i][j].y);
                bulletInitializePoints[i][j] = gameData.getBulletPoints()[i][j];
                bulletInitializeCount[i][j]++;
                gameData.getBulletDegrees()[i][j] = bulletInitializeDegrees[i][j];
            }
            for (int j = 0; j < bulletSize; j++) {
                if (bulletInitializePoints[i][j] == null) continue;
                if (bulletInitializeCount[i][j] >= 50) {
                    bulletInitializeCount[i][j] = null;
                    bulletInitializePoints[i][j] = null;
                    bulletInitializeSpeed[i][j] = null;
                    bulletInitializeDegrees[i][j] = 0;
                    gameData.getBulletPoints()[i][j] = null;
                    gameData.getBulletDegrees()[i][j] = 0 ;
                }

            }
        }
        long time = new Date().getTime() - initializeTime;
        if (!isTime && time > s * 1000L) {
            status = false;
        }
        gameData.setTime(time);
    }

    @Override
    public void run() {
        while (status) {
            try {
                rules();
                processed();
                byte[] bytes = JSONObject.toJSON(gameData).toString().getBytes(StandardCharsets.UTF_8);
                for (int i = 0; i < onlineGamePlay; i++) {
                    if (lastTime[i] + 60 * 1000 < new Date().getTime() || !gamePlayStatus[i]) {
                        gamePlayerID.remove(i);
                        datagramPackets[i] = null;
                        onlineGamePlay--;
                    }
                    if (onlineGamePlay == 0) {
                        synchronized (this) {
                            this.threadStatus = false;
                            this.wait();
                        }
                    }
                    if (datagramPackets[i] == null) continue;
                    DatagramPacket datagramPacket = null;
                    datagramPacket = new DatagramPacket(
                            bytes,
                            bytes.length,
                            datagramPackets[i].getAddress(),
                            datagramPackets[i].getPort()
                    );
                    try {
                        datagramSockets[i].send(datagramPacket);
                    } catch (IOException e) {
                        //throw new RuntimeException(e);
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        gameEnd.settlement(gameID, gameData.getLeagueTables(),gamePlayerUsernameID);
    }
}
