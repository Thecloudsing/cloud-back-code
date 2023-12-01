package org.example.service.impl;

import org.example.DAObase.dao.TeacherBasicDAO;
import org.example.pojo.TeacherInformation;
import org.example.service.CommonAbstract;
import org.example.service.ServiceException;
import org.example.service.TeacherService;
import org.example.utils.StringUtil;

import java.util.List;

public class TeacherServiceImpl extends CommonAbstract implements TeacherService {

    private TeacherBasicDAO teacherBasicDAO;
    @Override
    public List<TeacherInformation> selectTeacher() {
        if (like == null)
            return teacherBasicDAO.selTeacher(currentPage, limit);
        return teacherBasicDAO.selLikeTeacher(like, currentPage, limit);
    }

//    @Override
//    public List<TeacherInformation> selectLikeTeacher() {
//        return teacherBasicDAO.selLikeTeacher(like, currentPage, limit);
//    }

    @Override
    public List<TeacherInformation> addTeacher(TeacherInformation teacherInformation) {
        if (StringUtil.isEmptyFilters(teacherInformation, "id", "created_at"))
            throw new ServiceException(ServiceException.REQUEST_PARAMETER_NULL_VALUE);
        teacherBasicDAO.addTeacher(teacherInformation);
        return selectTeacher();
    }

    @Override
    public List<TeacherInformation> updateTeacher(TeacherInformation teacherInformation) {
        if (StringUtil.isEmptyFilters(teacherInformation, "created_at"))
            throw new ServiceException(ServiceException.REQUEST_PARAMETER_NULL_VALUE);
        teacherBasicDAO.updateTeacher(teacherInformation);
        return selectTeacher();
    }

    @Override
    public List<TeacherInformation> delTeacher(int id) {
        teacherBasicDAO.delTeacher(id);
        return selectTeacher();
    }

    @Override
    public int getTotalCount() {
        if (pageCount == null || loop)
            pageCount = like == null ?
                    teacherBasicDAO.getCount() :
                    teacherBasicDAO.getCount(" where teacherName like '%" + like + "%'");
        return pageCount;
    }
}
