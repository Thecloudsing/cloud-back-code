package org.example.DAObase.dao;

import org.example.pojo.StudentInformation;

import java.util.List;

public interface StudentBasicDAO extends CommonBasicDAO {

    void addStudent(StudentInformation studentInformation);
    void delStudent(int id);
    void updateStudent(StudentInformation studentInformation);
    List<StudentInformation> selStudent(int page, int limit);
    List<StudentInformation> selLikeStudent(String like, int page, int limit);
    
}
