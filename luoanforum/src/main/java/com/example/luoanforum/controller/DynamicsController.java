package com.example.luoanforum.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.luoanforum.pojo.*;
import com.example.luoanforum.service.*;
import com.example.luoanforum.token.CodeMsg;
import com.example.luoanforum.token.Result;
import com.example.luoanforum.util.VerificationCodeUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Mono;

//import javax.servlet.http.Cookie;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author 落扶苏
 * @version 1.1
 */
//@Controller

    @RestController
//    @Controller
    @RequestMapping(produces = {"text/html;charset=UTF-8;", "application/json;"})
    public class DynamicsController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RediscoverService rediscoverService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;

    @GetMapping("/text")
    public String text() {
        return "看什么看，想干嘛，哼！";
    }

    @GetMapping("/main")
//    首页
    public void homePage(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String page = req.getParameter("page");
        List<TopicInformation> topicInformations = topicService.viewDynamicsAll(page);
        JsonData jsonData = new JsonData();
        //String dynamics = JSONObject.fromObject(topicInformations).toString();
        jsonData.putData("topicInformations",topicInformations);
        jsonData.putData("lastPage",topicService.getLastPage());
        jsonData.putData("currentPage",topicService.inspectPage(page));
        HttpSession session = req.getSession();
//        if (session.getAttribute("sessionId") != null) {
//            Cookie[] cookies = req.getCookies();
//            if (cookies != null && cookies.length != 0) {
//                for (Cookie cookie : cookies) {
//                    if (cookie.getName().equals("accountPwdCookie")) {
//                        String value = cookie.getValue();
//                        if (value != null) {
//                            AccountPwdCookie accountPwdCookie = JSONObject.parseObject(value,AccountPwdCookie.class);
//                            if (this.signInLogic(accountPwdCookie.getAccount(), accountPwdCookie.getPassword()) != null) {
//                                UserInformation userInformation = userService.returnUserInformation(accountPwdCookie.getUid());
//                                session.setAttribute("userInformation", userInformation);
//                                jsonData.putData("userInformation", userInformation);
//                            }
//                        }
//                    }
//                }
//            }
//        }
        jsonData.setSuccess();
        String s = JSONObject.toJSON(jsonData).toString();
//        for (TopicInformation topicInformation: topicInformations)
//            System.out.println(Arrays.toString(topicInformation.getPicture_url()));
        resp.getWriter().println(s);
    }

    @GetMapping("/infocenter/{account}")
//    个人主页
    public void personalHomepage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uid = req.getParameter("uid");
        JsonData jsonData = new JsonData();
        Object userInformation = req.getSession().getAttribute("userInformation");
        jsonData.putData("topicInformations", topicService.viewDynamics(uid));
        if (userInformation == null) {
            jsonData.putData("userInformation", userService.returnUserInformation(uid));
        } else {
            jsonData.putData("userInformation", userInformation);
        }
        jsonData.setSuccess();
        String s = JSONObject.toJSON(jsonData).toString();
        resp.getWriter().println(s);
    }

    @PostMapping("/sign/{type}")
//    登录页面
    public void loginPage(@PathVariable String type, @RequestBody SignRegisterJson signRegisterJson, HttpSession session, HttpServletResponse resp) throws IOException {
        JsonData jsonData = new JsonData();
        boolean loginStatus = false;
        AccountPwdCookie accountPwdCookie = null;
        if (Objects.equals(type,"accountLogin")) {
            accountPwdCookie = signInLogic(signRegisterJson.getAccount(), signRegisterJson.getPassword());
            if (accountPwdCookie != null) loginStatus = true;
        } else if (Objects.equals(type,"emailLogin")) {
            String emailLoginCode = (String) session.getAttribute("emailLoginCode");
            if (VerificationCodeUtil.Code(signRegisterJson.getPassword(),emailLoginCode)) {
                if ((accountPwdCookie = loginService.getInformation(signRegisterJson.getAccount())) != null) loginStatus = true;
            }
        }

        jsonData.putData("loginStatus",loginStatus);

        if (loginStatus) {
//            session.setAttribute("sessionId", session.getId());
//            session.setAttribute("accountPwdCookie", accountPwdCookie);
//            String accountPwdCookieJSON = JSONObject.toJSON(accountPwdCookie).toString();
//            Cookie cookie = new Cookie("accountPwdCookie", accountPwdCookieJSON);
//            cookie.setPath("/");
//            cookie.setMaxAge(60 * 60 * 24 * 7);
//            resp.addCookie(cookie);
//
            jsonData.setSuccess();
            String token = tokenService.getToken(signRegisterJson.getAccount(), signRegisterJson.getPassword());
//            jsonObject.put("token", token);
            jsonData.putData("token",token);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            resp.addCookie(cookie);

            UserInformation userInformation = userService.returnUserInformation(accountPwdCookie.getUid());
            jsonData.putData("userInformation",userInformation);
//            return Result.success(jsonData);
        } else jsonData.setError(400, "user or password file");
        String s = JSONObject.toJSON(jsonData).toString();
        resp.getWriter().println(s);

//        return Result.error(CodeMsg.USER_OR_PASS_ERROR);
    }

