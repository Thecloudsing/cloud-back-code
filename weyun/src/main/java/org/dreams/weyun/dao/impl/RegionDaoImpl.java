package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.Region;
import org.dreams.weyun.mapper.RegionMapper;
import org.dreams.weyun.dao.RegionDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class RegionDaoImpl extends ServiceImpl<RegionMapper, Region> implements RegionDao {

}
