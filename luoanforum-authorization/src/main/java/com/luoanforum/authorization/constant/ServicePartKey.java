package com.luoanforum.authorization.constant;

public interface ServicePartKey {
    /**
     * 查询时放入Redis中的部分 key
     * @return 部分 key
     */
    String getKey();
}
