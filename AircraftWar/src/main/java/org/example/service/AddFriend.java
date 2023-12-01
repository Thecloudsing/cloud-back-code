package org.example.service;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;
import org.example.agreement.TypeProcessing;
import org.example.exception.ExceptionOutLog;
import org.example.flush.FriendFlush;
import org.example.mapper.FriendList;
import org.example.mapper.UserAuthentication;
import org.example.mapper.UserInterface;
import org.example.pojo.FriendLeaderboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("AddFriend")
public class AddFriend implements TypeProcessing {

    @Override
    public void main(StreamingMedia streamingMedia) throws IOException {
        this.friendList(streamingMedia);
    }

    @Autowired
    private ExceptionOutLog exception;
    @Autowired
    private FriendList friendList;
    @Autowired
    private UserAuthentication userAuthentication;
    @Autowired
    private UserInterface userInterface;

    private void friendList(StreamingMedia streamingMedia) throws IOException {
        String uuid = streamingMedia.getUUID();
        int id = streamingMedia.getUserInterface().getId();
        String friendAccount = streamingMedia.getFriendAccount();
        org.example.pojo.UserInterface userUuid = userInterface.getUser_uuid(friendAccount);
        if (userUuid != null) {
            this.add(uuid, id, userUuid.getId());
        }
        Integer integer = userAuthentication.duplicateAccountChecking(friendAccount);
        if (integer != null) {
            this.add(uuid, id, integer);
        }
    }

    private void add(String uuid, int uid, int fid) throws IOException {
        this.friendList.addFriend(uid, fid);
        this.friendList.addFriend(fid, uid);
        List<FriendLeaderboard> friendList = this.friendList.getFriendList();
        new FriendFlush(uuid).friendStatus(TCP_Type_Client.FRIEND_ADD_SUCCESS, streaming -> {
            streaming.setFriendList(friendList);
            return streaming;
        });
    }
}
