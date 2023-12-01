package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.Student;
import org.dreams.weyun.mapper.StudentMapper;
import org.dreams.weyun.dao.StudentDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class StudentDaoImpl extends ServiceImpl<StudentMapper, Student> implements StudentDao {

}
