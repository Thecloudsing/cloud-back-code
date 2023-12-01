package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.Answer;
import org.dreams.weyun.mapper.AnswerMapper;
import org.dreams.weyun.dao.AnswerDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class AnswerDaoImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerDao {

}
