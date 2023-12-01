package org.example.DAObase;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class DBUtils {
    
    private Druid druid;
    private final QueryRunner queryRunner = new QueryRunner();

    void init() throws SQLException {
//        if (connection == null)
            connection = druid.getConnection();
    }

    public<T> List<T> queryMultipleRowsOfRecords
            (Class<T> aClass, String sql, Object...obj) {
        List<T> list;
        try {
            init();
            list = queryRunner.query(connection, sql, new BeanListHandler<>(aClass), obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }
        return list;
    }
    public Object queryASingleLineRecord(String sql, Object...obj) {
        Object query;
        try {
            init();
            query = queryRunner.query(connection, sql, new ScalarHandler<>(), obj);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }
        return query;
    }
    public<T> T queryASingleLineRecord(Class<T> aClass, String sql, Object...obj) {
        T query;
        try {
            init();
            query = queryRunner.query(connection, sql, new BeanHandler<>(aClass), obj);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }
        return query;
    }
    public int updateRecords(String sql,Object...obj) {
        int update;
        try {
            init();
            update = queryRunner.update(connection, sql, obj);
            return update;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }
    }
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public boolean informationIsFound(String sql, String...str) {

        boolean value = false;
        try {
            init_sql(sql, str);
            value = resultSet.next();
            return value;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }
    }

    private void init_sql(String sql, String[] str) throws SQLException {
        connection = druid.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        if (str.length != 0) {
            for (int i = 0; i < str.length; i++) {
                preparedStatement.setString(i+1, str[i]);
            }
        }
        resultSet = preparedStatement.executeQuery();
    }

    public Integer countMath(String sql, String...str) {
        Integer value = null;
        try {
            init_sql(sql, str);
            if (resultSet.next())
                value = resultSet.getInt(1);
            return value;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close();
        }
    }

    private void close() {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
