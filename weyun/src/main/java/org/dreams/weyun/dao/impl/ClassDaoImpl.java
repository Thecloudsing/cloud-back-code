package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.Class;
import org.dreams.weyun.mapper.ClassMapper;
import org.dreams.weyun.dao.ClassDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class ClassDaoImpl extends ServiceImpl<ClassMapper, Class> implements ClassDao {

}
