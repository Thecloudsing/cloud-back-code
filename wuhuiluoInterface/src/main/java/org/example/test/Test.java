package org.example.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws IOException, SQLException {
//        StudentInformation studentInformation = new StudentInformation();
//        studentInformation.setId("666");
//        studentInformation.setBirthday("666");
//        studentInformation.setStudentId("666");
//        StringUtil.isNotEmptyFilters(studentInformation, "studentName");

        Properties properties = new Properties();

//        Path path = Paths.get(Objects.requireNonNull(Druid.class.getClassLoader().getResource("druid.properties")).getPath().substring(1));
//        properties.load(Files.newInputStream(path));
//        String initialize = properties.getProperty("initialize");
//        properties.setProperty("initialize", "1");
//        properties.store(Files.newOutputStream(path), "druid operate file");

        Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "331520");
        Statement statement = connection1.createStatement();
        statement.executeUpdate("create database if not exists `test` default character set utf8mb4");
        statement.close();
        connection1.close();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true", "root", "331520");
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        Resources.setCharset(StandardCharsets.UTF_8);
        scriptRunner.setLogWriter(null);
        scriptRunner.runScript(
                new InputStreamReader(
                        Files.newInputStream(
                                Paths.get("C:\\Users\\17561\\IdeaProjects\\wuhuiluoInterface\\src\\main\\resources\\admin-system.sql")),
                        StandardCharsets.UTF_8));
        connection.close();
    }
}
