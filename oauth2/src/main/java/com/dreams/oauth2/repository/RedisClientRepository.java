package com.dreams.oauth2.repository;

import com.dreams.oauth2.domain.entity.security.RedisRegisteredClient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Description: 基于Spring Data Redis的客户端repository
 *
 * @author luoan
 * @since 2023/10/25
 */
public interface RedisClientRepository extends CrudRepository<RedisRegisteredClient, String> {
    /**
     * 根据客户端Id查询客户端信息
     *
     * @param clientId 客户端id
     * @return 客户端信息
     */
    Optional<RedisRegisteredClient> findByClientId(String clientId);
}
