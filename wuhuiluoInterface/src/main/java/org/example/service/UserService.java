package org.example.service;

import org.example.pojo.UserInformation;

import java.util.List;


public interface UserService extends CommonService {
    String signIn(UserInformation userInformation);
    List<UserInformation> register(UserInformation userInformation);
    List<UserInformation> changePassword(UserInformation userInformation);
    List<UserInformation> selectUser();
//    List<UserInformation> selectLikeUser();
    List<UserInformation> alterPassword(UserInformation userInformation);
    List<UserInformation> delUser(String id);

}
