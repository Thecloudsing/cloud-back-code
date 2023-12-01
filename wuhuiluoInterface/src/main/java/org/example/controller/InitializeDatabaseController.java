package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.DAObase.Druid;
import org.example.mvc.Interceptor;
import org.example.service.ServiceException;
import org.example.utils.CodeMsg;
import org.example.utils.Result;
import org.example.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class InitializeDatabaseController {

    private Druid druid;

    public static String username;
    public static String password;
    private final Properties properties;
    private final Path path = Paths.get(Objects.requireNonNull(Druid.class.getClassLoader().getResource("admin.properties")).getPath().substring(1));

    public InitializeDatabaseController() throws IOException {
        properties = new Properties();
        properties.load(Files.newInputStream(path));
        username = properties.getProperty("admin-username");
        password = properties.getProperty("admin-password");
    }

    public void initialize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result<String> result = null;
        String host = request.getParameter("host");
        String databaseName = request.getParameter("databaseName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtil.isEmpty(host, databaseName, username, password))
            throw new ServiceException(ServiceException.REQUEST_PARAMETER_NULL_VALUE);
        if (druid.initializeDatabase(host, databaseName, username, password))
            result = new Result<>(CodeMsg.SUCCESS, "初始成功");
        else
            result = Result.error(CodeMsg.USER_OR_PASS_ERROR, "数据库连接失败");
        response.getWriter().println(JSONObject.toJSONString(result));
    }

    public void addAdministrator(HttpServletRequest request, HttpServletResponse response) throws IOException {
        username = request.getParameter("username");
        password = request.getParameter("password");
        properties.setProperty("admin-username", username);
        properties.setProperty("admin-password", password);
        properties.store(Files.newOutputStream(path), "admin");
        response.getWriter().println(JSONObject.toJSONString(Result.Redirect(301, Interceptor.rootPath + "/", "主页")));
    }

    public void sign(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("account");
        String password = request.getParameter("password");
        if (!(InitializeDatabaseController.username.equals(username) &&
        InitializeDatabaseController.password.equals(password))) {
            response.getWriter().println(JSONObject.toJSONString(Result.error(CodeMsg.USER_OR_PASS_ERROR)));
            return;
        }
        response.getWriter().println(JSONObject.toJSONString(Result.Redirect(300,Interceptor.rootPath + "/", "主页")));
    }
}


