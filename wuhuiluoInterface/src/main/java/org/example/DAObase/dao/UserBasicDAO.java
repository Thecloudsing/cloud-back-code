package org.example.DAObase.dao;

import org.example.pojo.UserInformation;

import java.util.List;

public interface UserBasicDAO extends CommonBasicDAO {
    boolean verification(String account, String password);

    boolean verificationAccount(String account);
    boolean verificationUsername(String username);
    void addUser(String id, String username, String account, String password);
    void delUser(String id);
    void updateUser(String id, String password);
    void forgetUser(String account, String password);
    List<UserInformation> selUser(int page, int limit);
    List<UserInformation> selLikeUser(String like, int page, int limit);

}