//    获取邮箱登录验证码
    @GetMapping("/sign/{account}")
    public void getLoginEmailCode(@PathVariable("account") String account, HttpSession session, HttpServletResponse response) throws IOException {
        String emailLoginCode = loginService.getEmailLoginCode(account);
        boolean signStatus = emailLoginCode != null;
        if (signStatus) session.setAttribute("emailLoginCode",emailLoginCode);
        JsonData jsonData = new JsonData(true);
        jsonData.putData("signStatus",signStatus);
        String s = JSONObject.toJSON(jsonData).toString();
        response.getWriter().println(s);
    }

    @PostMapping("/register/{type}")
//    注册页面
    public void registerPage(@PathVariable("type") String type,
                             @RequestBody SignRegisterJson signRegisterJson,
                             HttpSession session,
                             HttpServletResponse resp) throws IOException {
        JsonData jsonData = new JsonData();
        boolean registerStatus = false;
        if (Objects.equals(type,"accountRegister")) {
            String uid = registerService.registerAccount(signRegisterJson.getAccount(), signRegisterJson.getPassword(), signRegisterJson.getUsername());
            if (uid != null) registerStatus = true;
        } else if (Objects.equals(type,"emailRegister")) {
            if (VerificationCodeUtil.Code(signRegisterJson.getRegisterCode(),(String) session.getAttribute("emailRegisterCode"))) {
                if (registerService.registerEmail(signRegisterJson.getAccount(), signRegisterJson.getPassword(), signRegisterJson.getUsername()) != null) registerStatus = true;
            }
        }
        jsonData.putData("registerStatus",registerStatus);
        jsonData.setSuccess();
        String s = JSONObject.toJSON(jsonData).toString();
        resp.getWriter().println(s);
    }

    @GetMapping("/duplicate/{type}/{account}")
//    账号查重
    public void duplicateQuery(@PathVariable("account") String account,@PathVariable("type") String type, HttpServletResponse resp) throws IOException {
        JsonData jsonData = new JsonData();
        PrintWriter writer = resp.getWriter();
        if (type.equals("account")) {
            jsonData.putData("duplicateOrNot", registerService.duplicateQueryAccount(account) != null);
            jsonData.setSuccess();
        } else if (type.equals("email")) {
            jsonData.putData("duplicateOrNot", registerService.duplicateQueryEmail(account) != null);
            jsonData.setSuccess();
        }
        String s = JSONObject.toJSON(jsonData).toString();
        writer.println(s);
    }

    @GetMapping("/msg")
    public void getMsg(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.getWriter().println("hello world");
    }


    @GetMapping("/register/{email}")
    //    获取邮箱注册验证码
    public void getRegisterEmailCode(@PathVariable("email") String email, HttpSession session, HttpServletResponse response) throws IOException {
        String emailRegisterCode = registerService.getMailboxVerificationCode(email);
        boolean registerStatus = emailRegisterCode != null;
        if (registerStatus) session.setAttribute("emailRegisterCode",emailRegisterCode);
        JsonData jsonData = new JsonData(true);
        jsonData.putData("registerStatus",registerStatus);
        String s = JSONObject.toJSON(jsonData).toString();
        response.getWriter().println(s);
    }


    private AccountPwdCookie signInLogic(String account, String password) {
        AccountPwdCookie accountPwdCookie = loginService.accountVerificationLogin(account, password);
        if (accountPwdCookie == null) {
            accountPwdCookie = loginService.emailVerificationLogin(account, password);
            if (accountPwdCookie == null) {
                accountPwdCookie = loginService.smsVerificationLogin(account,password);
            }
        }
        return accountPwdCookie;
    }
}
