package com.dreams.common.contant;

/**
 * Description:
 *
 * @author luoan
 * @since 2023/10/19
 */
public class RedisKey {
    public static String USER_TOKEN_PREFIX = "user-token:";
    public static String USER_INFO_PREFIX = "user-info:";
    public static String initialize(String prefix) {
        return ProjectPrefix.redis_prefix + prefix;
    }
    public static String key(String prefix, String key) {
        return initialize(prefix) + key;
    }
    public static String key(String prefix, Long key) {
        return initialize(prefix) + key;
    }
}
