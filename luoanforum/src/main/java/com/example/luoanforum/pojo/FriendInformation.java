package com.example.luoanforum.pojo;

/**
 * @author 落扶苏
 * @version 1.1
 */
public class FriendInformation {
//    id int primary key auto_increment,
//    uid int unsigned,
//    fid int unsigned
    private int id;
    private int uid;
    private int fid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }
}
