package org.dreams.weyun.service.impl;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.dreams.weyun.dao.SchoolDao;
import org.dreams.weyun.domain.entity.School;
import org.dreams.weyun.domain.vo.request.SchoolAddOrPutRequest;
import org.dreams.weyun.service.SchoolService;

import java.util.List;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/11/29
 */

@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolDao schoolDao;

    @Override
    public void addSchool(SchoolAddOrPutRequest school) {
        School build = School.builder().build();
        BeanUtil.copyProperties(school, build);
        schoolDao.save(build);
    }

    @Override
    public void updateSchool(SchoolAddOrPutRequest school) {
        School build = School.builder().build();
        BeanUtil.copyProperties(school, build);
        schoolDao.updateById(build);
    }

    @Override
    public void deleteSchoolById(Integer id) {
        schoolDao.removeById(id);
    }

    @Override
    public void deleteSchoolByIds(List<Integer> ids) {
        schoolDao.removeBatchByIds(ids);
    }

    @Override
    public School querySchoolByEncodeAndName(String encode, String name) {
        return schoolDao.getByEncodeAndName(encode, name);
    }

    @Override
    public School querySchoolById(Integer id) {
        return schoolDao.getById(id);
    }
}
