package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.Role;
import org.dreams.weyun.mapper.RoleMapper;
import org.dreams.weyun.dao.RoleDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class RoleDaoImpl extends ServiceImpl<RoleMapper, Role> implements RoleDao {

}
