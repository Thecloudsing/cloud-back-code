package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.Teacher;
import org.dreams.weyun.mapper.TeacherMapper;
import org.dreams.weyun.dao.TeacherDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class TeacherDaoImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherDao {

}
