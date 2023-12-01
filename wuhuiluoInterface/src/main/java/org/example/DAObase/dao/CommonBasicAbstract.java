package org.example.DAObase.dao;

import org.example.DAObase.DBUtils;

public abstract class CommonBasicAbstract implements CommonBasicDAO {

    private DBUtils dbUtils;

    private String tableName;

    @Override
    public Integer getCount(String parameter) {
        return dbUtils.countMath("select count(*) from " + tableName + " " + parameter);
    }

    @Override
    public Integer getCount() {
        return dbUtils.countMath("select count(*) from " + tableName);
    }

    public DBUtils getDbUtils() {
        return dbUtils;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
