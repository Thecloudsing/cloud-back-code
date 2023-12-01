package com.luoanforum.authorization.constant;


public interface RedisKeyInterface {

    /**
     * 根据 id 查询时放入Redis中的部分 key
     *
     * @param servicePartKey enum extents {@link ServicePartKey}
     * @param params 其他拼接参数
     * @return redisKey
     */
    String getRedisKey(ServicePartKey servicePartKey, String ...params);
}
