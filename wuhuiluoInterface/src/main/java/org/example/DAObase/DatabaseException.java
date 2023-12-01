package org.example.DAObase;

public class DatabaseException extends RuntimeException {

    protected static final DatabaseException READ_PROPERTIES_EXCEPTION = new DatabaseException("读取数据库配置异常");
    protected static final DatabaseException INITIALIZE_EXCEPTION = new DatabaseException("数据库没有初始化，请到安装界面初始完后，移除install文件夹");
    protected static final DatabaseException CREATE_DATABASE_EXCEPTION = new DatabaseException("创建数据库异常请检查数据库配置");
    protected static final DatabaseException SQL_EXCEPTION = new DatabaseException("sql执行异常");

    public DatabaseException(String message) {
        super(message);
    }
    public DatabaseException(String message, Exception e) {
        super(message, e);
    }
    public DatabaseException(Exception init_e, Exception e) {
        super(init_e.getMessage(), e);
    }
}
