import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.luoanforum.authorization.client.RedisRegisteredClientRepository;
import com.luoanforum.authorization.configuration.RedisSpringAuthorizationServerConfiguration;
import com.luoanforum.authorization.properties.SpringAuthorizationServerRedisProperties;
import com.luoanforum.authorization.utils.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import javax.sql.DataSource;

import static org.springframework.security.oauth2.server.authorization.oidc.OidcClientMetadataClaimNames.CLIENT_ID;

@Slf4j
@SpringBootTest(classes = { RedisAutoConfiguration.class, RedisSpringAuthorizationServerConfiguration.class,
        SpringAuthorizationServerRedisProperties.class, RedisRegisteredClientRepository.class })

public class RegisteredClientTest {
//
//
//    @Autowired
//    private RedisRegisteredClientRepository redisRegisteredClientRepository;
//
//    /**
//     * 基于 JDBC 的 {@link RegisteredClientRepository}，用于直接操作数据库中的客户信息
//     */
//    private JdbcRegisteredClientRepository jdbcRegisteredClientRepository;
//
//    @Autowired
//    public void setJdbcRegisteredClientRepository(DataSource dataSource) {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        this.jdbcRegisteredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
//    }
//
//    @Test
//    void save() throws JsonProcessingException {
//        String id = "123456";
//        RegisteredClient.Builder registeredClientBuilder = RegisteredClient.withId(id);
//        // 客户ID
//        registeredClientBuilder.clientId("admin");
//        // 客户凭证
//        registeredClientBuilder.clientSecret("123456");
//        // 客户凭证验证方式
//        registeredClientBuilder.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
//        registeredClientBuilder.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST);
//        // 授权类型
//        registeredClientBuilder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
//        registeredClientBuilder.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN);
//        registeredClientBuilder.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS);
//        // 授权成功后重定向地址
//        registeredClientBuilder.redirectUri("http://127.0.0.1:1401/code");
//        // 授权范围
//        registeredClientBuilder.scope("snsapi_base");
//
//        ClientSettings.Builder clientSettingsBuilder = ClientSettings.builder();
//        clientSettingsBuilder.requireAuthorizationConsent(true);
//        ClientSettings clientSettings = clientSettingsBuilder.build();
//
//        RegisteredClient registeredClient = registeredClientBuilder.clientSettings(clientSettings).build();
//
//        redisRegisteredClientRepository.save(registeredClient);
//
//        ObjectMapper objectMapper = ObjectMapperUtils.redis();
//        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
//
//        RegisteredClient registeredClientByDatabase = jdbcRegisteredClientRepository.findById(id);
//        log.info("直接查询数据库中的保存的结果：{}", objectWriter.writeValueAsString(registeredClientByDatabase));
//    }
//
//    @Test
//    void findById() throws JsonProcessingException {
//        ObjectMapper objectMapper = ObjectMapperUtils.redis();
//        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
//
//        RegisteredClient clientRepositoryByRedis = redisRegisteredClientRepository.findById(ID);
//        log.info("根据 id：{} 查询Redis中的客户（不存在时从数据库中查询）：{}", ID, objectWriter.writeValueAsString(clientRepositoryByRedis));
//        RegisteredClient byId = redisRegisteredClientRepository.findById(ID);
//        log.info("\n{}", objectWriter.writeValueAsString(byId));
//    }
//
//    @Test
//    void findByClientId() throws JsonProcessingException {
//        ObjectMapper objectMapper = ObjectMapperUtils.redis();
//        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
//
//        RegisteredClient registeredClient = jdbcRegisteredClientRepository.findByClientId(CLIENT_ID);
//        log.info("根据 clientId：{} 直接查询数据库中的客户：{}", CLIENT_ID, objectWriter.writeValueAsString(registeredClient));
//        RegisteredClient byId = jdbcRegisteredClientRepository.findByClientId(CLIENT_ID);
//        log.info("\n{}", objectWriter.writeValueAsString(byId));
//    }
}