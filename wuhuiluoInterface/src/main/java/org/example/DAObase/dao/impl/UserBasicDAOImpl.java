package org.example.DAObase.dao.impl;

import org.example.DAObase.dao.CommonBasicAbstract;
import org.example.DAObase.dao.UserBasicDAO;
import org.example.pojo.UserInformation;

import java.util.List;

public class UserBasicDAOImpl extends CommonBasicAbstract implements UserBasicDAO {
    
    public UserBasicDAOImpl() {
        super();
        super.setTableName("user_data");
    }
    
    @Override
    public boolean verification(String account, String password) {
        return super.getDbUtils().informationIsFound("select * from user_data where account = ? and password = ?",account, password);
    }

    @Override
    public boolean verificationAccount(String account) {
        return super.getDbUtils().informationIsFound("select * from user_data where account = ?",account);
    }

    @Override
    public boolean verificationUsername(String username) {
        return super.getDbUtils().informationIsFound("select * from user_data where username = ?",username);
    }

    @Override
    public void addUser(String id, String username, String account, String password) {
        super.getDbUtils().updateRecords("insert into user_data (username,account,password) values (?,?,?)",
                username,account,password);
    }

    @Override
    public void delUser(String id) {
        super.getDbUtils().updateRecords("delete from user_data where id = ?",id);
    }

    @Override
    public void updateUser(String id, String password) {
        super.getDbUtils().updateRecords("update user_data set password = ? where id = ?",password,id);
    }

    @Override
    public void forgetUser(String account, String password) {
        super.getDbUtils().updateRecords("update user_data set password = ? where account = ?",password,account);
    }

    @Override
    public List<UserInformation> selUser(int page, int limit) {
        page = (page-1)*limit;
        return super.getDbUtils().queryMultipleRowsOfRecords(UserInformation.class,
                "select * from user_data limit ? , ?", page, limit);
    }

    @Override
    public List<UserInformation> selLikeUser(String like, int page, int limit) {
        page = (page-1)*limit;
        return super.getDbUtils().queryMultipleRowsOfRecords(UserInformation.class,
                "select * from user_data where username like '%" + like + "%' limit ? , ?", page, limit);
    }
    
}
