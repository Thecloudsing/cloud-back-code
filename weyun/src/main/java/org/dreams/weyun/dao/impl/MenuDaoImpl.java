package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.Menu;
import org.dreams.weyun.mapper.MenuMapper;
import org.dreams.weyun.dao.MenuDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class MenuDaoImpl extends ServiceImpl<MenuMapper, Menu> implements MenuDao {

}
