package com.dreams.oauth2.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreams.oauth2.dao.ISysRoleAuthorityDao;
import com.dreams.oauth2.domain.entity.SysRoleAuthority;
import com.dreams.oauth2.mapper.SysRoleAuthorityMapper;
import org.springframework.stereotype.Repository;


/**
 * <p>
 * 角色菜单多对多关联表 服务实现类
 * </p>
 *
 * @author luoan
 * @since 2023-07-04
 */
@Repository
public class SysRoleAuthorityDaoImpl extends ServiceImpl<SysRoleAuthorityMapper, SysRoleAuthority> implements ISysRoleAuthorityDao {

}
