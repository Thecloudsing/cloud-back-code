package com.luoanforum.authorization.client;

import com.luoanforum.authorization.configuration.RedisSpringAuthorizationServerConfiguration;
import com.luoanforum.authorization.constant.RedisKey;
import com.luoanforum.authorization.constant.RegisteredClientRedisKey;
import com.luoanforum.authorization.constant.RegisteredClientRedisKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisRegisteredClientRepository implements RegisteredClientRepository {

    @Autowired
    @Qualifier(RedisSpringAuthorizationServerConfiguration.REDIS_TEMPLATE_REGISTERED_CLIENT_BEAN_NAME)
    private RedisTemplate<String, RegisteredClient> redisTemplate;

    private JdbcRegisteredClientRepository jdbcRegisteredClientRepository;

    @Autowired
    private RedisKey redisKey;

    @Autowired
    public void setJdbcRegisteredClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcRegisteredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
    }

    /**
     * 保存 RegisteredClient
     * @param registeredClient {@link RegisteredClient}
     */
    @Override
    public void save(RegisteredClient registeredClient) {
        Assert.notNull(registeredClient, "registeredClient cannot be null");
        setRedisRegisteredClient(registeredClient);
        jdbcRegisteredClientRepository.save(registeredClient);
    }

    /**
     * 根据 id获取 RegisteredClient
     * @param id {@link RegisteredClient#getId()}
     * @return {@link RegisteredClient}
     */
    @Override
    public RegisteredClient findById(String id) {
        Assert.hasText(id, "id cannot be empty");
        return getRegisteredClient(RegisteredClientRedisKey.REGISTERED_CLIENT_ID, id);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Assert.hasText(clientId, "clientId cannot be empty");
        return getRegisteredClient(RegisteredClientRedisKey.REGISTERED_CLIENT_CLIENT_ID, clientId);
    }

    /**
     * 持久化 RegisteredClient
     *
     * @param registeredClient {@link RegisteredClient}
     */
    public void setRedisRegisteredClient(@NonNull RegisteredClient registeredClient) {
        String idKey = redisKey.getRedisKey(RegisteredClientRedisKey.REGISTERED_CLIENT_ID, registeredClient.getId());
        String clientIdKey = redisKey.getRedisKey(RegisteredClientRedisKey.REGISTERED_CLIENT_CLIENT_ID, registeredClient.getClientId());
        long timeout = redisKey.getRedisProperties().getRegisteredClientTimeout();
        redisTemplate.opsForValue().set(idKey, registeredClient, timeout, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(clientIdKey, registeredClient, timeout, TimeUnit.SECONDS);
    }

    /**
     * get redis RegisteredClient
     *
     * @param registerClientKey {@link com.luoanforum.authorization.constant.RegisteredClientRedisKey}
     * @param registeredUnique {@link RegisteredClient#getId()} | {@link RegisteredClient#getClientId()}
     * @return {@link RegisteredClient}
     */
    public RegisteredClient getRegisteredClient(RegisteredClientRedisKey registerClientKey, String registeredUnique) {
        String key = redisKey.getRedisKey(registerClientKey, registeredUnique);
        RegisteredClient redisRegisteredClient = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(redisRegisteredClient)
                .map((rrc) -> {
                    log.debug("Redis RegisteredClient findById Found ==> {}", rrc);
                    return rrc;
                })
                .orElseGet(() -> Optional.ofNullable(
                        registerClientKey == RegisteredClientRedisKey.REGISTERED_CLIENT_ID ?
                                jdbcRegisteredClientRepository.findById(registeredUnique) :
                        jdbcRegisteredClientRepository.findByClientId(registeredUnique)
                                )
                        .map(drc -> {
                            setRedisRegisteredClient(drc);
                            log.debug("Redis RegisteredClient findById Not Found, Try To Find In Database ==> {}", drc);
                            return drc;
                        })
                        .orElseThrow()
                );


//        if (redisRegisteredClient == null) {
//            RegisteredClient registeredClientData = jdbcRegisteredClientRepository.findById(registeredUnique);
//            if (registeredClientData != null) {
//                setRedisRegisteredClient(registeredClientData);
//            }
//            log.debug("direct form [database] RegisteredClient findById: {}", registeredClientData);
//            return registeredClientData;
//        } else {
//            log.debug("direct form [   redis] RegisteredClient findById: {}", redisRegisteredClient);
//            return redisRegisteredClient;
//        }
    }
}
