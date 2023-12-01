package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.Grade;
import org.dreams.weyun.mapper.GradeMapper;
import org.dreams.weyun.dao.GradeDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class GradeDaoImpl extends ServiceImpl<GradeMapper, Grade> implements GradeDao {

}
