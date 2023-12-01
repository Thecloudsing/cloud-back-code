package com.dreams.oauth2.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreams.oauth2.domain.entity.SysAuthority;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author luoan
 * @since 2023/10/25
 */
public interface ISysAuthorityDao extends IService<SysAuthority> {

    /**
     * 根据用户id获取权限列表
     *
     * @param userId 用户id
     * @return 权限列表
     */
    List<SysAuthority> getByUserId(Integer userId);

}
