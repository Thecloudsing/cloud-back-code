package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.SchoolType;
import org.dreams.weyun.mapper.SchoolTypeMapper;
import org.dreams.weyun.dao.SchoolTypeDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class SchoolTypeDaoImpl extends ServiceImpl<SchoolTypeMapper, SchoolType> implements SchoolTypeDao {

}
