package com.luoanforum.common.service.cache;

import java.util.List;
import java.util.Map;

/**
 * Description: 获取缓存接口
 * @param <I> 请求参数类型
 * @param <O> 返回参数类型
 */
public interface ObtainCache<I,O> {
    /**
     * 获取单个缓存
     * @param req 请求参数 一个key
     * @return 缓存结果 value
     */
    O get(I req);

    /**
     * 获取批量缓存
     * @param req 请求参数 多个key
     * @return 批量缓存结果 key-value
     */
    Map<I,O> getBatch(List<I> req);
}
