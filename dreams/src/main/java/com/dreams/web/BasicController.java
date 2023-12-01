/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dreams.web;

import com.dreams.common.domain.vo.response.ApiResult;
import com.dreams.common.exception.BusinessException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
//@RestController
public class BasicController {

    // http://127.0.0.1:8080/hello?name=lisi

    /**
     * hello world
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }
    @RequestMapping("/r")
    @ResponseBody
    public String r(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "this test " + name;
    }
    @RequestMapping("/api/user")
    @ResponseBody
    public Object user(@RequestParam(name = "name", defaultValue = "unknown user") String name) throws InterruptedException {
//        return ApiResult.success( "Hello " + name);
        Thread.sleep(1000);
        return "user => " + name + "hello, this is test jrebel! sss";
    }
    @RequestMapping("/api/test")
    @ResponseBody
    public Object test(@RequestParam(name = "name", defaultValue = "unknown user") String name) throws InterruptedException {
//        return ApiResult.success( "Hello " + name);
        Thread.sleep(2000);
        return "okay => " + name + "abc test jrebel! sss";
    }

    @RequestMapping("/api/error")
    @ResponseBody
    public Object error(@RequestParam(name = "name", defaultValue = "unknown user") String name) throws InterruptedException {
//        return ApiResult.success( "Hello " + name);
        Thread.sleep(1000);
        throw new BusinessException(500, "error: " + name);
//        return "okay => " + name;
    }
    @RequestMapping("/api/redirect")
    @ResponseBody
    public ApiResult<String> redirect(@RequestParam(name = "name", defaultValue = "unknown user") String name, HttpServletResponse resp) throws InterruptedException, IOException {
//        return ApiResult.success( "Hello " + name);
        Thread.sleep(1000);
//        resp.sendRedirect("/api/test?name=" + name);
//        resp.sendError(401, "error:auth -> " + name);
        return ApiResult.redirect("/");
    }

    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    @ResponseBody
    public User user() {
        User user = new User();
        user.setName("theonefx");
        user.setAge(666);
        return user;
    }

    // http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/save_user")
    @ResponseBody
    public String saveUser(User u) {
        return "user will save: name=" + u.getName() + ", age=" + u.getAge();
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html() {
        return "index.html";
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
        user.setName("zhangsan");
        user.setAge(18);
    }
}
