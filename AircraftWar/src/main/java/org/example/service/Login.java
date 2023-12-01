package org.example.service;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;
import org.example.agreement.TypeProcessing;
import org.example.controller.StreamingMediaProcessing;
import org.example.exception.ExceptionOutLog;
import org.example.flush.ValidationReturns;
import org.example.mapper.FriendList;
import org.example.mapper.UserAuthentication;
import org.example.mapper.UserInterface;
import org.example.pojo.Authentication;
import org.example.pojo.FriendLeaderboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service("Login")
public class Login implements TypeProcessing {
    @Override
    public void main(StreamingMedia streamingMedia) throws IOException {
        this.verification(streamingMedia);
    }

    @Autowired
    FriendList friendList;
    @Autowired
    UserInterface userInterface;
    @Autowired
    UserAuthentication userAuthentication;

    private void verification(StreamingMedia streamingMedia) throws IOException {
        Authentication authentication = streamingMedia.getAuthentication();
        String account = authentication.getAccount();
        String password = authentication.getPassword();
        Integer verification = this.userAuthentication.verification(account, password);
        String socketValue = streamingMedia.getSocketValue();
        if (verification != null) {
            org.example.pojo.UserInterface user = this.userInterface.getUser_id(verification);
            List<FriendLeaderboard> friendList = this.friendList.getFriendList();
            StreamingMediaProcessing.UUID_SOCKET_VALUE.put(user.getUuid(), socketValue);
            new ValidationReturns(socketValue).loginStatus(TCP_Type_Client.LOGIN_SUCCESS,
                    streamingMediaSend -> {
                streamingMediaSend.setUserInterface(user);
                streamingMediaSend.setUsername(user.getUsername());
                streamingMediaSend.setUUID(user.getUuid());
                streamingMediaSend.setFriendList(friendList);
                return streamingMediaSend;
            });
        } else {
            new ValidationReturns(socketValue).loginStatus(TCP_Type_Client.LOGIN_FAILURE,null);
        }
    }
}
