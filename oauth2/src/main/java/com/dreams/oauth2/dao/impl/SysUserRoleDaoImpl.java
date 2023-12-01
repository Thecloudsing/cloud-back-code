package com.dreams.oauth2.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreams.oauth2.dao.ISysUserRoleDao;
import com.dreams.oauth2.domain.entity.SysUserRole;
import com.dreams.oauth2.mapper.SysUserRoleMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author luoan
 * @since 2023-07-04
 */
@Repository
public class SysUserRoleDaoImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleDao {

}
