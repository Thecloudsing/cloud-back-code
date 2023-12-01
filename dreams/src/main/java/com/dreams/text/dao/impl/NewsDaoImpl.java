package com.dreams.text.dao.impl;

import com.dreams.text.domain.entity.News;
import com.dreams.text.mapper.NewsMapper;
import com.dreams.text.dao.NewsDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

/**
 * Description: 新闻表 服务实现类
 *
 * @author luoan
 * @since 2023/10/19
 */
@Repository
public class NewsDaoImpl extends ServiceImpl<NewsMapper, News> implements NewsDao {

}
