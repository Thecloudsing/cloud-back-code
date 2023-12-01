package org.dreams.weyun.dao.impl;

import org.dreams.weyun.domain.entity.Question;
import org.dreams.weyun.mapper.QuestionMapper;
import org.dreams.weyun.dao.QuestionDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description:  服务实现类
 *
 * @author luoan
 * @since 2023/11/29
 */
@Repository
public class QuestionDaoImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionDao {

}
