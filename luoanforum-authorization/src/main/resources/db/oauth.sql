# drop database if exists luoanforum_oauth;
# create database luoanforum_oauth;
use luoanforum_oauth;

-- ----------------------------
-- Table structure for oauth2_authorization
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_authorization`;
CREATE TABLE `oauth2_authorization`  (
                                         `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `registered_client_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `principal_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `authorization_grant_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                         `authorized_scopes` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `attributes` blob NULL,
                                         `state` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `authorization_code_value` blob NULL,
                                         `authorization_code_issued_at` timestamp(0) NULL DEFAULT NULL,
                                         `authorization_code_expires_at` timestamp(0) NULL DEFAULT NULL,
                                         `authorization_code_metadata` blob NULL,
                                         `access_token_value` blob NULL,
                                         `access_token_issued_at` timestamp(0) NULL DEFAULT NULL,
                                         `access_token_expires_at` timestamp(0) NULL DEFAULT NULL,
                                         `access_token_metadata` blob NULL,
                                         `access_token_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `access_token_scopes` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                         `oidc_id_token_value` blob NULL,
                                         `oidc_id_token_issued_at` timestamp(0) NULL DEFAULT NULL,
                                         `oidc_id_token_expires_at` timestamp(0) NULL DEFAULT NULL,
                                         `oidc_id_token_metadata` blob NULL,
                                         `refresh_token_value` blob NULL,
                                         `refresh_token_issued_at` timestamp(0) NULL DEFAULT NULL,
                                         `refresh_token_expires_at` timestamp(0) NULL DEFAULT NULL,
                                         `refresh_token_metadata` blob NULL,
                                         `user_code_value` blob NULL,
                                         `user_code_issued_at` timestamp(0) NULL DEFAULT NULL,
                                         `user_code_expires_at` timestamp(0) NULL DEFAULT NULL,
                                         `user_code_metadata` blob NULL,
                                         `device_code_value` blob NULL,
                                         `device_code_issued_at` timestamp(0) NULL DEFAULT NULL,
                                         `device_code_expires_at` timestamp(0) NULL DEFAULT NULL,
                                         `device_code_metadata` blob NULL,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth2_authorization
