package org.example.service.impl;

import org.example.DAObase.dao.StudentBasicDAO;
import org.example.pojo.StudentInformation;
import org.example.service.CommonAbstract;
import org.example.service.ServiceException;
import org.example.service.StudentService;
import org.example.utils.StringUtil;

import java.util.List;

public class StudentServiceImpl extends CommonAbstract implements StudentService {
    private StudentBasicDAO studentBasicDAO;
    @Override
    public List<StudentInformation> selectStudent() {
        if (like == null)
            return studentBasicDAO.selStudent(currentPage, limit);
        return studentBasicDAO.selLikeStudent(like, currentPage, limit);
    }

//    @Override
//    public List<StudentInformation> selectLikeStudent() {
//        return studentBasicDAO.selLikeStudent(like, currentPage, limit);
//    }

    @Override
    public List<StudentInformation> addStudent(StudentInformation studentInformation) {
        if (StringUtil.isEmptyFilters(studentInformation, "id", "created_at"))
            throw new ServiceException(ServiceException.REQUEST_PARAMETER_NULL_VALUE);
        studentBasicDAO.addStudent(studentInformation);
        return selectStudent();
    }

    @Override
    public List<StudentInformation> updateStudent(StudentInformation studentInformation) {
        if (StringUtil.isEmptyFilters(studentInformation, "created_at"))
            throw new ServiceException(ServiceException.REQUEST_PARAMETER_NULL_VALUE);
        studentBasicDAO.updateStudent(studentInformation);
        return selectStudent();
    }

    @Override
    public List<StudentInformation> delStudent(int id) {
        studentBasicDAO.delStudent(id);
        return selectStudent();
    }

    @Override
    public int getTotalCount() {
        if (pageCount == null || loop)
            pageCount = like == null ?
                    studentBasicDAO.getCount() :
                    studentBasicDAO.getCount(" where studentName like '%" + like + "%'");
        return pageCount;
    }

}
