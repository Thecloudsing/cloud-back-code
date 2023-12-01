package com.luoanforum.authorization.configuration;

import com.luoanforum.authorization.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;


/**
 * {@link OAuth2AuthorizationService} 配置
 */
@Configuration
public class RedisSpringAuthorizationServerConfiguration {

	public static final String REDIS_TEMPLATE_REGISTERED_CLIENT_BEAN_NAME = "redisTemplateRegisteredClient";

	public static final String REDIS_TEMPLATE_OAUTH2_AUTHORIZATION_BEAN_NAME = "redisTemplateOAuth2Authorization";

	public static final String REDIS_TEMPLATE_OAUTH2_AUTHORIZATION_CONSENT_BEAN_NAME = "redisTemplateOAuth2AuthorizationConsent";

	/**
	 * 注意：如果要使用注解 {@link Autowired} 管理 {@link RedisTemplate}， 则需要将 {@link RedisTemplate} 的
	 * {@link Bean} 缺省泛型
	 * @param redisConnectionFactory Redis 连接工厂
	 * @return 返回 Redis 模板
	 */
	@Bean(REDIS_TEMPLATE_REGISTERED_CLIENT_BEAN_NAME)
	@ConditionalOnMissingBean(name = REDIS_TEMPLATE_REGISTERED_CLIENT_BEAN_NAME)
	public RedisTemplate<String, RegisteredClient> redisTemplateRegisteredClient(
			RedisConnectionFactory redisConnectionFactory) {

		// Helper类简化了 Redis 数据访问代码
		RedisTemplate<String, RegisteredClient> template = new RedisTemplate<>();

		// 设置连接工厂。
		template.setConnectionFactory(redisConnectionFactory);

		// 可以使用读写JSON
		Jackson2JsonRedisSerializer<RegisteredClient> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				ObjectMapperUtils.redis(),
				RegisteredClient.class);

//		jackson2JsonRedisSerializer.setObjectMapper(ObjectMapperUtils.redis());

		// Redis 字符串：键、值序列化
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(jackson2JsonRedisSerializer);

		// Redis Hash：键、值序列化
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(jackson2JsonRedisSerializer);

		template.afterPropertiesSet();

		return template;
	}

	/**
	 * 注意：如果要使用注解 {@link Autowired} 管理 {@link RedisTemplate}， 则需要将 {@link RedisTemplate} 的
	 * {@link Bean} 缺省泛型
	 * @param redisConnectionFactory Redis 连接工厂
	 * @return 返回 Redis 模板
	 */
	@Bean(REDIS_TEMPLATE_OAUTH2_AUTHORIZATION_BEAN_NAME)
	@ConditionalOnMissingBean(name = REDIS_TEMPLATE_OAUTH2_AUTHORIZATION_BEAN_NAME)
	public RedisTemplate<String, OAuth2Authorization> redisTemplateOAuth2Authorization(
			RedisConnectionFactory redisConnectionFactory) {

		// Helper类简化了 Redis 数据访问代码
		RedisTemplate<String, OAuth2Authorization> template = new RedisTemplate<>();

		// 设置连接工厂。
		template.setConnectionFactory(redisConnectionFactory);

		// 可以使用读写JSON
		Jackson2JsonRedisSerializer<OAuth2Authorization> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				ObjectMapperUtils.redis(),
				OAuth2Authorization.class);

//		 已被弃用的方法
//		jackson2JsonRedisSerializer.setObjectMapper(ObjectMapperUtils.redis());


		// Redis 字符串：键、值序列化
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(jackson2JsonRedisSerializer);

		// Redis Hash：键、值序列化
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(jackson2JsonRedisSerializer);

		template.afterPropertiesSet();

		return template;
	}

	/**
	 * 注意：如果要使用注解 {@link Autowired} 管理 {@link RedisTemplate}， 则需要将 {@link RedisTemplate} 的
	 * {@link Bean} 缺省泛型
	 * @param redisConnectionFactory Redis 连接工厂
	 * @return 返回 Redis 模板
	 */
	@Bean(REDIS_TEMPLATE_OAUTH2_AUTHORIZATION_CONSENT_BEAN_NAME)
	@ConditionalOnMissingBean(name = REDIS_TEMPLATE_OAUTH2_AUTHORIZATION_CONSENT_BEAN_NAME)
	public RedisTemplate<String, OAuth2AuthorizationConsent> redisTemplateOAuth2AuthorizationConsent(
			RedisConnectionFactory redisConnectionFactory) {

		// Helper类简化了 Redis 数据访问代码
		RedisTemplate<String, OAuth2AuthorizationConsent> template = new RedisTemplate<>();

		// 设置连接工厂。
		template.setConnectionFactory(redisConnectionFactory);

		// 可以使用读写JSON
		Jackson2JsonRedisSerializer<OAuth2AuthorizationConsent> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				ObjectMapperUtils.redis(),
				OAuth2AuthorizationConsent.class);

//		jackson2JsonRedisSerializer.setObjectMapper(ObjectMapperUtils.redis());

		// Redis 字符串：键、值序列化
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(jackson2JsonRedisSerializer);

		// Redis Hash：键、值序列化
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(jackson2JsonRedisSerializer);

		template.afterPropertiesSet();

		return template;
	}

}
