package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.Profession;
import org.dreams.weyun.mapper.ProfessionMapper;
import org.dreams.weyun.dao.ProfessionDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class ProfessionDaoImpl extends ServiceImpl<ProfessionMapper, Profession> implements ProfessionDao {

}
