package org.example.service;

import org.example.pojo.TeacherInformation;

import java.util.List;

public interface TeacherService extends CommonService {


    List<TeacherInformation> selectTeacher();
//    List<TeacherInformation> selectLikeTeacher();
    List<TeacherInformation> addTeacher(TeacherInformation teacherInformation);
    List<TeacherInformation> updateTeacher(TeacherInformation teacherInformation);

    List<TeacherInformation> delTeacher(int id);

    
}
