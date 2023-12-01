package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.Subject;
import org.dreams.weyun.mapper.SubjectMapper;
import org.dreams.weyun.dao.SubjectDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class SubjectDaoImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectDao {

}
