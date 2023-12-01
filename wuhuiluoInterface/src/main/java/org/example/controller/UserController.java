package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.annotation.UserLoginToken;
import org.example.pojo.UserInformation;
import org.example.service.UserService;
import org.example.utils.Conversion;
import org.example.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController {
    private UserService userService;
    private final Class<UserInformation> u = UserInformation.class;
    private Result<?> result = null;

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserInformation userInformation = Conversion.requestParams(request.getParameterMap(), u);
        result = Result.auto(userService.signIn(userInformation));
        response.getWriter().println(JSONObject.toJSONString(result));

    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserInformation userInformation = Conversion.requestParams(request.getParameterMap(), u);
        result = Result.wrapResult(request, false, userService, () -> Result.auto(userService.register(userInformation)));
        response.getWriter().println(JSONObject.toJSONString(result));

    }

//    public void getUserList(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String page = request.getParameter("page");
//        String limit = request.getParameter("limit");
//        int[] ints;
//        if ((ints = parseInt(page,limit))[0] == 0)
//            streamData = new StreamData();
//        else {
//            int[] examine = userService.examineUserPage(ints[1],ints[2]);
//            int userMaxPage = userService.getUserMaxPage(examine[2]);
//            streamData = new StreamData("userList", userService.selectUser(examine[0], examine[1]));
//            streamData.addData("currentPage",examine[0]);
//            streamData.addData("lastPage",userMaxPage);
//
//        }
//        response.getWriter().println(JSONObject.toJSONString(streamData));
//        
//    }

    @UserLoginToken
    public void getUserList(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String like = request.getParameter("like");
//        String page = request.getParameter("page");
//        String limit = request.getParameter("limit");
//
//        if (StringUtil.isNotEmpty(like)) {
//            userService.setPageLimit(like, page, limit);
//            result = Result.auto(userService.selectLikeUser(like));
//        } else {
//            userService.setPageLimit(null, page, limit);
//            result = Result.auto(userService.selectUser());
//        }
//        result.setCurrentPage(userService.getCurrentPage());
//        result.setLastPage(userService.getLastPage());
//        response.getWriter().println(JSONObject.toJSONString(result));
//        String like = request.getParameter("like");
//        String page = request.getParameter("page");
//        String limit = request.getParameter("limit");
//        like = StringUtil.isNotEmpty(like) ? like : null;
//        userService.setPageLimit(like, page, limit);
//        Result<List<UserInformation>> result = like != null ?
//                Result.auto(userService.selectLikeUser()) :
//                Result.auto(userService.selectUser());
//        result.setTotalCount(userService.getTotalCount());
//        result.setCurrentPage(userService.getCurrentPage());
//        result.setLastPage(userService.getLastPage());
        result = Result.wrapResult(request, false, userService, () -> Result.auto(userService.selectUser()));
        response.getWriter().println(JSONObject.toJSONString(result));
    }

    @UserLoginToken
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserInformation userInformation = Conversion.requestParams(request.getParameterMap(), u);
        result = Result.wrapResult(request, false, userService, () -> Result.auto(userService.alterPassword(userInformation)));
        response.getWriter().println(JSONObject.toJSONString(result));
    }

    @UserLoginToken
    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserInformation userInformation = Conversion.requestParams(request.getParameterMap(), u);
        result = Result.wrapResult(request, false, userService, () -> Result.auto(userService.changePassword(userInformation)));
        response.getWriter().println(JSONObject.toJSONString(result));
    }

    @UserLoginToken
    public void delUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        result = Result.wrapResult(request, false, userService, () -> Result.auto(userService.delUser(id)));
        response.getWriter().println(JSONObject.toJSONString(result));
    }

//    private int[] parseInt(String ...values) {
//        int[] ints = new int[values.length+1];
//        for (int i = 0; i < values.length; i++) {
//            try {
//                ints[i+1] = Integer.parseInt(values[i]);
//            } catch (Exception e) {
//                return ints;
//            }
//        }
//        ints[0] = 1;
//        return ints;
//    }
}

