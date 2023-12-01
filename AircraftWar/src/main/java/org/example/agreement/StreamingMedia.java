package org.example.agreement;

import org.example.pojo.Authentication;
import org.example.pojo.FriendLeaderboard;
import org.example.pojo.GameStream;
import org.example.pojo.UserInterface;

import java.io.Serializable;
import java.util.List;

public class StreamingMedia implements Serializable {

    private String UUID;
    private String username;
    private TCP_Type_Server typeServer;
    private TCP_Type_Client typeClient;
    private String socketValue;
    private String msg;
    private int statusCode;
    private String friendAccount;
    private Authentication authentication;
    private List<FriendLeaderboard> friendList;
    private UserInterface userInterface;
    private GameStream gameStream;
    private static final long serialVersionUID = 1L;
    public StreamingMedia(TCP_Type_Server typeServer) {
        this.typeServer = typeServer;
    }

    public StreamingMedia(TCP_Type_Client typeClient) {
        this.typeClient = typeClient;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TCP_Type_Server getTypeServer() {
        return typeServer;
    }

    public TCP_Type_Client getTypeClient() {
        return this.typeClient;
    }

    public String getSocketValue() {
        return socketValue;
    }

    public void setSocketValue(String socketValue) {
        this.socketValue = socketValue;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getFriendAccount() {
        return friendAccount;
    }

    public void setFriendAccount(String friendAccount) {
        this.friendAccount = friendAccount;
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public List<FriendLeaderboard> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<FriendLeaderboard> friendList) {
        this.friendList = friendList;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public GameStream getGameStream() {
        return gameStream;
    }

    public void setGameStream(GameStream gameStream) {
        this.gameStream = gameStream;
    }

}
