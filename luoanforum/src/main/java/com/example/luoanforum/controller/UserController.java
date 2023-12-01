package com.example.luoanforum.controller;

//import cn.hutool.json.JSONObject;
import com.example.luoanforum.annotation.UserLoginToken;
import com.example.luoanforum.pojo.SignRegisterJson;
import com.example.luoanforum.service.LoginService;
import com.example.luoanforum.service.TokenService;
import com.example.luoanforum.service.TopicService;
//import com.example.luoanforum.service.loginService;
import com.example.luoanforum.token.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


/**
 * @author ：Mr.ZJW
 * @date ：Created 2022/2/26 10:47
 * @description：
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TopicService topicService;
    
    
    @Autowired
    private TokenService tokenService;

    /**
     * 查询用户信息
     * @return
     */
    @UserLoginToken
    @GetMapping("/list")
    public Result<Object> list(){
        return Result.success(topicService.viewDynamicsAll("1"));
    }


    /**
     * 登录验证
     * @param signRegisterJson
     * @param response
     * @return
     */
//    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
//    public Result<Object> login(@RequestBody SignRegisterJson signRegisterJson, HttpServletResponse response) {
//        JSONObject jsonObject = new JSONObject();
//        //获取用户账号密码
//        User userForBase = new User();
//        signRegisterJson.setId(loginService.findByUsername(user).getId());
//        userForBase.setUsername(loginService.findByUsername(user).getUsername());
//        userForBase.setPassword(loginService.findByUsername(user).getPassword());
//        //判断账号或密码是否正确
//        if (!userForBase.getPassword().equals(user.getPassword())) {
//            return Result.error(CodeMsg.USER_OR_PASS_ERROR);
//        } else {
//            String token = tokenService.getToken();
//            jsonObject.put("token", token);
//            Cookie cookie = new Cookie("token", token);
//            cookie.setPath("/");
//            response.addCookie(cookie);
//            return Result.success(jsonObject);
//        }
//    }

}

