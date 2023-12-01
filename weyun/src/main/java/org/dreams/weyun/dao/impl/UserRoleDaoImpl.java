package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.UserRole;
import org.dreams.weyun.mapper.UserRoleMapper;
import org.dreams.weyun.dao.UserRoleDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class UserRoleDaoImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleDao {

}
