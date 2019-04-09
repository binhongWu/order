/*
Navicat Oracle Data Transfer
Oracle Client Version : 10.2.0.5.0

Source Server         : 224-大数据-拓展宝
Source Server Version : 110200
Source Host           : 192.168.1.224:1521
Source Schema         : TZB

Target Server Type    : ORACLE
Target Server Version : 110200
File Encoding         : 65001

Date: 2018-12-28 14:14:56
*/


-- ----------------------------
-- Table structure for CORE_AUDIT
-- ----------------------------
DROP TABLE "TZB"."CORE_AUDIT";
CREATE TABLE "TZB"."CORE_AUDIT" (
"ID" NUMBER(11) NOT NULL ,
"FUNCTION_CODE" NVARCHAR2(45) NULL ,
"FUNCTION_NAME" NVARCHAR2(45) NULL ,
"USER_ID" NUMBER(11) NULL ,
"USER_NAME" NVARCHAR2(45) NULL ,
"IP" NVARCHAR2(45) NULL ,
"CREATE_TIME" DATE NULL ,
"SUCCESS" NUMBER(4) NULL ,
"MESSAGE" NCLOB NULL ,
"ORG_ID" NVARCHAR2(45) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "TZB"."CORE_AUDIT" IS '管理平台数据';

-- ----------------------------
-- Records of CORE_AUDIT
-- ----------------------------
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('1', 'org.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 19:58:50', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('2', 'org.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 19:58:51', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('3', 'role.edit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 20:00:10', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('4', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 20:00:11', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('5', 'user.add', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 20:00:39', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('6', 'user.edit', '用户编辑', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 20:10:15', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('7', 'user.query', '用户列表', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 20:10:15', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('8', 'role.edit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 20:10:16', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('9', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 20:10:17', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('10', 'user.edit', '用户编辑', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 20:14:46', 'YYYY-MM-DD HH24:MI:SS'), '0', 'java.sql.SQLException: Error on delete of ''C:\Users\ADMINI~1\AppData\Local\Temp\#sql978_2c3_6.MYI'' (Errcode: 13 - Permission denied)', null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('11', 'user.edit', '用户编辑', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 20:15:12', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('12', 'user.query', '用户列表', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 20:15:13', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('13', 'role.edit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 20:15:14', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('14', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 20:15:14', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('15', 'audit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-06 20:16:23', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('16', 'role.edit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:42:58', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('17', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:42:58', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('18', 'role.edit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:53:11', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('19', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:53:11', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('20', 'user.add', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:53:13', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('21', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:53:28', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('22', 'role.add', '角色添加', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:53:29', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('23', 'role.edit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:53:43', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('24', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:53:43', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('25', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:53:45', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('26', 'role.edit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:56:03', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('27', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:56:03', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('28', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:56:06', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('29', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:56:07', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('30', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:56:09', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('31', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 09:56:10', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('32', 'role.edit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 10:02:00', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('33', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 10:02:00', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('34', 'role.edit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 10:02:02', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('35', 'role.edit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 10:05:40', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('36', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 10:05:40', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('37', 'role.edit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 10:05:42', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('38', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 10:06:02', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('39', 'role.edit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 10:07:45', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('40', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 10:07:45', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('41', 'role.edit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 10:07:47', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('42', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 10:08:03', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('43', 'role.update', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 10:08:03', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('44', 'role.edit', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 10:08:16', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);
INSERT INTO "TZB"."CORE_AUDIT" VALUES ('45', 'role.query', '未定义', '1', '超级管理员', '172.16.49.65', TO_DATE('2018-02-07 10:08:16', 'YYYY-MM-DD HH24:MI:SS'), '1', empty_clob(), null);

-- ----------------------------
-- Table structure for CORE_DICT
-- ----------------------------
DROP TABLE "TZB"."CORE_DICT";
CREATE TABLE "TZB"."CORE_DICT" (
"ID" NUMBER(11) NOT NULL ,
"VALUE" NVARCHAR2(16) NOT NULL ,
"NAME" NVARCHAR2(128) NOT NULL ,
"TYPE" NVARCHAR2(64) NOT NULL ,
"TYPE_NAME" NVARCHAR2(64) NOT NULL ,
"SORT" NUMBER(11) NULL ,
"PARENT" NUMBER(11) NULL ,
"DEL_FLAG" NUMBER(11) NULL ,
"REMARK" NVARCHAR2(255) NULL ,
"CREATE_TIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "TZB"."CORE_DICT" IS '管理平台数据';
COMMENT ON COLUMN "TZB"."CORE_DICT"."NAME" IS '名称';
COMMENT ON COLUMN "TZB"."CORE_DICT"."TYPE" IS '字典编码';
COMMENT ON COLUMN "TZB"."CORE_DICT"."TYPE_NAME" IS '类型描述';
COMMENT ON COLUMN "TZB"."CORE_DICT"."SORT" IS '排序';
COMMENT ON COLUMN "TZB"."CORE_DICT"."PARENT" IS '父id';
COMMENT ON COLUMN "TZB"."CORE_DICT"."DEL_FLAG" IS '删除标记';
COMMENT ON COLUMN "TZB"."CORE_DICT"."REMARK" IS '备注';
COMMENT ON COLUMN "TZB"."CORE_DICT"."CREATE_TIME" IS '创建时间';

-- ----------------------------
-- Records of CORE_DICT
-- ----------------------------
INSERT INTO "TZB"."CORE_DICT" VALUES ('1', 'DA0', '查看自己', 'data_access_type', '数据权限', '1', null, '0', '11111111111111111123', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('2', 'DA1', '查看本公司', 'data_access_type', '数据权限', '3', null, '0', 'hello,go', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('3', 'DA2', '查看同机构', 'data_access_type', '数据权限', '3', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('4', 'DA3', '查看本部门', 'data_access_type', '数据权限', '4', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('5', 'DA4', '查看集团', 'data_access_type', '数据权限', '5', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('6', 'DA5', '查看母公司', 'data_access_type', '数据权限', '6', null, '0', null, TO_DATE('2017-10-14 11:45:02', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('7', 'FN0', '普通功能', 'function_type', '功能点类型', '2', null, '0', null, TO_DATE('2017-10-23 10:18:03', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('8', 'FN1', '含数据权限', 'function_type', '功能点类型', '1', null, '0', null, TO_DATE('2017-10-23 10:20:05', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('9', 'JT_01', '管理岗位', 'job_type', '岗位类型', '1', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10', 'JT_02', '技术岗位', 'job_type', '岗位类型', '2', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('11', 'JT_S_01', '董事会', 'job_sub_managment_type', '管理岗位子类型', '1', '9', '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('12', 'JT_S_02', '秘书', 'job_sub_managment_type', '管理岗位子类型', '2', '9', '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('13', 'JT_S_03', '技术经理', 'job_dev_sub_type', '技术岗位子类型', '1', '10', '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('14', 'JT_S_04', '程序员', 'job_dev_sub_type', '技术岗位子类型', '2', '10', '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('15', 'MENU_M', '菜单', 'menu_type', '菜单类型', '3', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('16', 'MENU_N', '导航', 'menu_type', '菜单类型', '2', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('17', 'MENU_S', '系统', 'menu_type', '菜单类型', '1', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('18', 'ORGT0', '集团总部', 'org_type', '机构类型', '1', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('19', 'ORGT1', '分公司', 'org_type', '机构类型', '2', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('20', 'ORGT2', '部门', 'org_type', '机构类型', '3', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('21', 'ORGT3', '小组', 'org_type', '机构类型', '4', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('22', 'R0', '操作角色', 'role_type', '数据权限', '1', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('23', 'R1', '工作流角色', 'role_type', '用户角色', '2', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('24', 'S0', '禁用', 'user_state', '用户状态', '2', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('25', 'S1', '启用', 'user_state', '用户状态', '1', null, '0', null, null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('26', '昂按', '随碟附送分', 'kkkk', '水电费水电费', null, null, '0', null, TO_DATE('2018-02-28 15:43:34', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('27', '昂按', '随碟附送分', 'kkkk', '水电费水电费', null, null, '0', null, TO_DATE('2018-02-28 15:46:08', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('28', 'sdf', 'sdfsdf', 'sfsdf', 'sdfsdf', '1', null, '1', null, TO_DATE('2018-02-28 15:47:56', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('29', 'asas', 'sdfsd', 'sd', 'sd', null, null, '1', null, TO_DATE('2018-02-28 15:50:32', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('30', 'asas', 'sdfsd', 'sd', 'sd', null, null, '1', null, TO_DATE('2018-02-28 15:50:50', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('31', '1', '男', 'gender', '性别', null, null, '0', null, TO_DATE('2018-03-10 11:36:19', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('32', '2', '女', 'gender', '性别', null, null, '0', null, TO_DATE('2018-03-10 11:36:20', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('33', '0', '否', 'yes_no', '是否', '1', null, '0', 'CMS', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('34', '1', '是', 'yes_no', '是否', '2', null, '0', 'CMS', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('35', '0', '平稳收益', 'financing_profit_type', '理财收益类型', '1', null, '0', 'CMS', TO_DATE('2018-09-17 19:57:11', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('36', '1', '递增收益', 'financing_profit_type', '理财收益类型', '2', null, '0', 'CMS', TO_DATE('2018-09-17 19:57:23', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('37', '2', '浮动收益', 'financing_profit_type', '理财收益类型', '3', null, '0', 'CMS', TO_DATE('2018-09-17 19:57:34', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('38', '0', '无风险', 'financing_risk_level', '风险等级', '1', null, '0', 'CMS', TO_DATE('2018-09-17 19:58:12', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('39', '1', '低风险', 'financing_risk_level', '风险等级', '2', null, '0', 'CMS', TO_DATE('2018-09-17 19:58:22', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('40', '2', '一般风险', 'financing_risk_level', '风险等级', '3', null, '0', 'CMS', TO_DATE('2018-09-17 19:58:36', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('41', '3', '高风险', 'financing_risk_level', '风险等级', '4', null, '0', 'CMS', TO_DATE('2018-09-17 19:58:47', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('42', '0', '保本', 'financing_break_even', '理财-是否保本', '0', null, '0', 'CMS', TO_DATE('2018-09-17 19:59:22', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('43', '1', '非保本', 'financing_break_even', '理财-是否保本', '1', null, '0', 'CMS', TO_DATE('2018-09-17 19:59:31', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('44', '0', '未开启', 'financing_project_status', '理财项目状态', '0', null, '0', 'CMS', TO_DATE('2018-09-17 20:00:05', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('45', '1', '筹集中', 'financing_project_status', '理财项目状态', '1', null, '0', 'CMS', TO_DATE('2018-09-17 20:00:14', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('46', '2', '进行中', 'financing_project_status', '理财项目状态', '2', null, '0', 'CMS', TO_DATE('2018-09-17 20:00:22', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('47', '3', '结息中', 'financing_project_status', '理财项目状态', '3', null, '0', 'CMS', TO_DATE('2018-09-17 20:00:31', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('48', '4', '已完成', 'financing_project_status', '理财项目状态', '4', null, '0', 'CMS', TO_DATE('2018-09-17 20:00:43', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('49', '9', '项目取消', 'financing_project_status', '理财项目状态', '9', null, '1', 'CMS', TO_DATE('2018-09-17 20:00:53', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('50', '0', '充值', 'financing_income_type', '钱包入账类型', '0', null, '0', 'CMS', TO_DATE('2018-09-18 11:13:05', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('51', '1', '撤回', 'financing_income_type', '钱包入账类型', '1', null, '0', 'CMS', TO_DATE('2018-09-18 11:13:13', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('52', '2', '赎回', 'financing_income_type', '钱包入账类型', '2', null, '0', 'CMS', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('53', '0', '刷卡支付', 'financing_pay_type', '支付类型', '0', null, '0', 'CMS', TO_DATE('2018-09-18 11:14:15', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('54', '1', '快捷支付', 'financing_pay_type', '支付类型', '1', null, '0', 'CMS', TO_DATE('2018-09-18 11:14:24', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('55', '0', '正在处理', 'financing_withdraw_detail_status', '理财提现明细状态', '0', null, '0', 'CMS', TO_DATE('2018-09-18 13:55:38', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('56', '1', '交易成功', 'financing_withdraw_detail_status', '理财提现明细状态', '1', null, '0', 'CMS', TO_DATE('2018-09-18 13:55:47', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('57', '5', '提现返还', 'financing_withdraw_detail_status', '理财提现明细状态', '5', null, '0', 'CMS', TO_DATE('2018-09-18 13:56:06', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('58', '99', '风控冻结', 'financing_withdraw_detail_status', '理财提现明细状态', '99', null, '0', 'CMS', TO_DATE('2018-09-18 13:56:16', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('59', '0', '购买', 'financing_purchase_status', '项目认购状态', '0', null, '0', 'CMS', TO_DATE('2018-09-18 13:56:50', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('60', '1', '撤回', 'financing_purchase_status', '项目认购状态', '1', null, '0', 'CMS', TO_DATE('2018-09-18 13:57:01', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('61', '2', '赎回', 'financing_purchase_status', '项目认购状态', '2', null, '0', 'CMS', TO_DATE('2018-09-18 13:57:14', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('62', '0', '登记', 'onlinepay_card_status', '无卡交易卡状态', '0', null, '0', 'CMS', TO_DATE('2018-09-27 11:33:22', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('63', '1', '可使用', 'onlinepay_card_status', '无卡交易卡状态', '1', null, '0', 'CMS', TO_DATE('2018-09-27 11:34:10', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('64', '3', '不可使用', 'onlinepay_card_status', '无卡交易卡状态', '2', null, '0', 'CMS', TO_DATE('2018-09-27 11:35:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('65', '310', '上海浦东发展银行', 'bank', '所属银行', '0', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:27', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('66', '301', '交通银行', 'bank', '所属银行', '1', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:27', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('67', '318', '渤海银行', 'bank', '所属银行', '2', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:27', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('68', '102', '中国工商银行', 'bank', '所属银行', '3', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:27', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('69', '103', '中国农业银行', 'bank', '所属银行', '4', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:27', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('70', '104', '中国银行', 'bank', '所属银行', '5', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:27', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('71', '105', '中国建设银行', 'bank', '所属银行', '6', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:27', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('72', '403', '中国邮政储蓄银行', 'bank', '所属银行', '7', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:27', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('73', '302', '中信银行', 'bank', '所属银行', '8', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:27', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('74', '303', '中国光大银行', 'bank', '所属银行', '9', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:28', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('75', '304', '华夏银行', 'bank', '所属银行', '10', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:28', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('76', '305', '中国民生银行', 'bank', '所属银行', '11', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:28', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('77', '306', '广东发展银行', 'bank', '所属银行', '12', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:28', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('78', '307', '深圳发展银行（原平安）', 'bank', '所属银行', '13', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:28', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('79', '308', '招商银行', 'bank', '所属银行', '14', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:28', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('80', '309', '兴业银行', 'bank', '所属银行', '15', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:28', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('81', '781', '厦门国际银行', 'bank', '所属银行', '16', null, '0', 'CMS', TO_DATE('2018-09-27 08:43:28', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('82', '1', '国家', 'sys_area_type', '区域类型', '0', null, '0', 'CMS', TO_DATE('2018-09-28 09:27:27', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('83', '2', '省份、直辖市', 'sys_area_type', '区域类型', '1', null, '0', 'CMS', TO_DATE('2018-09-28 09:28:02', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('84', '3', '地市', 'sys_area_type', '区域类型', '2', null, '0', 'CMS', TO_DATE('2018-09-28 09:28:31', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('85', '4', '区县', 'sys_area_type', '区域类型', '3', null, '0', 'CMS', TO_DATE('2018-09-28 09:28:55', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('86', '1', '绑卡', 'sys_sms_text_type', '发送短信类型', '0', null, '0', 'CMS', TO_DATE('2018-09-28 09:29:45', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('87', '0', '未支付', 'onlinepay_status', '无卡支付交易状态', '0', null, '0', 'CMS', TO_DATE('2018-09-29 17:08:31', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('88', '1', '支付成功', 'onlinepay_status', '无卡支付交易状态', '1', null, '0', 'CMS', TO_DATE('2018-09-29 17:09:08', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('89', '2', '交易失败', 'onlinepay_status', '无卡支付交易状态', '2', null, '0', 'CMS', TO_DATE('2018-09-29 17:09:32', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('90', '1', '待审核', 'merchant_card_review_status', '修改结算卡审核状态', '0', null, '0', 'CMS', TO_DATE('2018-09-30 11:11:53', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('91', '2', '拒绝', 'merchant_card_review_status', '修改结算卡审核状态', '1', null, '0', 'CMS', TO_DATE('2018-09-30 11:12:48', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('92', '3', '通过', 'merchant_card_review_status', '修改结算卡审核状态', '2', null, '0', 'CMS', TO_DATE('2018-09-30 11:13:20', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('93', '0', '不显示', 'financing_platform_config_show_nav', '是否显示顶部NAV', '0', null, '0', 'CMS', TO_DATE('2018-10-25 17:30:57', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('94', '1', '显示', 'financing_platform_config_show_nav', '是否显示顶部NAV', '1', null, '0', 'CMS', TO_DATE('2018-10-25 17:31:38', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('95', '313', '北京银行', 'bank', '所属银行', '17', null, '0', 'CMS', TO_DATE('2018-10-29 09:43:42', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('96', '325', '上海银行', 'bank', '所属银行', '18', null, '0', 'CMS', TO_DATE('2018-10-29 09:52:14', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('97', '1', '结算卡身份证正面+银行卡正面合照', 'financing_pic_type', '图片类型', '0', null, '0', 'CMS', TO_DATE('2018-11-13 14:24:29', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('98', '2', '修改结算卡身份证正面+银行卡正面合照审核照片', 'financing_pic_type', '图片类型', '0', null, '0', 'CMS', TO_DATE('2018-11-13 14:26:56', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('10007', '1', '团长分润', 'should_send_detail_type', '应发明细奖励类型', '0', null, '0', 'big-date', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10008', '2', '团长补贴', 'should_send_detail_type', '应发明细奖励类型', '1', null, '0', 'big-date', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10009', '3', '大机构奖', 'should_send_detail_type', '应发明细奖励类型', '2', null, '0', 'big-date', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10010', '4', '商户活跃奖励', 'should_send_detail_type', '应发明细奖励类型', '3', null, '0', 'big-date', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10011', '5', '团长设备返现奖励', 'should_send_detail_type', '应发明细奖励类型', '4', null, '0', 'big-date', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10012', '6', '创客激活奖励', 'should_send_detail_type', '应发明细奖励类型', '5', null, '0', 'big-date', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10013', '6', '应发明细-创客激活奖励', 'should_send_detail_type', '应发明细奖励类型', '5', null, '1', 'big-date', TO_DATE('2018-12-26 14:07:10', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_DICT" VALUES ('10014', '7', '创客设备返现奖励', 'should_send_detail_type', '应发明细奖励类型', '6', null, '0', 'big-date', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10015', '8', '创客交易达标奖励', 'should_send_detail_type', '应发明细奖励类型', '7', null, '0', 'big-date', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10016', '9', '创客拓展奖日结', 'should_send_detail_type', '应发明细奖励类型', '8', null, '0', 'big-date', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10017', '10', '创客服务费', 'should_send_detail_type', '应发明细奖励类型', '9', null, '0', 'big-date', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10018', '11', '创客培训费', 'should_send_detail_type', '应发明细奖励类型', '10', null, '0', 'big-date', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10019', '12', '银惠通商户补贴', 'should_send_detail_type', '应发明细奖励类型', '11', null, '0', 'big-date', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10020', '13', '团长推荐办卡奖励', 'should_send_detail_type', '应发明细奖励类型', '12', null, '0', 'big-date', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10021', '14', '推荐办卡奖励', 'should_send_detail_type', '应发明细奖励类型', '13', null, '0', 'big-date', null);
INSERT INTO "TZB"."CORE_DICT" VALUES ('10022', '15', '团队建设奖励', 'should_send_detail_type', '应发明细奖励类型', '14', null, '0', 'big-date', null);

-- ----------------------------
-- Table structure for CORE_FILE
-- ----------------------------
DROP TABLE "TZB"."CORE_FILE";
CREATE TABLE "TZB"."CORE_FILE" (
"ID" NUMBER(11) NOT NULL ,
"NAME" NVARCHAR2(64) NULL ,
"PATH" NVARCHAR2(255) NULL ,
"BIZ_ID" NVARCHAR2(128) NULL ,
"USER_ID" NUMBER(11) NULL ,
"CREATE_TIME" DATE NULL ,
"ORG_ID" NUMBER(11) NULL ,
"BIZ_TYPE" NVARCHAR2(128) NULL ,
"FILE_BATCH_ID" NVARCHAR2(128) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "TZB"."CORE_FILE" IS '管理平台数据';
COMMENT ON COLUMN "TZB"."CORE_FILE"."NAME" IS '文件名称';
COMMENT ON COLUMN "TZB"."CORE_FILE"."PATH" IS '路径';
COMMENT ON COLUMN "TZB"."CORE_FILE"."BIZ_ID" IS '业务ID';
COMMENT ON COLUMN "TZB"."CORE_FILE"."USER_ID" IS '上传人id';
COMMENT ON COLUMN "TZB"."CORE_FILE"."CREATE_TIME" IS '创建时间';

-- ----------------------------
-- Records of CORE_FILE
-- ----------------------------
INSERT INTO "TZB"."CORE_FILE" VALUES ('19', 'dict_upload_template.xls', '20180311/dict_upload_template.xls.8caa38fb-52ef-4c73-85ea-abfb1f5c5dc4', null, '1', TO_DATE('2018-03-11 15:36:58', 'YYYY-MM-DD HH24:MI:SS'), '1', null, '18c0dd67-e334-47ba-8969-915bcf77c544');
INSERT INTO "TZB"."CORE_FILE" VALUES ('20', 'dict_upload_template.xls', '20180311/dict_upload_template.xls.f50b7f0f-d376-4a95-be6a-14f5aa4a81e6', null, '1', TO_DATE('2018-03-11 15:37:15', 'YYYY-MM-DD HH24:MI:SS'), '1', null, '335a583a-9c74-462d-be0a-5a82d427b1aa');
INSERT INTO "TZB"."CORE_FILE" VALUES ('21', 'dict_upload_template.xls', '20180311/dict_upload_template.xls.b0b9434d-e367-43ef-b8ac-366cf7b018b6', null, '1', TO_DATE('2018-03-11 15:38:52', 'YYYY-MM-DD HH24:MI:SS'), '1', null, 'a5b887c6-101c-46e8-b9e2-b3b448edff34');
INSERT INTO "TZB"."CORE_FILE" VALUES ('22', 'dict_upload_template.xls', '20180311/dict_upload_template.xls.15f02d15-2dd0-4cb7-b2e5-4f7d72fb497d', null, '1', TO_DATE('2018-03-11 15:39:02', 'YYYY-MM-DD HH24:MI:SS'), '1', null, '833d96bc-797c-403f-aa2e-00e2b5a3cd71');
INSERT INTO "TZB"."CORE_FILE" VALUES ('23', 'dict_upload_template.xls', '20180311/dict_upload_template.xls.f12350bc-31da-4875-a78e-0135f512fb4c', null, '1', TO_DATE('2018-03-11 15:41:52', 'YYYY-MM-DD HH24:MI:SS'), '1', null, '0b2a66a3-8aa8-46b7-8bf0-ce9f68041cd8');
INSERT INTO "TZB"."CORE_FILE" VALUES ('24', 'dict_upload_template.xls', '20180311/dict_upload_template.xls.5bf626e5-2152-45a5-a432-fc2e9fcb7903', null, '1', TO_DATE('2018-03-11 15:43:18', 'YYYY-MM-DD HH24:MI:SS'), '1', null, '813725ab-2c07-4e48-a766-7ebbe3083212');
INSERT INTO "TZB"."CORE_FILE" VALUES ('25', 'dict_upload_template.xls', '20180311/dict_upload_template.xls.3cd3eb95-aab9-4105-8d28-76a723274709', null, '1', TO_DATE('2018-03-11 15:43:58', 'YYYY-MM-DD HH24:MI:SS'), '1', null, '4216455c-20d7-4912-bfc8-c8cca7e70e9f');
INSERT INTO "TZB"."CORE_FILE" VALUES ('26', 'dict_upload_template.xls', '20180311/dict_upload_template.xls.d3dc557b-1e77-4955-a3be-7a8b4f86407c', null, '1', TO_DATE('2018-03-11 15:45:02', 'YYYY-MM-DD HH24:MI:SS'), '1', null, 'e42dc975-edd5-4d16-8529-fa69b56a5eb5');
INSERT INTO "TZB"."CORE_FILE" VALUES ('34', 'dict_upload_template.xls', '20180311/dict_upload_template.xls.d50f8245-ec3e-4de1-9742-0c5c12105f27', '175', '1', TO_DATE('2018-03-11 16:30:36', 'YYYY-MM-DD HH24:MI:SS'), '1', null, '79b294da-8792-4bfd-9d84-e3f989b88cdf');
INSERT INTO "TZB"."CORE_FILE" VALUES ('37', '副本 功能列表.xlsx', '20180311/副本 功能列表.xlsx.bc7554e3-2a30-4667-aa61-0e280340b2be', '175', '1', TO_DATE('2018-03-11 18:53:41', 'YYYY-MM-DD HH24:MI:SS'), '1', 'User', '79b294da-8792-4bfd-9d84-e3f989b88cdf');
INSERT INTO "TZB"."CORE_FILE" VALUES ('38', '副本 功能列表.xlsx', '20180311/副本 功能列表.xlsx.d991eb1f-24a9-4db1-87c1-7ef9d2b8a40a', '175', '1', TO_DATE('2018-03-11 22:10:57', 'YYYY-MM-DD HH24:MI:SS'), '1', 'User', '79b294da-8792-4bfd-9d84-e3f989b88cdf');

-- ----------------------------
-- Table structure for CORE_FILE_TAG
-- ----------------------------
DROP TABLE "TZB"."CORE_FILE_TAG";
CREATE TABLE "TZB"."CORE_FILE_TAG" (
"ID" NUMBER(11) NOT NULL ,
"KEY" NVARCHAR2(64) NOT NULL ,
"VALUE" NVARCHAR2(255) NOT NULL ,
"FILE_ID" NUMBER(11) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "TZB"."CORE_FILE_TAG" IS '管理平台数据';
COMMENT ON COLUMN "TZB"."CORE_FILE_TAG"."KEY" IS 'key，关键字';
COMMENT ON COLUMN "TZB"."CORE_FILE_TAG"."VALUE" IS '关键字对应的值';
COMMENT ON COLUMN "TZB"."CORE_FILE_TAG"."FILE_ID" IS 'sys_file的id，文件id';

-- ----------------------------
-- Records of CORE_FILE_TAG
-- ----------------------------
INSERT INTO "TZB"."CORE_FILE_TAG" VALUES ('1', 'customer', '12332', '1');
INSERT INTO "TZB"."CORE_FILE_TAG" VALUES ('2', 'type', 'crdit', '2');

-- ----------------------------
-- Table structure for CORE_FUNCTION
-- ----------------------------
DROP TABLE "TZB"."CORE_FUNCTION";
CREATE TABLE "TZB"."CORE_FUNCTION" (
"ID" NUMBER(11) NOT NULL ,
"CODE" NVARCHAR2(1024) NULL ,
"NAME" NVARCHAR2(16) NULL ,
"CREATE_TIME" DATE NULL ,
"ACCESS_URL" NVARCHAR2(1024) NULL ,
"PARENT_ID" NUMBER(11) NULL ,
"TYPE" NVARCHAR2(4) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "TZB"."CORE_FUNCTION" IS '管理平台数据';

-- ----------------------------
-- Records of CORE_FUNCTION
-- ----------------------------
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('1', 'user', '用户功能', null, '/admin/user/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('2', 'user.query', '用户列表', null, null, '1', 'FN1');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('3', 'user.edit', '用户编辑', null, null, '1', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('6', 'org', '组织机构', null, '/admin/org/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('7', 'role', '角色管理', null, '/admin/role/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('8', 'menu', '菜单管理', null, '/admin/menu/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('9', 'function', '功能点管理', null, '/admin/function/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('10', 'roleFunction', '角色功能授权', null, '/admin/role/function.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('11', 'roleDataAccess', '角色数据授权', null, '/admin/role/data.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('12', 'code', '代码生成', null, '/core/codeGen/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('13', 'dict', '字典管理', null, '/admin/dict/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('18', 'trace', '审计查询', null, '/admin/audit/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('19', 'file', '相关文档', null, '/trade/interAndRelate/file.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('91', 'test', '测试', TO_DATE('2017-10-11 16:59:01', 'YYYY-MM-DD HH24:MI:SS'), '/test/test.do', '6', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('161', 'role.add', '角色添加', TO_DATE('2017-10-23 09:45:05', 'YYYY-MM-DD HH24:MI:SS'), null, '7', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('167', 'workflow.admin', '工作流监控', null, '/admin/workflow/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('180', 'code.query', '代码生成测试', null, null, '12', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('181', 'blog.query', '博客查询功能', null, null, '182', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('182', 'blog', '博客测试', null, '/admin/blog/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('183', 'code.project', '项目生成', TO_DATE('2018-03-01 09:38:17', 'YYYY-MM-DD HH24:MI:SS'), '/core/codeGen/project.do', '12', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('184', 'financingProject', '理财项目功能', TO_DATE('2018-09-17 17:56:15', 'YYYY-MM-DD HH24:MI:SS'), '/cms/financingProject/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('186', 'financingWallet.query', '理财钱包信息', TO_DATE('2018-09-17 20:14:59', 'YYYY-MM-DD HH24:MI:SS'), '/cms/financingWallet/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('187', 'financingIncomeDetail', '理财入账明细', TO_DATE('2018-09-18 10:10:47', 'YYYY-MM-DD HH24:MI:SS'), '/cms/financingIncomeDetail/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('188', 'financingWithdrawDetail', '理财提现明细', TO_DATE('2018-09-18 11:49:18', 'YYYY-MM-DD HH24:MI:SS'), '/cms/financingWithdrawDetail/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('189', 'financingPurchaseDetail.query', '理财购买明细', TO_DATE('2018-09-18 15:38:27', 'YYYY-MM-DD HH24:MI:SS'), '/cms/financingPurchaseDetail/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('190', 'financingProfitDetail.query', '理财分润明细', TO_DATE('2018-09-18 15:57:01', 'YYYY-MM-DD HH24:MI:SS'), '/cms/financingProfitDetail/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('191', 'financingMerchant.query', '理财商户', TO_DATE('2018-09-19 11:46:36', 'YYYY-MM-DD HH24:MI:SS'), '/cms/financingMerchant/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('192', 'financingProjectTemplate', '理财项目模板', TO_DATE('2018-09-19 16:38:17', 'YYYY-MM-DD HH24:MI:SS'), '/cms/financingProjectTemplate/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('193', 'financingPlatformConfig.query', '平台配置', TO_DATE('2018-09-19 16:51:23', 'YYYY-MM-DD HH24:MI:SS'), '/cms/financingPlatformConfig/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('194', 'tradingOnlinepayOrder.query', '无卡订单', TO_DATE('2018-09-19 18:19:07', 'YYYY-MM-DD HH24:MI:SS'), '/cms/tradingOnlinepayOrder/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('195', 'tradingOnlinepayCardInfo.query', '无卡交易卡信息', TO_DATE('2018-09-19 19:42:54', 'YYYY-MM-DD HH24:MI:SS'), '/cms/tradingOnlinepayCardInfo/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('196', 'financingMerchantCardReview', '商户结算卡修改审核', TO_DATE('2018-09-30 11:29:54', 'YYYY-MM-DD HH24:MI:SS'), '/cms/financingMerchantCardReview/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('197', 'financingProject.query', '理财项目列表', TO_DATE('2018-10-31 16:49:38', 'YYYY-MM-DD HH24:MI:SS'), null, '184', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('198', 'financingProject.edit', '理财项目编辑', TO_DATE('2018-10-31 16:49:38', 'YYYY-MM-DD HH24:MI:SS'), null, '184', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('199', 'financingProjectTemplate.query', '理财项目模板列表', TO_DATE('2018-09-19 16:38:17', 'YYYY-MM-DD HH24:MI:SS'), null, '192', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('200', 'financingProjectTemplate.edit', '理财项目模板编辑', TO_DATE('2018-09-19 16:38:17', 'YYYY-MM-DD HH24:MI:SS'), null, '192', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('201', 'financingIncomeDetail.query', '理财入账明细列表', TO_DATE('2018-09-18 10:10:47', 'YYYY-MM-DD HH24:MI:SS'), null, '187', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('203', 'financingIncomeDetail.exportDocument', '理财入账明细导出', TO_DATE('2018-09-18 10:10:47', 'YYYY-MM-DD HH24:MI:SS'), null, '187', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('204', 'financingWithdrawDetail.query', '理财提现明细列表', TO_DATE('2018-09-18 11:49:18', 'YYYY-MM-DD HH24:MI:SS'), null, '188', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('205', 'financingWithdrawDetail.exportDocument', '理财提现明细导出', TO_DATE('2018-09-18 11:49:18', 'YYYY-MM-DD HH24:MI:SS'), null, '188', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('206', 'financingWithdrawDetail.importDocument', '理财提现明细导入', TO_DATE('2018-09-18 11:49:18', 'YYYY-MM-DD HH24:MI:SS'), null, '188', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('207', 'financingMerchantCardReview.query', '商户结算卡修改审核列表', TO_DATE('2018-09-30 11:29:54', 'YYYY-MM-DD HH24:MI:SS'), null, '196', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('208', 'financingMerchantCardReview.audit', '商户结算卡修改审核审核', TO_DATE('2018-09-30 11:29:54', 'YYYY-MM-DD HH24:MI:SS'), null, '196', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('209', 'financingWallet.exportDocument', '理财钱包数据导出', TO_DATE('2018-11-12 11:22:05', 'YYYY-MM-DD HH24:MI:SS'), 'null', '186', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('210', 'tradingOnlinepayOrder.exportDocument', '无卡订单数据导出', TO_DATE('2018-11-12 15:04:23', 'YYYY-MM-DD HH24:MI:SS'), 'null', '194', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('211', 'financingProfitDetail.reportSearchData', '理财分润明细统计', TO_DATE('2018-12-13 15:41:17', 'YYYY-MM-DD HH24:MI:SS'), 'test', '190', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('20001', 'bigHeadTradingActivation', '团长交易和激活量统计', TO_DATE('2018-12-20 19:10:16', 'YYYY-MM-DD HH24:MI:SS'), '/bdata/bigHeadTradingActivation/index.do', '0', 'FN0');
INSERT INTO "TZB"."CORE_FUNCTION" VALUES ('10005', 'bigShouldSendDetailIng', '应发明细', TO_DATE('2018-12-26 11:44:16', 'YYYY-MM-DD HH24:MI:SS'), '/bdata/bigShouldSendDetailIng/index.do', '0', 'FN0');

-- ----------------------------
-- Table structure for CORE_MENU
-- ----------------------------
DROP TABLE "TZB"."CORE_MENU";
CREATE TABLE "TZB"."CORE_MENU" (
"ID" NUMBER(11) NOT NULL ,
"CODE" NVARCHAR2(16) NULL ,
"NAME" NVARCHAR2(16) NULL ,
"CREATE_TIME" DATE NULL ,
"FUNCTION_ID" NUMBER(11) NULL ,
"TYPE" NVARCHAR2(6) NULL ,
"PARENT_MENU_ID" NUMBER(11) NULL ,
"SEQ" NUMBER(11) NULL ,
"ICON" NVARCHAR2(255) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "TZB"."CORE_MENU" IS '管理平台数据';
COMMENT ON COLUMN "TZB"."CORE_MENU"."TYPE" IS '1,系统，2 导航 3 菜单项（对应某个功能点）';
COMMENT ON COLUMN "TZB"."CORE_MENU"."ICON" IS '图标';

-- ----------------------------
-- Records of CORE_MENU
-- ----------------------------
INSERT INTO "TZB"."CORE_MENU" VALUES ('8', '系统管理', '系统管理', null, null, 'MENU_S', '0', '1', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('10', '用户管理', '用户管理', null, '1', 'MENU_M', '18', '1', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('11', '组织机构管理', '组织机构管理', null, '6', 'MENU_M', '18', '2', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('12', '角色管理', '角色管理', null, '7', 'MENU_M', '18', '3', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('13', '菜单项', '菜单项', null, '8', 'MENU_M', '18', '4', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('14', '功能点管理', '功能点管理', null, '9', 'MENU_M', '18', '5', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('15', '字典数据管理', '字典数据管理', null, '13', 'MENU_M', '18', '6', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('16', '审计查询', '审计查询', null, '18', 'MENU_M', '19', '7', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('17', '代码生成', '代码生成', null, '12', 'MENU_M', '24', '8', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('18', '基础管理', '基础管理', null, null, 'MENU_N', '8', '1', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('19', '监控管理', '监控管理', null, null, 'MENU_N', '8', '2', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('20', '流程监控', '流程监控', null, '167', 'MENU_M', '19', '3', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('21', '角色功能授权', '角色功能授权', null, '10', 'MENU_M', '18', '8', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('22', '角色数据授权', '角色数据授权', null, '11', 'MENU_M', '18', '9', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('23', '博客测试', '博客测试1', null, '182', 'MENU_M', '19', '9', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('24', '代码生成导航', '代码生成', TO_DATE('2018-03-01 09:39:31', 'YYYY-MM-DD HH24:MI:SS'), null, 'MENU_N', '8', '1', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('25', '子系统生成', '子系统生成', TO_DATE('2018-03-01 09:42:35', 'YYYY-MM-DD HH24:MI:SS'), '183', 'MENU_M', '24', '1', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('26', 'CMS', '大数据', TO_DATE('2018-09-17 16:12:31', 'YYYY-MM-DD HH24:MI:SS'), null, 'MENU_S', '0', '1', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('10006', '应发明细', '应发明细', TO_DATE('2018-12-26 11:46:55', 'YYYY-MM-DD HH24:MI:SS'), '10005', 'MENU_M', '10001', '1', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('10001', '拓展宝财务工具', '拓展宝财务工具', TO_DATE('2018-12-20 18:28:19', 'YYYY-MM-DD HH24:MI:SS'), null, 'MENU_N', '26', '1', null);
INSERT INTO "TZB"."CORE_MENU" VALUES ('210001', '团长交易和激活量统计', '团长交易和激活量统计', TO_DATE('2018-12-21 09:34:21', 'YYYY-MM-DD HH24:MI:SS'), '20001', 'MENU_M', '10001', '1', null);

-- ----------------------------
-- Table structure for CORE_ORG
-- ----------------------------
DROP TABLE "TZB"."CORE_ORG";
CREATE TABLE "TZB"."CORE_ORG" (
"ID" NUMBER(11) NOT NULL ,
"CODE" NVARCHAR2(16) NOT NULL ,
"NAME" NVARCHAR2(16) NOT NULL ,
"CREATE_TIME" DATE NULL ,
"PARENT_ORG_ID" NUMBER(11) NULL ,
"TYPE" NVARCHAR2(6) NOT NULL ,
"DEL_FLAG" NUMBER(4) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "TZB"."CORE_ORG" IS '管理平台数据';
COMMENT ON COLUMN "TZB"."CORE_ORG"."TYPE" IS '1 公司，2 部门，3 小组';

-- ----------------------------
-- Records of CORE_ORG
-- ----------------------------
INSERT INTO "TZB"."CORE_ORG" VALUES ('1', '集团公司', '集团', TO_DATE('2018-02-02 17:18:50', 'YYYY-MM-DD HH24:MI:SS'), null, 'ORGT0', '0');
INSERT INTO "TZB"."CORE_ORG" VALUES ('3', '信息科技部门', '信息科技部门', null, '1', 'ORGT2', '0');
INSERT INTO "TZB"."CORE_ORG" VALUES ('4', '贵州银行', '贵州银行', TO_DATE('2018-02-02 17:18:56', 'YYYY-MM-DD HH24:MI:SS'), '1', 'ORGT1', '0');
INSERT INTO "TZB"."CORE_ORG" VALUES ('5', '贵州银行金科', '贵州银行金融科技开发公司', null, '4', 'ORGT1', '0');
INSERT INTO "TZB"."CORE_ORG" VALUES ('6', '金科研发', '金科研发', null, '5', 'ORGT2', '0');
INSERT INTO "TZB"."CORE_ORG" VALUES ('7', '金科研发部门', '金科研发部门', TO_DATE('2018-02-05 13:49:52', 'YYYY-MM-DD HH24:MI:SS'), '6', 'ORGT2', '1');
INSERT INTO "TZB"."CORE_ORG" VALUES ('8', '金科研发2部', '金科研发2部', TO_DATE('2018-02-05 13:50:43', 'YYYY-MM-DD HH24:MI:SS'), '6', 'ORGT2', '1');

-- ----------------------------
-- Table structure for CORE_ROLE
-- ----------------------------
DROP TABLE "TZB"."CORE_ROLE";
CREATE TABLE "TZB"."CORE_ROLE" (
"ID" NUMBER(11) NOT NULL ,
"CODE" NVARCHAR2(16) NULL ,
"NAME" NVARCHAR2(255) NULL ,
"CREATE_TIME" DATE NULL ,
"TYPE" NVARCHAR2(6) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "TZB"."CORE_ROLE" IS '管理平台数据';
COMMENT ON COLUMN "TZB"."CORE_ROLE"."CODE" IS '角色编码';
COMMENT ON COLUMN "TZB"."CORE_ROLE"."NAME" IS '角色名称';
COMMENT ON COLUMN "TZB"."CORE_ROLE"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "TZB"."CORE_ROLE"."TYPE" IS '1 可以配置 2 固定权限角色';

-- ----------------------------
-- Records of CORE_ROLE
-- ----------------------------
INSERT INTO "TZB"."CORE_ROLE" VALUES ('2', 'CEO', '公司CEO', null, 'R0');
INSERT INTO "TZB"."CORE_ROLE" VALUES ('15', 'admin', 'admin', null, 'R0');
INSERT INTO "TZB"."CORE_ROLE" VALUES ('16', 'caiwu', '财务', TO_DATE('2018-11-12 11:09:05', 'YYYY-MM-DD HH24:MI:SS'), 'R0');

-- ----------------------------
-- Table structure for CORE_ROLE_FUNCTION
-- ----------------------------
DROP TABLE "TZB"."CORE_ROLE_FUNCTION";
CREATE TABLE "TZB"."CORE_ROLE_FUNCTION" (
"ID" NUMBER(11) NOT NULL ,
"ROLE_ID" NUMBER(11) NULL ,
"FUNCTION_ID" NUMBER(11) NULL ,
"DATA_ACCESS_TYPE" NUMBER(4) NULL ,
"DATA_ACCESS_POLICY" NVARCHAR2(128) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "TZB"."CORE_ROLE_FUNCTION" IS '管理平台数据';

-- ----------------------------
-- Records of CORE_ROLE_FUNCTION
-- ----------------------------
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('197', null, '1', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('198', null, '2', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('199', null, '3', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('200', null, '6', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('201', null, '91', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('202', null, '8', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('203', '2', '184', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('204', '2', '186', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('205', '2', '187', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('206', '2', '188', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('207', '2', '189', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('208', '2', '190', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('209', '2', '191', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('210', '2', '192', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('211', '2', '193', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('212', '2', '194', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('213', '2', '197', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('214', '2', '198', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('215', '2', '201', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('216', '2', '203', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('217', '2', '204', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('218', '2', '205', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('219', '2', '206', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('220', '2', '199', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('221', '2', '200', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('222', '2', '195', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('223', '2', '196', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('224', '2', '207', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('225', '2', '208', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('226', '16', '184', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('227', '16', '197', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('228', '16', '186', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('229', '16', '187', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('230', '16', '201', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('231', '16', '203', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('232', '16', '188', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('233', '16', '204', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('234', '16', '205', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('235', '16', '206', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('236', '16', '189', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('237', '16', '190', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('238', '16', '191', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('239', '16', '192', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('240', '16', '199', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('241', '16', '193', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('242', '16', '194', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('243', '16', '195', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('244', '16', '196', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('245', '16', '207', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('246', '16', '208', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('247', '2', '209', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('248', '2', '210', null, null);
INSERT INTO "TZB"."CORE_ROLE_FUNCTION" VALUES ('249', '2', '211', null, null);

-- ----------------------------
-- Table structure for CORE_ROLE_MENU
-- ----------------------------
DROP TABLE "TZB"."CORE_ROLE_MENU";
CREATE TABLE "TZB"."CORE_ROLE_MENU" (
"ID" NUMBER(11) NOT NULL ,
"ROLE_ID" NUMBER(11) NULL ,
"MENU_ID" NUMBER(11) NULL ,
"CREATE_TIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "TZB"."CORE_ROLE_MENU" IS '管理平台数据';

-- ----------------------------
-- Records of CORE_ROLE_MENU
-- ----------------------------
INSERT INTO "TZB"."CORE_ROLE_MENU" VALUES ('196', null, '10', null);
INSERT INTO "TZB"."CORE_ROLE_MENU" VALUES ('197', null, '11', null);
INSERT INTO "TZB"."CORE_ROLE_MENU" VALUES ('198', null, '13', null);

-- ----------------------------
-- Table structure for CORE_USER
-- ----------------------------
DROP TABLE "TZB"."CORE_USER";
CREATE TABLE "TZB"."CORE_USER" (
"ID" NUMBER(11) NOT NULL ,
"CODE" NVARCHAR2(16) NULL ,
"NAME" NVARCHAR2(16) NULL ,
"PASSWORD" NVARCHAR2(64) NULL ,
"CREATE_TIME" DATE NULL ,
"ORG_ID" NUMBER(11) NULL ,
"STATE" NVARCHAR2(16) NULL ,
"JOB_TYPE1" NVARCHAR2(16) NULL ,
"DEL_FLAG" NUMBER(4) NULL ,
"update_Time" DATE NULL ,
"JOB_TYPE0" NVARCHAR2(16) NULL ,
"attachment_id" NVARCHAR2(128) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "TZB"."CORE_USER" IS '管理平台数据';
COMMENT ON COLUMN "TZB"."CORE_USER"."STATE" IS '用户状态 1:启用 0:停用';
COMMENT ON COLUMN "TZB"."CORE_USER"."DEL_FLAG" IS '用户删除标记 0:未删除 1:已删除';

-- ----------------------------
-- Records of CORE_USER
-- ----------------------------
INSERT INTO "TZB"."CORE_USER" VALUES ('1', 'admin', '超级管理员1', '123456', TO_DATE('2017-09-13 09:21:03', 'YYYY-MM-DD HH24:MI:SS'), '1', 'S1', 'JT_S_01', '0', TO_DATE('2018-11-12 10:00:12', 'YYYY-MM-DD HH24:MI:SS'), 'JT_01', null);
INSERT INTO "TZB"."CORE_USER" VALUES ('171', 'lixx', '李小小', null, TO_DATE('2018-01-28 11:21:20', 'YYYY-MM-DD HH24:MI:SS'), '3', 'S1', 'JT_S_04', '1', null, 'JT_02', null);
INSERT INTO "TZB"."CORE_USER" VALUES ('172', 'lixx2', '李xx2', '123456', TO_DATE('2018-01-28 11:22:38', 'YYYY-MM-DD HH24:MI:SS'), '4', 'S1', 'JT_S_02', '1', null, 'JT_01', null);
INSERT INTO "TZB"."CORE_USER" VALUES ('173', 'test1', 'test1', '123', TO_DATE('2018-01-28 14:44:55', 'YYYY-MM-DD HH24:MI:SS'), '5', 'S1', 'JT_S_04', '1', null, 'JT_02', null);
INSERT INTO "TZB"."CORE_USER" VALUES ('174', 'hank250', '李小熊', null, TO_DATE('2018-02-16 11:36:41', 'YYYY-MM-DD HH24:MI:SS'), '4', 'S1', 'JT_S_04', '1', null, 'JT_02', null);
INSERT INTO "TZB"."CORE_USER" VALUES ('175', 'test123', 'test12344', '123456', TO_DATE('2018-03-11 16:19:21', 'YYYY-MM-DD HH24:MI:SS'), '3', 'S1', 'JT_S_04', '1', TO_DATE('2018-09-19 10:48:44', 'YYYY-MM-DD HH24:MI:SS'), 'JT_02', '79b294da-8792-4bfd-9d84-e3f989b88cdf');
INSERT INTO "TZB"."CORE_USER" VALUES ('176', '15505950081', '陈磊', '123456', TO_DATE('2018-10-30 22:12:37', 'YYYY-MM-DD HH24:MI:SS'), '3', 'S1', 'JT_S_01', '0', TO_DATE('2018-10-30 22:16:05', 'YYYY-MM-DD HH24:MI:SS'), 'JT_01', '6bcb7a5a-b659-42c5-9e11-b58bf813b07c');
INSERT INTO "TZB"."CORE_USER" VALUES ('177', 'xiaohang', '小杭', '123456', TO_DATE('2018-10-30 22:17:41', 'YYYY-MM-DD HH24:MI:SS'), '3', 'S1', 'JT_S_04', '0', TO_DATE('2018-12-13 15:40:37', 'YYYY-MM-DD HH24:MI:SS'), 'JT_02', '58364f3c-14de-445f-b2fe-d0d3d49214e3');
INSERT INTO "TZB"."CORE_USER" VALUES ('178', 'caiwutest', '财务测试', '123456', TO_DATE('2018-11-12 11:20:08', 'YYYY-MM-DD HH24:MI:SS'), '1', 'S1', null, '0', TO_DATE('2018-11-12 11:20:54', 'YYYY-MM-DD HH24:MI:SS'), 'JT_01', '03193f21-9ab4-4cd7-97bc-5c42a9b4cfb1');

-- ----------------------------
-- Table structure for CORE_USER_ROLE
-- ----------------------------
DROP TABLE "TZB"."CORE_USER_ROLE";
CREATE TABLE "TZB"."CORE_USER_ROLE" (
"ID" NUMBER(11) NOT NULL ,
"USER_ID" NUMBER(11) NULL ,
"ROLE_ID" NUMBER(11) NULL ,
"ORG_ID" NUMBER(11) NULL ,
"CREATE_TIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "TZB"."CORE_USER_ROLE" IS '管理平台数据 用户角色关系表';

-- ----------------------------
-- Records of CORE_USER_ROLE
-- ----------------------------
INSERT INTO "TZB"."CORE_USER_ROLE" VALUES ('2', '4', '2', '5', null);
INSERT INTO "TZB"."CORE_USER_ROLE" VALUES ('52', '47', '2', '1', TO_DATE('2017-09-07 01:12:02', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_USER_ROLE" VALUES ('55', '68', '2', '3', TO_DATE('2017-09-07 21:42:03', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_USER_ROLE" VALUES ('173', '1', '15', '1', TO_DATE('2018-09-19 10:48:04', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_USER_ROLE" VALUES ('174', '1', '2', '1', TO_DATE('2018-09-19 10:48:13', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_USER_ROLE" VALUES ('175', '176', '2', '3', TO_DATE('2018-10-30 22:15:49', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_USER_ROLE" VALUES ('176', '177', '2', '3', TO_DATE('2018-10-30 22:17:52', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "TZB"."CORE_USER_ROLE" VALUES ('177', '178', '16', '1', TO_DATE('2018-11-12 11:20:27', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Indexes structure for table CORE_AUDIT
-- ----------------------------

-- ----------------------------
-- Checks structure for table CORE_AUDIT
-- ----------------------------
ALTER TABLE "TZB"."CORE_AUDIT" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table CORE_AUDIT
-- ----------------------------
ALTER TABLE "TZB"."CORE_AUDIT" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table CORE_DICT
-- ----------------------------
CREATE INDEX "TZB"."idx_code"
ON "TZB"."CORE_DICT" ("TYPE" ASC)
LOGGING
VISIBLE;
CREATE INDEX "TZB"."idx_pid"
ON "TZB"."CORE_DICT" ("PARENT" ASC)
LOGGING
VISIBLE;
CREATE INDEX "TZB"."idx_value"
ON "TZB"."CORE_DICT" ("VALUE" ASC)
LOGGING
VISIBLE;

-- ----------------------------
-- Checks structure for table CORE_DICT
-- ----------------------------
ALTER TABLE "TZB"."CORE_DICT" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "TZB"."CORE_DICT" ADD CHECK ("VALUE" IS NOT NULL);
ALTER TABLE "TZB"."CORE_DICT" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "TZB"."CORE_DICT" ADD CHECK ("TYPE" IS NOT NULL);
ALTER TABLE "TZB"."CORE_DICT" ADD CHECK ("TYPE_NAME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table CORE_DICT
-- ----------------------------
ALTER TABLE "TZB"."CORE_DICT" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table CORE_FILE
-- ----------------------------

-- ----------------------------
-- Checks structure for table CORE_FILE
-- ----------------------------
ALTER TABLE "TZB"."CORE_FILE" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table CORE_FILE
-- ----------------------------
ALTER TABLE "TZB"."CORE_FILE" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table CORE_FILE_TAG
-- ----------------------------

-- ----------------------------
-- Checks structure for table CORE_FILE_TAG
-- ----------------------------
ALTER TABLE "TZB"."CORE_FILE_TAG" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "TZB"."CORE_FILE_TAG" ADD CHECK ("KEY" IS NOT NULL);
ALTER TABLE "TZB"."CORE_FILE_TAG" ADD CHECK ("VALUE" IS NOT NULL);
ALTER TABLE "TZB"."CORE_FILE_TAG" ADD CHECK ("FILE_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table CORE_FILE_TAG
-- ----------------------------
ALTER TABLE "TZB"."CORE_FILE_TAG" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table CORE_FUNCTION
-- ----------------------------

-- ----------------------------
-- Checks structure for table CORE_FUNCTION
-- ----------------------------
ALTER TABLE "TZB"."CORE_FUNCTION" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "TZB"."CORE_FUNCTION" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table CORE_FUNCTION
-- ----------------------------
ALTER TABLE "TZB"."CORE_FUNCTION" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table CORE_MENU
-- ----------------------------

-- ----------------------------
-- Checks structure for table CORE_MENU
-- ----------------------------
ALTER TABLE "TZB"."CORE_MENU" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table CORE_MENU
-- ----------------------------
ALTER TABLE "TZB"."CORE_MENU" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table CORE_ORG
-- ----------------------------

-- ----------------------------
-- Checks structure for table CORE_ORG
-- ----------------------------
ALTER TABLE "TZB"."CORE_ORG" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "TZB"."CORE_ORG" ADD CHECK ("CODE" IS NOT NULL);
ALTER TABLE "TZB"."CORE_ORG" ADD CHECK ("NAME" IS NOT NULL);
ALTER TABLE "TZB"."CORE_ORG" ADD CHECK ("TYPE" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table CORE_ORG
-- ----------------------------
ALTER TABLE "TZB"."CORE_ORG" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table CORE_ROLE
-- ----------------------------
CREATE INDEX "TZB"."code_idx"
ON "TZB"."CORE_ROLE" ("CODE" ASC)
LOGGING
VISIBLE;

-- ----------------------------
-- Checks structure for table CORE_ROLE
-- ----------------------------
ALTER TABLE "TZB"."CORE_ROLE" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table CORE_ROLE
-- ----------------------------
ALTER TABLE "TZB"."CORE_ROLE" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table CORE_ROLE_FUNCTION
-- ----------------------------

-- ----------------------------
-- Checks structure for table CORE_ROLE_FUNCTION
-- ----------------------------
ALTER TABLE "TZB"."CORE_ROLE_FUNCTION" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table CORE_ROLE_FUNCTION
-- ----------------------------
ALTER TABLE "TZB"."CORE_ROLE_FUNCTION" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table CORE_ROLE_MENU
-- ----------------------------

-- ----------------------------
-- Checks structure for table CORE_ROLE_MENU
-- ----------------------------
ALTER TABLE "TZB"."CORE_ROLE_MENU" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table CORE_ROLE_MENU
-- ----------------------------
ALTER TABLE "TZB"."CORE_ROLE_MENU" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table CORE_USER
-- ----------------------------

-- ----------------------------
-- Checks structure for table CORE_USER
-- ----------------------------
ALTER TABLE "TZB"."CORE_USER" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table CORE_USER
-- ----------------------------
ALTER TABLE "TZB"."CORE_USER" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table CORE_USER_ROLE
-- ----------------------------

-- ----------------------------
-- Checks structure for table CORE_USER_ROLE
-- ----------------------------
ALTER TABLE "TZB"."CORE_USER_ROLE" ADD CHECK ("ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table CORE_USER_ROLE
-- ----------------------------
ALTER TABLE "TZB"."CORE_USER_ROLE" ADD PRIMARY KEY ("ID");
