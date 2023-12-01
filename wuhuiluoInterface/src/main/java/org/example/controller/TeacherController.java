package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.annotation.UserLoginToken;
import org.example.pojo.TeacherInformation;
import org.example.service.TeacherService;
import org.example.utils.Conversion;
import org.example.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TeacherController {
    private TeacherService teacherService;

    private final Class<TeacherInformation> t = TeacherInformation.class;
    private Result<List<TeacherInformation>> result;

    @UserLoginToken
    public void addTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TeacherInformation teacherInformation = Conversion.requestParams(request.getParameterMap(), t);
        result = Result.wrapResult(request, true, teacherService, () -> Result.auto(teacherService.addTeacher(teacherInformation)));
        response.getWriter().println(JSONObject.toJSONString(result));
    }

    @UserLoginToken
    public void delTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        result = Result.wrapResult(request, false, teacherService, () -> Result.auto(teacherService.delTeacher(Integer.parseInt(id))));
        response.getWriter().println(JSONObject.toJSONString(result));
    }

    @UserLoginToken
    public void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TeacherInformation teacherInformation = Conversion.requestParams(request.getParameterMap(), t);
        result = Result.wrapResult(request, false, teacherService, () -> Result.auto(teacherService.updateTeacher(teacherInformation)));
        response.getWriter().println(JSONObject.toJSONString(result));
    }

    @UserLoginToken
    public void selTeacherList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        result = Result.wrapResult(request, false, teacherService, () -> Result.auto(teacherService.selectTeacher()));
        response.getWriter().println(JSONObject.toJSONString(result));
    }

}
