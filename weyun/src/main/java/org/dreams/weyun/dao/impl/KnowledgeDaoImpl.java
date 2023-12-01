package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.Knowledge;
import org.dreams.weyun.mapper.KnowledgeMapper;
import org.dreams.weyun.dao.KnowledgeDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class KnowledgeDaoImpl extends ServiceImpl<KnowledgeMapper, Knowledge> implements KnowledgeDao {

}
