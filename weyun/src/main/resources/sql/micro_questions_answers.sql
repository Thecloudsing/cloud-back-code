/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : micro_questions_answers

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 27/11/2023 22:21:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_answer
-- ----------------------------
DROP TABLE IF EXISTS `base_answer`;
CREATE TABLE `base_answer`  (
  `answer_id` int NOT NULL AUTO_INCREMENT COMMENT '回答ID',
  `content` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回答内容',
  `amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '悬赏金额',
  `view_number` int NULL DEFAULT NULL COMMENT '查看数',
  `comment_number` int NULL DEFAULT NULL COMMENT '评论数',
  `collect_number` int NULL DEFAULT NULL COMMENT '收藏数',
  `status_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态类型【已答/未答】',
  `question_id` int NULL DEFAULT NULL COMMENT '提问ID',
  `grade_id` int NULL DEFAULT NULL COMMENT '年级ID',
  `class_id` int NULL DEFAULT NULL COMMENT '班级ID',
  `student_id` int NULL DEFAULT NULL COMMENT '学生ID',
  `teacher_id` int NULL DEFAULT NULL COMMENT '教师ID',
  `subject_id` int NULL DEFAULT NULL COMMENT '学科ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`answer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_answer
-- ----------------------------

-- ----------------------------
-- Table structure for base_class
-- ----------------------------
DROP TABLE IF EXISTS `base_class`;
CREATE TABLE `base_class`  (
  `class_id` int NOT NULL AUTO_INCREMENT COMMENT '班级ID',
  `class_encoding` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '班级编码',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '班级名称',
  `grade_id` int NULL DEFAULT NULL COMMENT '年级ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_class
-- ----------------------------

-- ----------------------------
-- Table structure for base_grade
-- ----------------------------
DROP TABLE IF EXISTS `base_grade`;
CREATE TABLE `base_grade`  (
  `grade_id` int NOT NULL AUTO_INCREMENT COMMENT '年级ID',
  `grade_encoding` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年级编码',
  `grade_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年级名称',
  `school_id` int NULL DEFAULT NULL COMMENT '学校ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`grade_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_grade
-- ----------------------------

-- ----------------------------
-- Table structure for base_knowledge
-- ----------------------------
DROP TABLE IF EXISTS `base_knowledge`;
CREATE TABLE `base_knowledge`  (
  `knowledge_id` int NOT NULL AUTO_INCREMENT COMMENT '知识点ID',
  `knowledge_encoding` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '知识点编码',
  `knowledge_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '知识点名称',
  `knowledge_father_id` int NULL DEFAULT NULL COMMENT '知识点父ID',
  `subject_id` int NULL DEFAULT NULL COMMENT '学科ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`knowledge_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_knowledge
-- ----------------------------
INSERT INTO `base_knowledge` VALUES (1, '0001', '函数', 0, 2, '2023-11-21 11:55:25', 0);
INSERT INTO `base_knowledge` VALUES (2, '002', '三角函数', 1, 2, '2023-11-21 11:55:59', 0);

-- ----------------------------
-- Table structure for base_menu
-- ----------------------------
DROP TABLE IF EXISTS `base_menu`;
CREATE TABLE `base_menu`  (
  `permissions_id` int NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `permissions_encoding` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `permissions_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `permissions_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限图标',
  `permissions_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转链接',
  `permissions_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限文件地址',
  `permissions_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限类型【父菜单、子菜单、按钮】',
  `permissions_sort` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限排序',
  `permissions_father_id` int NULL DEFAULT NULL COMMENT '权限父ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`permissions_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_menu
-- ----------------------------

-- ----------------------------
-- Table structure for base_profession
-- ----------------------------
DROP TABLE IF EXISTS `base_profession`;
CREATE TABLE `base_profession`  (
  `profession_id` int NOT NULL AUTO_INCREMENT COMMENT '专业ID',
  `profession_encoding` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业编码',
  `profession_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业名称',
  `school_id` int NULL DEFAULT NULL COMMENT '学校ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`profession_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_profession
-- ----------------------------

-- ----------------------------
-- Table structure for base_question
-- ----------------------------
DROP TABLE IF EXISTS `base_question`;
CREATE TABLE `base_question`  (
  `question_id` int NOT NULL AUTO_INCREMENT COMMENT '提问ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提问内容',
  `label` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标注',
  `grade_id` int NULL DEFAULT NULL COMMENT '年级ID',
  `class_id` int NULL DEFAULT NULL COMMENT '班级ID',
  `student_id` int NULL DEFAULT NULL COMMENT '学生ID',
  `teacher_id` int NULL DEFAULT NULL COMMENT '教师ID',
  `subject_id` int NULL DEFAULT NULL COMMENT '学科ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_question
-- ----------------------------

-- ----------------------------
-- Table structure for base_region
-- ----------------------------
DROP TABLE IF EXISTS `base_region`;
CREATE TABLE `base_region`  (
  `region_id` int NOT NULL AUTO_INCREMENT COMMENT '区域ID',
  `region_encoding` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区域编码',
  `region_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区域名称',
  `region_father_id` int NULL DEFAULT NULL COMMENT '区域父ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`region_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_region
-- ----------------------------
INSERT INTO `base_region` VALUES (1, '1012', '广东省', 0, '2023-11-21 10:58:46', 0);
INSERT INTO `base_region` VALUES (2, '1013', '广州市', 1, '2023-11-21 10:59:36', 0);
INSERT INTO `base_region` VALUES (3, '10014', '佛山市', 1, '2023-11-21 11:00:01', 0);
INSERT INTO `base_region` VALUES (4, '10086', '天河区', 2, '2023-11-21 11:00:36', 0);

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role`  (
  `role_id` int NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_encoding` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_role
-- ----------------------------

-- ----------------------------
-- Table structure for base_role_permissions
-- ----------------------------
DROP TABLE IF EXISTS `base_role_permissions`;
CREATE TABLE `base_role_permissions`  (
  `role_permissions_id` int NOT NULL AUTO_INCREMENT COMMENT '角色权限ID',
  `role_id` int NULL DEFAULT NULL COMMENT '角色ID',
  `permissions_id` int NULL DEFAULT NULL COMMENT '权限ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`role_permissions_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_role_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for base_school
-- ----------------------------
DROP TABLE IF EXISTS `base_school`;
CREATE TABLE `base_school`  (
  `school_id` int NOT NULL AUTO_INCREMENT COMMENT '学校ID',
  `school_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学校名称',
  `region_id` int NULL DEFAULT NULL COMMENT '区域ID',
  `school_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学校地址',
  `school_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学校电话',
  `school_mailbox` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学校邮箱',
  `type_id` int NULL DEFAULT NULL COMMENT '学校类型ID',
  `school_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学校性质【0：公办 1：民办】',
  `school_brief` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学校简介',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`school_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_school
-- ----------------------------

-- ----------------------------
-- Table structure for base_school_type
-- ----------------------------
DROP TABLE IF EXISTS `base_school_type`;
CREATE TABLE `base_school_type`  (
  `type_id` int NOT NULL AUTO_INCREMENT COMMENT '学校类型ID',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学校类型名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_school_type
-- ----------------------------
INSERT INTO `base_school_type` VALUES (1, '小学', '2023-11-21 11:00:59', 0);
INSERT INTO `base_school_type` VALUES (2, '中学', '2023-11-21 11:01:01', 0);

-- ----------------------------
-- Table structure for base_student
-- ----------------------------
DROP TABLE IF EXISTS `base_student`;
CREATE TABLE `base_student`  (
  `student_id` int NOT NULL AUTO_INCREMENT COMMENT '学生ID',
  `student_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生名称',
  `student_sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生性别',
  `student_birthdate` datetime NULL DEFAULT NULL COMMENT '出生日期',
  `student_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `standing_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `subject_id` int NULL DEFAULT NULL COMMENT '学科ID',
  `student_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '政治面貌【团员、群众、党\n员】',
  `class_id` int NULL DEFAULT NULL COMMENT '班级ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_student
-- ----------------------------

-- ----------------------------
-- Table structure for base_subject
-- ----------------------------
DROP TABLE IF EXISTS `base_subject`;
CREATE TABLE `base_subject`  (
  `subject_id` int NOT NULL AUTO_INCREMENT COMMENT '学科ID',
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学科名称',
  `school_id` int NULL DEFAULT NULL COMMENT '学校ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`subject_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_subject
-- ----------------------------
INSERT INTO `base_subject` VALUES (1, '语文', NULL, '2023-11-21 11:20:30', 0);
INSERT INTO `base_subject` VALUES (2, '数学', NULL, '2023-11-21 11:20:54', 0);

-- ----------------------------
-- Table structure for base_teacher
-- ----------------------------
DROP TABLE IF EXISTS `base_teacher`;
CREATE TABLE `base_teacher`  (
  `teacher_id` int NOT NULL AUTO_INCREMENT COMMENT '教师ID',
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '教师名称',
  `teacher_sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '教师性别',
  `teacher_birthdate` datetime NULL DEFAULT NULL COMMENT '出生日期',
  `teacher_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `standing_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `teacher_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '政治面貌【1.团员、2.群众、3.党员】',
  `school_id` int NULL DEFAULT NULL COMMENT '学校ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_teacher
-- ----------------------------
INSERT INTO `base_teacher` VALUES (1, '张三', '男', '2023-10-05 11:33:13', '124564567', '7456321', '3', NULL, '2023-11-21 11:34:38', 0);
INSERT INTO `base_teacher` VALUES (2, '李四', '男', '2023-07-01 11:35:06', '12345678', '87456321', '2', NULL, '2023-11-21 11:35:20', 0);
INSERT INTO `base_teacher` VALUES (3, '王薇', '女', '2023-02-01 11:35:49', '123456', '654321', '1', NULL, '2023-11-21 11:36:22', 0);

-- ----------------------------
-- Table structure for base_teacher_subject
-- ----------------------------
DROP TABLE IF EXISTS `base_teacher_subject`;
CREATE TABLE `base_teacher_subject`  (
  `teacher_subject_id` int NOT NULL AUTO_INCREMENT COMMENT '教师学科ID',
  `teacher_id` int NULL DEFAULT NULL COMMENT '教师ID',
  `subject_id` int NULL DEFAULT NULL COMMENT '学科ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`teacher_subject_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_teacher_subject
-- ----------------------------
INSERT INTO `base_teacher_subject` VALUES (1, 1, 1, '2023-11-21 11:36:39', 0);
INSERT INTO `base_teacher_subject` VALUES (2, 1, 2, '2023-11-21 11:36:53', 0);

-- ----------------------------
-- Table structure for base_user
-- ----------------------------
DROP TABLE IF EXISTS `base_user`;
CREATE TABLE `base_user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `role_id` int NULL DEFAULT NULL COMMENT '角色ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_user
-- ----------------------------

-- ----------------------------
-- Table structure for base_user_role
-- ----------------------------
DROP TABLE IF EXISTS `base_user_role`;
CREATE TABLE `base_user_role`  (
  `user_role_id` int NOT NULL AUTO_INCREMENT COMMENT '用户角色ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` int NULL DEFAULT NULL COMMENT '角色ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
