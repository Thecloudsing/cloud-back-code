package org.example.DAObase.dao.impl;

import org.example.DAObase.dao.CommonBasicAbstract;
import org.example.DAObase.dao.StudentBasicDAO;
import org.example.pojo.StudentInformation;

import java.util.List;

public class StudentBasicDAOImpl extends CommonBasicAbstract implements StudentBasicDAO {

    public StudentBasicDAOImpl() {
        super();
        super.setTableName("student");
    }

    @Override
    public void addStudent(StudentInformation studentInformation) {
        super.getDbUtils().updateRecords("insert into student " +
                        "(studentName, birthday, grade, gender, studentId, school) " +
                        "values (?, ?, ?, ?, ?, ?)",
                studentInformation.getStudentName(),
                studentInformation.getBirthday(),
                studentInformation.getGrade(),
                studentInformation.getGender(),
                studentInformation.getStudentId(),
                studentInformation.getSchool()
                );
    }

    @Override
    public void delStudent(int id) {
        super.getDbUtils().updateRecords("delete from student where id = ?", id);
    }

    @Override
    public void updateStudent(StudentInformation studentInformation) {
        super.getDbUtils().updateRecords("update student set studentName = ? , " +
                        "birthday = ? , " +
                        "grade = ? , " +
                        "gender = ? , " +
                        "school = ? , " +
                        "studentId = ? " +
                        "where id = ?",
                studentInformation.getStudentName(),
                studentInformation.getBirthday(),
                studentInformation.getGrade(),
                studentInformation.getGender(),
                studentInformation.getSchool(),
                studentInformation.getStudentId(),
                studentInformation.getId());
    }

    @Override
    public List<StudentInformation> selStudent(int page, int limit) {
        page = (page-1)*limit;
        return super.getDbUtils().queryMultipleRowsOfRecords(StudentInformation.class,
                "select * from student limit ? , ?", page, limit);
    }

    @Override
    public List<StudentInformation> selLikeStudent(String like, int page, int limit) {
        page = (page-1)*limit;
        return super.getDbUtils().queryMultipleRowsOfRecords(StudentInformation.class,
                "select * from student where studentName like '%" + like + "%' limit ? , ?", page, limit);

    }
}
