package org.example.DAObase;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;

public class Druid {

    public static boolean initState = false;
    private DataSource dataSource;
    private final Properties properties;

   private final Path path = Paths.get(Objects.requireNonNull(Druid.class.getClassLoader().getResource("druid.properties")).getPath().substring(1));

    public Druid() {
        try {
//            String property = System.getProperty("user.dir");
            properties = new Properties();
            properties.load(Files.newInputStream(path));
            Class.forName(properties.getProperty("driverClassName"));
            String initialize = properties.getProperty("initialize");
            if (initialize.equals("1")) initState = true;
        } catch (IOException | ClassNotFoundException e) {
            throw DatabaseException.READ_PROPERTIES_EXCEPTION;
        }
    }

    public boolean initializeDatabase(String host, String basename, String username, String password) {
        String url = "jdbc:mysql://" + host + "/" + basename + "?rewriteBatchedStatements=true";
        try {
            Connection connection_ = DriverManager.getConnection("jdbc:mysql://" + host, username, password);
            Statement statement = connection_.createStatement();
            statement.executeUpdate("create database if not exists `" + basename + "` default character set utf8mb4");
            statement.close();
            connection_.close();

            Connection connection = DriverManager.getConnection(url, username, password);
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            Resources.setCharset(StandardCharsets.UTF_8);
            scriptRunner.setLogWriter(null);
            scriptRunner.runScript(
                    new InputStreamReader(
                            Files.newInputStream(
                                    Paths.get(Objects.requireNonNull(Druid.class.getClassLoader().getResource("admin-system.sql")).getPath().substring(1))),
                            StandardCharsets.UTF_8));
            connection.close();
            DriverManager.getConnection(url, username, password);
            properties.setProperty("url", url);
            properties.setProperty("username", username);
            properties.setProperty("password", password);
            properties.setProperty("initialize", "1");
            properties.store(Files.newOutputStream(path), "druid operate file");
            initState = true;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Connection getConnection() throws SQLException {
        try {
            if (!initState) throw DatabaseException.INITIALIZE_EXCEPTION;
            if (dataSource == null)
                dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new DatabaseException(DatabaseException.CREATE_DATABASE_EXCEPTION, e);
        }

        return dataSource.getConnection();
    }

    public void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
