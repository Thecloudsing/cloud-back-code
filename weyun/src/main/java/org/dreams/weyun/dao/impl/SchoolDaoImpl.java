package org.dreams.weyun.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dreams.weyun.dao.SchoolDao;
import org.dreams.weyun.domain.entity.School;
import org.dreams.weyun.domain.enums.DeleteFlagEnum;
import org.dreams.weyun.mapper.SchoolMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class SchoolDaoImpl extends ServiceImpl<SchoolMapper, School> implements SchoolDao {

    @Override
    public School getByEncodeAndName(String encode, String name) {
        return lambdaQuery()
                .eq(School::getRegionId, encode)
                .eq(School::getSchoolName, name)
                .eq(School::getDeleteFlag, DeleteFlagEnum.NORMAL.getStatus())
                .one();
    }

    @Override
    public boolean removeById(Serializable id) {
        return lambdaUpdate().set(School::getDeleteFlag, DeleteFlagEnum.DELETE.getStatus())
                .eq(School::getSchoolId, id)
                .update();
    }

    @Override
    public boolean removeBatchByIds(Collection<?> list) {
        Set<School> delS = list.stream().map(
                (id) -> School.builder()
                        .schoolId((Integer) id)
                        .deleteFlag(DeleteFlagEnum.DELETE.getStatus())
                        .build())
                .collect(Collectors.toSet());
        return updateBatchById(delS);
    }
}
