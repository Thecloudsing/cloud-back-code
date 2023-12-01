package com.luoanforum.authorization.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/", produces = "application/json;charset=UTF-8")
public class CallbackController {

    @RequestMapping("/callback")
    public String callback(String code, String state) {
        return "callback \n code: " + code + "\n state: " + state + "\n";
    }

    @RequestMapping("/getCode")
    public String getCode(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:9900/oauth2/authorize?response_type=code&client_id=oidc-client&scope=openid&redirect_uri=http://10.10.10.7:9900/callback");
        return "getCode:success";
    }
}
