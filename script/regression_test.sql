/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : regression_test

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 17/06/2019 14:25:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test_dataset_types_info
-- ----------------------------
DROP TABLE IF EXISTS `test_dataset_types_info`;
CREATE TABLE `test_dataset_types_info`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '测试数据集类型id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '测试数据集类型名',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测试数据集类型描述',
  `default_service_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '测试数据集类型默认的调用服务地址',
  `created_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建该测试集的账号',
  `created_date` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `updated_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改（插入删除追加testcase等）日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test_dataset_upload_format_templates
-- ----------------------------
DROP TABLE IF EXISTS `test_dataset_upload_format_templates`;
CREATE TABLE `test_dataset_upload_format_templates`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dataset_type_id` int(11) NOT NULL COMMENT '外键，关联test_dataset_types_info，标示该模板单元属于哪一个回归测试集类型',
  `request_colum_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '该模板单元的标准输入列名，用于校验输入文件和生成用例数据',
  `request_colum_data_type` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '该模板单元的标准输入列内容类型，用于校验输入文件，可支持字符串、整数、浮点数',
  `response_colum_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `response_colum_data_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_date` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `updated_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dataset_type_id`(`dataset_type_id`) USING BTREE,
  CONSTRAINT `test_dataset_upload_format_templates_ibfk_1` FOREIGN KEY (`dataset_type_id`) REFERENCES `test_dataset_types_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test_datasets_info
-- ----------------------------
DROP TABLE IF EXISTS `test_datasets_info`;
CREATE TABLE `test_datasets_info`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '回归测试集id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回归测试集名称',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回归测试集描述',
  `dataset_type_id` int(10) NOT NULL COMMENT '外键，关联test_dataset_types_info',
  `test_case_count` int(10) NULL DEFAULT NULL COMMENT '该测试集中测试用例条数',
  `created_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建该测试集的账号',
  `created_date` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `updated_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改（插入删除追加testcase等）日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dataset_type_id`(`dataset_type_id`) USING BTREE,
  CONSTRAINT `test_datasets_info_ibfk_1` FOREIGN KEY (`dataset_type_id`) REFERENCES `test_dataset_types_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test_executions_info
-- ----------------------------
DROP TABLE IF EXISTS `test_executions_info`;
CREATE TABLE `test_executions_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回归测试执行任务名',
  `desc` varchar(3072) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回归测试执行任务描述',
  `test_dataset_id` int(11) NOT NULL COMMENT '外键，关联test_dataset_info，用于获取该回归测试执行所使用的回归测试集',
  `testcase_count` int(11) NOT NULL COMMENT '该测试集中测试用例条数',
  `finished_testcase_count` int(11) NOT NULL DEFAULT 0,
  `test_execution_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '执行中' COMMENT '用于标示该测试执行的总体状态，执行中或执行完成',
  `passed_testcase_count` int(11) NOT NULL DEFAULT 0,
  `correctness_score` double(6, 2) NULL DEFAULT 0.00 COMMENT '从算法准确率度量出发的该轮测试的总体准确度得分，由每个case的准确率得分加总除以总case条数折算成百分比得出，测试执行完成后计算',
  `adjusted_correctness_score` double(6, 2) NULL DEFAULT 0.00 COMMENT '考虑了每个testcase不同权重的该轮测试的总体准确度调整得分',
  `correctness_distribution` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'json字串，存储算法准确度每种不同准确度的testcase百分比分布',
  `service_address` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '该轮测试运行时的调用服务地址，通常为http url，测试执行时根据这个字段向相应server发送请求',
  `created_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建该测试执行的账号',
  `created_date` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `updated_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `test_dataset_id`(`test_dataset_id`) USING BTREE,
  CONSTRAINT `test_executions_info_ibfk_1` FOREIGN KEY (`test_dataset_id`) REFERENCES `test_datasets_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for testcase_execution_results
-- ----------------------------
DROP TABLE IF EXISTS `testcase_execution_results`;
CREATE TABLE `testcase_execution_results`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test_execution_id` int(11) NOT NULL COMMENT '外键，关联test_executions_info，标示该结果属于哪一轮测试执行',
  `testcase_id` int(11) NOT NULL COMMENT '外键，关联testcases，标示该结果是哪一个测试用例的执行结果',
  `execution_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '测试用例执行状态，成功/失败',
  `result_data` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'json形式的用例结果',
  `passed` tinyint(1) NOT NULL COMMENT '0/1结果表示该用例执行是否通过了严格标准的回归检验',
  `correctness` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字符串结果表示该用例执行结果的算法准确性衡量',
  `correctness_score` double(4, 2) NULL DEFAULT NULL COMMENT '浮点数结果表示该用例执行结果的算法准确性得分，原则上在0~1之间',
  `created_date` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `updated_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改（插入删除追加testcase等）日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `test_execution_id`(`test_execution_id`) USING BTREE,
  INDEX `testcase_id`(`testcase_id`) USING BTREE,
  CONSTRAINT `testcase_execution_results_ibfk_1` FOREIGN KEY (`test_execution_id`) REFERENCES `test_executions_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `testcase_execution_results_ibfk_2` FOREIGN KEY (`testcase_id`) REFERENCES `testcases` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for testcase_service_input_templates
-- ----------------------------
DROP TABLE IF EXISTS `testcase_service_input_templates`;
CREATE TABLE `testcase_service_input_templates`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dataset_type_id` int(11) NOT NULL COMMENT '外键，关联test_dataset_types_info，标示该模板属于哪一个回归测试集类型',
  `request_template` varchar(3072) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'json字符串，带placeholder，用于运行时结合具体testcase生成服务请求',
  `response_template` varchar(3072) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_date` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `updated_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dataset_type_id`(`dataset_type_id`) USING BTREE,
  CONSTRAINT `testcase_service_input_templates_ibfk_1` FOREIGN KEY (`dataset_type_id`) REFERENCES `test_dataset_types_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for testcases
-- ----------------------------
DROP TABLE IF EXISTS `testcases`;
CREATE TABLE `testcases`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dataset_id` int(11) NOT NULL COMMENT '外键，标示该测试用例属于哪一个测试集',
  `data` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'json形式的用例输入',
  `expect_data` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期望结果',
  `weight` double(11, 4) NULL DEFAULT 1.0000 COMMENT '该用例权重，默认为1',
  `created_date` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `updated_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改（插入删除追加testcase等）日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dataset_id`(`dataset_id`) USING BTREE,
  CONSTRAINT `testcases_ibfk_1` FOREIGN KEY (`dataset_id`) REFERENCES `test_datasets_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
