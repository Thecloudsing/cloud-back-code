package org.example.service;

import org.example.agreement.StreamingMedia;
import org.example.agreement.TCP_Type_Client;
import org.example.agreement.TypeProcessing;
import org.example.exception.ExceptionOutLog;
import org.example.flush.FriendFlush;
import org.example.mapper.FriendList;
import org.example.pojo.FriendLeaderboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("Friend")

public class Friend implements TypeProcessing {
    @Override
    public void main(StreamingMedia streamingMedia) throws IOException {
        this.friendList(streamingMedia);
    }

    @Autowired
    private FriendList friendList;

    private void friendList(StreamingMedia streamingMedia) throws IOException {
        String uuid = streamingMedia.getUUID();
        int id = streamingMedia.getUserInterface().getId();
        List<FriendLeaderboard> friendList = this.friendList.getFriendList();
        new FriendFlush(uuid).friendStatus(TCP_Type_Client.FRIEND_GET_SUCCESS, streaming -> {
            streaming.setFriendList(friendList);
            return streaming;
        });
    }
}
