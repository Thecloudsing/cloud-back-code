package org.example.service.impl;

import org.example.DAObase.dao.UserBasicDAO;
import org.example.pojo.UserInformation;
import org.example.service.CommonAbstract;
import org.example.service.ServiceException;
import org.example.service.UserService;
import org.example.utils.StringUtil;

import java.util.List;

public class UserServiceImpl extends CommonAbstract implements UserService {

    private UserBasicDAO userBasicDAO;

    @Override
    public String signIn(UserInformation userInformation) {
        if (StringUtil.isEmptyMatch(userInformation, "account", "password"))
            throw new ServiceException(ServiceException.REQUEST_PARAMETER_NULL_VALUE);
        if (!userBasicDAO.verification(userInformation.getAccount(), userInformation.getPassword())) {
            return error + "账号或密码错误";
        }
        return success + "登录成功";
    }

    @Override
    public List<UserInformation> register(UserInformation userInformation) {
        if (StringUtil.isEmptyMatch(userInformation, "username", "account", "password"))
            throw new ServiceException(ServiceException.REQUEST_PARAMETER_NULL_VALUE);
        if (userBasicDAO.verificationUsername(userInformation.getUsername()))
            throw new ServiceException(ServiceException.THE_USERNAME_HAS_ALREADY_BEEN_REGISTERED);
        if (userBasicDAO.verificationAccount(userInformation.getAccount()))
            throw new ServiceException(ServiceException.THE_ACCOUNT_HAS_ALREADY_BEEN_REGISTERED);
        userBasicDAO.addUser(userInformation.getId(), userInformation.getUsername(), userInformation.getAccount(), userInformation.getPassword());
        return selectUser();
    }

    @Override
    public List<UserInformation> changePassword(UserInformation userInformation) {
        if (StringUtil.isEmptyMatch(userInformation, "account", "oldPassword", "newPassword"))
            throw new ServiceException(ServiceException.REQUEST_PARAMETER_NULL_VALUE);
        if (userInformation.getOldPassword().equals(userInformation.getNewPassword()))
            throw new ServiceException(ServiceException.THE_NEW_PASSWORD_CANNOT_BE_THE_SAME_AS_THE_OLD_PASSWORD);
        if (userBasicDAO.verificationAccount(userInformation.getAccount()))
            throw new ServiceException(ServiceException.THIS_ACCOUNT_HAS_NOT_BEEN_REGISTERED);
        if (!userBasicDAO.verification(userInformation.getAccount(), userInformation.getOldPassword()))
            throw new ServiceException(ServiceException.ORIGINAL_PASSWORD_ERROR);
        userBasicDAO.forgetUser(userInformation.getAccount(), userInformation.getNewPassword());
        return selectUser();
    }

    @Override
    public List<UserInformation> selectUser() {
        if (like == null)
            return userBasicDAO.selUser(currentPage, limit);
        return userBasicDAO.selLikeUser(like, currentPage, limit);
    }

//    @Override
//    public List<UserInformation> selectLikeUser() {
//        return userBasicDAO.selLikeUser(like, currentPage, limit);
//    }

    @Override
    public List<UserInformation> alterPassword(UserInformation userInformation) {
        if (StringUtil.isEmptyMatch(userInformation, "account", "password"))
            throw new ServiceException(ServiceException.REQUEST_PARAMETER_NULL_VALUE);
        userBasicDAO.updateUser(userInformation.getAccount(), userInformation.getPassword());
        return selectUser();
    }

    @Override
    public List<UserInformation> delUser(String id) {
        if (StringUtil.isEmpty(id))
            throw new ServiceException(ServiceException.REQUEST_PARAMETER_NULL_VALUE);
        userBasicDAO.delUser(id);
        return selectUser();
    }

    @Override
    public int getTotalCount() {
        if (pageCount == null || loop)
            pageCount = like == null ?
                    userBasicDAO.getCount() :
                    userBasicDAO.getCount(" where username like '%" + like + "%'");
        return pageCount;
    }

}
