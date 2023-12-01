package org.example.DAObase.dao.impl;

import org.example.DAObase.dao.CommonBasicAbstract;
import org.example.DAObase.dao.TeacherBasicDAO;
import org.example.pojo.TeacherInformation;

import java.util.List;

public class TeacherBasicDAOImpl extends CommonBasicAbstract implements TeacherBasicDAO {
    
    public TeacherBasicDAOImpl() {
        super();
        super.setTableName("teacher");
    }
    
    @Override
    public void addTeacher(TeacherInformation teacherInformation) {
        super.getDbUtils().updateRecords("insert into teacher (teacherName, subjects, callPhoneNumber, onboardingTime, baseSalary) " +
                "values (?, ?, ?, ?, ?)",
                teacherInformation.getTeacherName(),
                teacherInformation.getSubjects(),
                teacherInformation.getCallPhoneNumber(),
                teacherInformation.getOnboardingTime(), 
                teacherInformation.getBaseSalary());
    }

    @Override
    public void delTeacher(int id) {
        super.getDbUtils().updateRecords("delete from teacher where id = ?", id);
    }

    @Override
    public void updateTeacher(TeacherInformation teacherInformation) {
        super.getDbUtils().updateRecords("update teacher set teacherName = ?, " +
                        "subjects = ?, " +
                        "callPhoneNumber = ?, " +
                        "onboardingTime = ?, " +
                        "baseSalary = ? " +
                        "where id = ?",
                teacherInformation.getTeacherName(),
                teacherInformation.getSubjects(),
                teacherInformation.getCallPhoneNumber(),
                teacherInformation.getOnboardingTime(),
                teacherInformation.getBaseSalary(),
                teacherInformation.getId());
    }

    @Override
    public List<TeacherInformation> selTeacher(int page, int limit) {
        page = (page-1)*limit;
        return super.getDbUtils().queryMultipleRowsOfRecords(TeacherInformation.class,
                "select * from teacher limit ? , ?", page, limit);
    }

    @Override
    public List<TeacherInformation> selLikeTeacher(String like, int page, int limit) {
        page = (page-1)*limit;
        return super.getDbUtils().queryMultipleRowsOfRecords(TeacherInformation.class,
                "select * from teacher where teacherName like '%" + like + "%' limit ? , ?", page, limit);

    }
    
}