-- ----------------------------
INSERT INTO `oauth2_authorization` VALUES ('684c544f-a11c-475d-a6f8-7d2c891c0bd0', '7b692184-914f-4dcb-9142-f542a7e43fa4', 'user', 'authorization_code', 'openid,message.read', 0x7B2240636C617373223A226A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654D6170222C226A6176612E73656375726974792E5072696E636970616C223A7B2240636C617373223A226F72672E737072696E676672616D65776F726B2E73656375726974792E61757468656E7469636174696F6E2E557365726E616D6550617373776F726441757468656E7469636174696F6E546F6B656E222C22617574686F726974696573223A5B226A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C6552616E646F6D4163636573734C697374222C5B5D5D2C2264657461696C73223A7B2240636C617373223A226F72672E737072696E676672616D65776F726B2E73656375726974792E7765622E61757468656E7469636174696F6E2E57656241757468656E7469636174696F6E44657461696C73222C2272656D6F746541646472657373223A22303A303A303A303A303A303A303A31222C2273657373696F6E4964223A223141433645423144444332313637373745434232313233383934343034463941227D2C2261757468656E74696361746564223A747275652C227072696E636970616C223A7B2240636C617373223A22636F6D2E73792E617574687365727665722E646F6D61696E2E55736572496E666F222C226964223A322C22757365726E616D65223A2275736572222C2270617373776F7264223A222432612431302475772E5178624744316F35412E446C6230786A6A4C754D356E734934376962746950446F74707437424462666D4162573654475375222C226E616D65223A22E596BBE8AEADE6B5A9222C22656E61626C6564223A6E756C6C2C226163636F756E744E6F6E45787069726564223A6E756C6C2C2263726564656E7469616C734E6F6E45787069726564223A6E756C6C2C226163636F756E744E6F6E4C6F636B6564223A6E756C6C2C22617574686F726974696573223A5B226A6176612E7574696C2E41727261794C697374222C5B5D5D7D2C2263726564656E7469616C73223A6E756C6C7D2C226F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F72652E656E64706F696E742E4F4175746832417574686F72697A6174696F6E52657175657374223A7B2240636C617373223A226F72672E737072696E676672616D65776F726B2E73656375726974792E6F61757468322E636F72652E656E64706F696E742E4F4175746832417574686F72697A6174696F6E52657175657374222C22617574686F72697A6174696F6E557269223A22687474703A2F2F6C6F63616C686F73743A393030302F6F61757468322F617574686F72697A65222C22617574686F72697A6174696F6E4772616E7454797065223A7B2276616C7565223A22617574686F72697A6174696F6E5F636F6465227D2C22726573706F6E736554797065223A7B2276616C7565223A22636F6465227D2C22636C69656E744964223A226F6964632D636C69656E74222C227265646972656374557269223A22687474703A2F2F7777772E62616964752E636F6D222C2273636F706573223A5B226A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C65536574222C5B226F70656E6964222C226D6573736167652E72656164225D5D2C227374617465223A6E756C6C2C226164646974696F6E616C506172616D6574657273223A7B2240636C617373223A226A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654D6170222C22636F6E74696E7565223A22227D2C22617574686F72697A6174696F6E52657175657374557269223A22687474703A2F2F6C6F63616C686F73743A393030302F6F61757468322F617574686F72697A653F726573706F6E73655F747970653D636F646526636C69656E745F69643D6F6964632D636C69656E742673636F70653D6F70656E69642532306D6573736167652E726561642672656469726563745F7572693D687474703A2F2F7777772E62616964752E636F6D26636F6E74696E75653D222C2261747472696275746573223A7B2240636C617373223A226A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654D6170227D7D7D, NULL, 0x5F79764F55656652333835374D47634568767647556C70376B3934584474684566514F7A376F6B4A4A49397846723767673156484E37367170772D385262303965713749797867367A7748765953314B6C34614547463846487851542D64745576466A6C5A78357634546A39627A2D6F613753583636456E7775614A4A347750, '2023-07-06 08:17:08', '2023-07-06 08:22:08', 0x7B2240636C617373223A226A6176612E7574696C2E436F6C6C656374696F6E7324556E6D6F6469666961626C654D6170222C226D657461646174612E746F6B656E2E696E76616C696461746564223A66616C73657D, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for oauth2_authorization_consent
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_authorization_consent`;
CREATE TABLE `oauth2_authorization_consent`  (
                                                 `registered_client_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                                 `principal_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                                 `authorities` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                                 PRIMARY KEY (`registered_client_id`, `principal_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth2_authorization_consent
-- ----------------------------

-- ----------------------------
-- Table structure for oauth2_authorized_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_authorized_client`;
CREATE TABLE `oauth2_authorized_client`  (
                                             `client_registration_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `principal_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `access_token_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `access_token_value` blob NOT NULL,
                                             `access_token_issued_at` timestamp(0) NOT NULL,
                                             `access_token_expires_at` timestamp(0) NOT NULL,
                                             `access_token_scopes` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                             `refresh_token_value` blob NULL,
                                             `refresh_token_issued_at` timestamp(0) NULL DEFAULT NULL,
                                             `created_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                                             PRIMARY KEY (`client_registration_id`, `principal_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth2_authorized_client
-- ----------------------------

-- ----------------------------
-- Table structure for oauth2_registered_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_registered_client`;
CREATE TABLE `oauth2_registered_client`  (
                                             `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `client_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `client_id_issued_at` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
                                             `client_secret` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                             `client_secret_expires_at` timestamp(0) NULL DEFAULT NULL,
                                             `client_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `client_authentication_methods` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `authorization_grant_types` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `redirect_uris` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                             `post_logout_redirect_uris` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                             `scopes` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `client_settings` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             `token_settings` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth2_registered_client
-- ----------------------------
INSERT INTO `oauth2_registered_client` VALUES ('2c1a95f5-b4cd-49cd-884f-9e3a94c69e05', 'user-client', '2023-07-06 17:44:20', '$2a$10$HIatI0j2sqgPO5lSIuQH9.TxEGlrpBrI0ZD5MqwKOG6FMzwhlB7xa', NULL, '2c1a95f5-b4cd-49cd-884f-9e3a94c69e05', 'client_secret_post,client_secret_basic', 'refresh_token,client_credentials,authorization_code', 'http://www.baidu.com,http://localhost:9001/api/login/welcome,http://localhost:9001/login/oauth2/code/oidc-client', 'http://127.0.0.1:8080/', 'all,openid,profile,message.read,message.write', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');
INSERT INTO `oauth2_registered_client` VALUES ('885e9d2a-794e-4019-8843-5675045c573e', 'oidc-client', '2023-07-06 17:44:20', '$2a$10$k50p3tJgk3B7d9DrAxYIfO37pVSVO574qEWYH.nq6FTEprHA4Esie', NULL, '885e9d2a-794e-4019-8843-5675045c573e', 'client_secret_basic', 'refresh_token,client_credentials,authorization_code', 'http://www.baidu.com,http://localhost:9001/api/login/welcome,http://localhost:9001/login/oauth2/code/oidc-client', 'http://127.0.0.1:8080/', 'all,openid,profile,message.read,message.write', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":false}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",43200.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `id` bigint(20) NOT NULL COMMENT '主键',
                             `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
                             `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
                             `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
                             `status` tinyint(4) NOT NULL COMMENT '状态',
                             `create_time` datetime(0) NOT NULL COMMENT '创建时间',
                             `create_user` bigint(20) NOT NULL COMMENT '创建人',
                             `update_time` datetime(0) NOT NULL COMMENT '修改时间',
                             `update_user` bigint(20) NOT NULL COMMENT '修改人',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (2, 'user', '用户', '$2a$10$uw.QxbGD1o5A.Dlb0xjjLuM5nsI47ibtiPDotpt7BDbfmAbW6TGSu', 0, '2023-06-27 18:26:23', 1, '2023-06-27 18:26:26', 1);

SET FOREIGN_KEY_CHECKS = 1;