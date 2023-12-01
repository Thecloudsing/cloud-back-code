package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import org.example.annotation.UserLoginToken;
import org.example.pojo.StudentInformation;
import org.example.service.StudentService;
import org.example.utils.Conversion;
import org.example.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentController {
    private StudentService studentService;
    private final Class<StudentInformation> s = StudentInformation.class;
    private Result<List<StudentInformation>> result;

    @UserLoginToken
    public void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StudentInformation studentInformation = Conversion.requestParams(request.getParameterMap(), s);
        result = Result.wrapResult(request, true, studentService, () -> Result.auto(studentService.addStudent(studentInformation)));
        response.getWriter().println(JSONObject.toJSONString(result));
    }

    @UserLoginToken
    public void delStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        result = Result.wrapResult(request, false, studentService, () -> Result.auto(studentService.delStudent(Integer.parseInt(id))));
        response.getWriter().println(JSONObject.toJSONString(result));
    }

    @UserLoginToken
    public void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StudentInformation studentInformation = Conversion.requestParams(request.getParameterMap(), s);
        result = Result.wrapResult(request, false, studentService, () -> Result.auto(studentService.updateStudent(studentInformation)));
        response.getWriter().println(JSONObject.toJSONString(result));
    }

    @UserLoginToken
    public void selStudentList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        result = Result.wrapResult(request, false, studentService, () -> Result.auto(studentService.selectStudent()));
        response.getWriter().println(JSONObject.toJSONString(result));
    }
}
