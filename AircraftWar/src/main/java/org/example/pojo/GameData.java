package org.example.pojo;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GameData implements Serializable {

    private final int aircraftSize = 4;
    private final int bulletSize = 10;
    private Point[] aircraftPoints = new Point[aircraftSize];
    private int[] aircraftDegrees = new int[aircraftSize];
    private Point[] aircraftDestructionS = new Point[aircraftSize];
    private Point[][] bulletPoints = new Point[aircraftSize][bulletSize];
    private int[][] bulletDegrees = new int[aircraftSize][bulletSize];
    private Long time;
    private Map<String,Integer> leagueTables = new HashMap<>();


    private boolean[] bulletInitializePoints = new boolean[bulletSize];
    private boolean status = true;
    private Point mouseLastPoint = new Point();
    private String gamePlayerID;
    private String GameID;
    private String name;

    public int getAircraftSize() {
        return aircraftSize;
    }

    public int getBulletSize() {
        return bulletSize;
    }

    public Point[] getAircraftPoints() {
        return aircraftPoints;
    }


    public int[] getAircraftDegrees() {
        return aircraftDegrees;
    }

    public Point[] getAircraftDestructionS() {
        return aircraftDestructionS;
    }


    public Point[][] getBulletPoints() {
        return bulletPoints;
    }


    public int[][] getBulletDegrees() {
        return bulletDegrees;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Map<String, Integer> getLeagueTables() {
        return leagueTables;
    }


    public boolean[] getBulletInitializePoints() {
        return bulletInitializePoints;
    }


    public Point getMouseLastPoint() {
        return mouseLastPoint;
    }

    public String getGamePlayerID() {
        return gamePlayerID;
    }

    public void setGamePlayerID(String gamePlayerID) {
        this.gamePlayerID = gamePlayerID;
    }

    public String getGameID() {
        return GameID;
    }

    public void setGameID(String gameID) {
        GameID = gameID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAircraftPoints(Point[] aircraftPoints) {
        this.aircraftPoints = aircraftPoints;
    }

    public void setAircraftDegrees(int[] aircraftDegrees) {
        this.aircraftDegrees = aircraftDegrees;
    }

    public void setAircraftDestructionS(Point[] aircraftDestructionS) {
        this.aircraftDestructionS = aircraftDestructionS;
    }

    public void setBulletPoints(Point[][] bulletPoints) {
        this.bulletPoints = bulletPoints;
    }

    public void setBulletDegrees(int[][] bulletDegrees) {
        this.bulletDegrees = bulletDegrees;
    }

    public void setBulletInitializePoints(boolean[] bulletInitializePoints) {
        this.bulletInitializePoints = bulletInitializePoints;
    }

    public void setMouseLastPoint(Point mouseLastPoint) {
        this.mouseLastPoint = mouseLastPoint;
    }

    public void setLeagueTables(Map<String, Integer> leagueTables) {
        this.leagueTables = leagueTables;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private static final long serialVersionUID = 1L;
}