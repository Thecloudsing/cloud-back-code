package org.example.DAObase.dao;

import org.example.pojo.TeacherInformation;

import java.util.List;

public interface TeacherBasicDAO extends CommonBasicDAO {

    void addTeacher(TeacherInformation teacherInformation);
    void delTeacher(int id);
    void updateTeacher(TeacherInformation teacherInformation);
    List<TeacherInformation> selTeacher(int page, int limit);
    List<TeacherInformation> selLikeTeacher(String like, int page, int limit);
}
