package org.example.service;

import org.example.pojo.StudentInformation;

import java.util.List;

public interface StudentService extends CommonService {

    List<StudentInformation> selectStudent();
//    List<StudentInformation> selectLikeStudent();

    List<StudentInformation> addStudent(StudentInformation studentInformation);
    List<StudentInformation> updateStudent(StudentInformation studentInformation);
    List<StudentInformation> delStudent(int id);

}
