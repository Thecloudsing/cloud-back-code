package com.dreams.common.service.cache;

import cn.hutool.core.collection.CollectionUtil;
import com.dreams.common.utils.RedisUtils;
import org.springframework.data.util.Pair;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description: 获取缓存抽象类
 *
 * @param <I> 请求参数类型
 * @param <O> 返回参数类型
 */
public abstract class AbstractRedisCache<I, O> implements BatchCache<I, O> {
    /**
     * 缓存类对象
     */
    private final Class<O> outClass;

    /**
     * 构造方法
     * 获取泛型类型
     */
    public AbstractRedisCache() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType parameterizedType))
            throw new RuntimeException("获取泛型类型失败");
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (actualTypeArguments.length != 2)
            throw new RuntimeException("获取泛型类型失败");
        this.outClass = (Class<O>) actualTypeArguments[1];
    }

    /**
     * 抽象获取缓存key 由子类实现
     *
     * @param req 请求参数
     * @return 缓存key
     */
    protected abstract String getKey(I req);

    /**
     * 抽象获取缓存过期时间 由子类实现
     *
     * @return 缓存过期时间
     */
    protected abstract Long getExpireSeconds();

    /**
     * 抽象加载缓存 由子类实现
     *
     * @param req 请求参数 多个key
     * @return 批量缓存结果 key-value
     */
    protected abstract Map<I, O> load(List<I> req);

    /**
     * 获取单个缓存
     *
     * @param req 请求参数 一个key
     * @return 单个缓存结果 value
     */
    @Override
    public O get(I req) {
        return getBatch(Collections.singletonList(req)).get(req);
    }

    /**
     * 获取批量缓存
     *
     * @param req 请求参数 多个key
     * @return 批量缓存结果 key-value
     */
    @Override
    public Map<I, O> getBatch(List<I> req) {
        // Collections 是一个工具类，里面有很多静态方法，比如sort方法，sort方法可以对集合进行排序
        // Collectors 是一个工具类，里面有很多静态方法，比如toList方法，toList方法可以将流转换成List
        List<String> keys = req.stream().map(this::getKey).collect(Collectors.toList());
        List<O> valueList = RedisUtils.mget(keys, outClass);
        List<I> loadReqs = new ArrayList<>();
        for (int i = 0; i < valueList.size(); i++) {
            if (Objects.isNull(valueList.get(i))) {
                loadReqs.add(req.get(i));
            }
        }
        Map<I, O> load = new HashMap<>();
        //不足的重新加载进redis
        if (CollectionUtil.isNotEmpty(loadReqs)) {
            load = load(loadReqs);
            Map<String, O> loadMap = load.entrySet().stream()
                    .map(a -> Pair.of(getKey(a.getKey()), a.getValue()))
                    .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
            RedisUtils.mset(loadMap, getExpireSeconds());
        }

        //组装最后的结果
        Map<I, O> resultMap = new HashMap<>();
        for (int i = 0; i < req.size(); i++) {
            I I = req.get(i);
            O O = Optional.ofNullable(valueList.get(i))
                    .orElse(load.get(I));
            resultMap.put(I, O);
        }
        return resultMap;
    }
}
