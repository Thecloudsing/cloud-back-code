package com.example.luoanforum.util;//package com.luoan.util;
//
//import com.alibaba.fastjson.JSONObject;
//import com.luoan.pojo.AccountPwdCookie;
//import com.luoan.pojo.TopicInformation;
//import com.luoan.pojo.UserInformation;
//import com.luoan.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * @author 落扶苏
// * @version 1.1
// */
//@Controller
//public class DynamicsController {
//
//    @Autowired
//    private LoginService loginService;
//    @Autowired
//    private RediscoverService rediscoverService;
//    @Autowired
//    private RegisterService registerService;
//    @Autowired
//    private ReplyService replyService;
//    @Autowired
//    private TopicService topicService;
//    @Autowired
//    private UserService userService;
//
//
//    @GetMapping("/main")
////    首页
//    public void homePage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//        List<TopicInformation> topicInformations = topicService.viewDynamicsAll();
//        Map<String,Object> json = new HashMap<>();
//        //String dynamics = JSONObject.fromObject(topicInformations).toString();
//        json.put("topicInformations",topicInformations);
//        HttpSession session = req.getSession();
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
//                                json.put("userInformation", userInformation);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        String s = JSONObject.toJSON(json).toString();
//        resp.getWriter().println(s);
//    }
//
//    @GetMapping("/infocenter/{account}")
////    个人主页
//    public void personalHomepage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String uid = req.getParameter("uid");
//        Map<String,Object> json = new HashMap<>();
//        Object userInformation = req.getSession().getAttribute("userInformation");
//        json.put("topicInformations", topicService.viewDynamics(uid));
//        if (userInformation == null) {
//            json.put("userInformation", userService.returnUserInformation(uid));
//        } else {
//            json.put("userInformation", userInformation);
//        }
//        String s = JSONObject.toJSON(json).toString();
//        resp.getWriter().println(s);
//    }
//
////    登录页面
//    public void loginPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String type = req.getParameter("type");
//
//        String msg = "error";
//        HttpSession session = req.getSession();
//        AccountPwdCookie accountPwdCookie = null;
//        String account = req.getParameter("account");
//        String password = req.getParameter("password");
//        if (Objects.equals(type,"accountLogin")) {
//            accountPwdCookie = signInLogic(account, password);
//            if (accountPwdCookie == null) msg = "账号或密码错误";
//            else msg = "success";
//        } else if (Objects.equals(type,"emailLogin")) {
//            String emailLoginCode = (String) session.getAttribute("emailLoginCode");
//            if (VerificationCodeUtil.Code(password,emailLoginCode)) {
//                if ((accountPwdCookie = loginService.getInformation(account)) == null)
//                    msg = "验证码错误";
//                else msg = "success";
//            }
//        }
//
//        Map<String,Object> json = new HashMap<>();
//        json.put("state",msg);
//
//        if (msg.equals("success")) {
//            session.setAttribute("sessionId", session.getId());
//            session.setAttribute("accountPwdCookie", accountPwdCookie);
//            String accountPwdCookieJSON = JSONObject.toJSON(accountPwdCookie).toString();
//            Cookie cookie = new Cookie("accountPwdCookie", accountPwdCookieJSON);
//            cookie.setPath("/");
//            cookie.setMaxAge(60 * 60 * 24 * 7);
//            resp.addCookie(cookie);
//
//            UserInformation userInformation = userService.returnUserInformation(accountPwdCookie.getUid());
//            json.put("userInformation",userInformation);
//        }
//        String s = JSONObject.toJSON(json).toString();
//        resp.getWriter().println(s);
//    }
//
////    获取邮箱登录验证码
//    public void getLoginEmailCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String account = req.getParameter("account");
//        String emailLoginCode = loginService.getEmailLoginCode(account);
//        if (emailLoginCode != null) req.getSession().setAttribute("emailLoginCode",emailLoginCode);
//
//        resp.getWriter().println(emailLoginCode);
//    }
//
////    注册页面
//    public void registerPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        Map<String, Object> json = new HashMap<>();
//        String msg = "注册失败";
//        String type = req.getParameter("type");
//        String username = req.getParameter("username");
//        String account = req.getParameter("account");
//        String password = req.getParameter("password");
//        if (Objects.equals(type,"accountRegister")) {
//            String uid = registerService.registerAccount(account, password, username);
//            if (uid != null) msg = "success";
//        } else if (Objects.equals(type,"emailRegister")) {
//            String registerCode = req.getParameter("registerCode");
//            if (VerificationCodeUtil.Code(registerCode,(String) req.getSession().getAttribute("emailRegisterCode"))) {
//                if (registerService.registerEmail(account, password, username) != null) {
//                    msg = "success";
//                }
//            }
//        }
//        json.put("state",msg);
//        String s = JSONObject.toJSON(json).toString();
//        resp.getWriter().println(s);
//    }
//
////    账号查重
//    public void duplicateQuery(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        Map<String,Object> json = new HashMap<>();
//        String msg = "error";
//        String account = req.getParameter("account");
//        String type = req.getParameter("type");
//        PrintWriter writer = resp.getWriter();
//        if (type.equals("account")) {
//            if (registerService.duplicateQueryAccount(account) != null)
//                msg = "success";
//        } else if (type.equals("email")) {
//            if (registerService.duplicateQueryEmail(account) != null)
//                msg = "success";
//        }
//        json.put("state",msg);
//        String s = JSONObject.toJSON(json).toString();
//        writer.println(s);
//    }
//
//    //    获取邮箱注册验证码
//    public void getRegisterEmailCode(HttpServletRequest req, HttpServletResponse resp) {
//        String account = req.getParameter("account");
//        String emailLoginCode = registerService.getMailboxVerificationCode(account);
//        if (emailLoginCode != null) req.getSession().setAttribute("emailRegisterCode",emailLoginCode);
//    }
//
//
//    private AccountPwdCookie signInLogic(String account, String password) {
//        AccountPwdCookie accountPwdCookie = loginService.accountVerificationLogin(account, password);
//        if (accountPwdCookie == null) {
//            accountPwdCookie = loginService.emailVerificationLogin(account, password);
//            if (accountPwdCookie == null) {
//                accountPwdCookie = loginService.smsVerificationLogin(account,password);
//            }
//        }
//        return accountPwdCookie;
//    }
//}
