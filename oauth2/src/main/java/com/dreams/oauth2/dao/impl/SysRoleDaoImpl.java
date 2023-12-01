package com.dreams.oauth2.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreams.oauth2.dao.ISysRoleDao;
import com.dreams.oauth2.domain.entity.SysRole;
import com.dreams.oauth2.mapper.SysRoleMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author luoan
 * @since 2023-07-04
 */
@Repository
public class SysRoleDaoImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleDao {

}
