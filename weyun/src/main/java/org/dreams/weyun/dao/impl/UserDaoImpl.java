package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.User;
import org.dreams.weyun.mapper.UserMapper;
import org.dreams.weyun.dao.UserDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class UserDaoImpl extends ServiceImpl<UserMapper, User> implements UserDao {

}
