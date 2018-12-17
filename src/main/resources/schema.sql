-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.21-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 sourcetracerdb.dict_common 结构
DROP TABLE IF EXISTS `dict_common`;
CREATE TABLE IF NOT EXISTS `dict_common` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DictType` int(11) NOT NULL,
  `Code` varchar(45) NOT NULL,
  `Name` varchar(256) NOT NULL,
  `IsDeleted` bit(1) NOT NULL,
  `Comment` varchar(256) DEFAULT NULL,
  `version` bigint(19) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DictCommonUnique` (`DictType`,`Code`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 sourcetracerdb.dict_member_price 结构
DROP TABLE IF EXISTS `dict_member_price`;
CREATE TABLE IF NOT EXISTS `dict_member_price` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `MemberTypeId` int(10) NOT NULL COMMENT '会员类型iD',
  `Type` int(11) NOT NULL COMMENT '1:平台费；2码量费',
  `Qty` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '数量（月/生码量）',
  `Price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `Comment` varchar(256) DEFAULT NULL,
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0',
  `version` bigint(19) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_dict_member_price2dict_member_type_idx` (`MemberTypeId`),
  CONSTRAINT `FK_dict_member_price2dict_member_type` FOREIGN KEY (`MemberTypeId`) REFERENCES `dict_member_type` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员价格明细表';

-- 数据导出被取消选择。
-- 导出  表 sourcetracerdb.dict_member_type 结构
DROP TABLE IF EXISTS `dict_member_type`;
CREATE TABLE IF NOT EXISTS `dict_member_type` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(256) NOT NULL COMMENT '名称',
  `FreeCodeQty` int(11) NOT NULL DEFAULT '0' COMMENT '免费生码量',
  `HoldTime` int(11) NOT NULL DEFAULT '0' COMMENT '数据保存时长（月）',
  `IsDefault` bit(1) NOT NULL DEFAULT b'0' COMMENT '默认注册会员类型',
  `IsDeleted` bit(1) NOT NULL DEFAULT b'0',
  `Comment` varchar(256) DEFAULT NULL COMMENT '备注',
  `version` bigint(19) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='会员类型';

-- 数据导出被取消选择。
-- 导出  表 sourcetracerdb.dict_region 结构
DROP TABLE IF EXISTS `dict_region`;
CREATE TABLE IF NOT EXISTS `dict_region` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Code` varchar(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Parent_ID` int(11) DEFAULT NULL,
  `Level` int(11) NOT NULL,
  `Order` int(11) NOT NULL,
  `Name_En` varchar(100) NOT NULL,
  `ShortName_En` varchar(10) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `dict_region_p_idx` (`Parent_ID`),
  CONSTRAINT `dict_region_pppp` FOREIGN KEY (`Parent_ID`) REFERENCES `dict_region` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=978 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 sourcetracerdb.dict_system_function 结构
DROP TABLE IF EXISTS `dict_system_function`;
CREATE TABLE IF NOT EXISTS `dict_system_function` (
  `Id` varchar(64) NOT NULL,
  `CssClass` varchar(256) DEFAULT NULL,
  `DisplayName` varchar(256) NOT NULL,
  `FullName` varchar(1000) NOT NULL,
  `FunType` int(11) NOT NULL,
  `Name` varchar(256) NOT NULL,
  `Page` varchar(256) DEFAULT NULL,
  `Seq` varchar(50) DEFAULT NULL,
  `ParentId` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKq3i1j5uiei87rwnui6s5cw3hd` (`ParentId`),
  CONSTRAINT `FKq3i1j5uiei87rwnui6s5cw3hd` FOREIGN KEY (`ParentId`) REFERENCES `dict_system_function` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 sourcetracerdb.sys_member 结构
DROP TABLE IF EXISTS `sys_member`;
CREATE TABLE IF NOT EXISTS `sys_member` (
  `ID` int(4) unsigned zerofill NOT NULL,
  `Region_ID` int(11) NOT NULL COMMENT '区域',
  `Name` varchar(256) NOT NULL COMMENT '企业名称',
  `Name_En` varchar(256) DEFAULT NULL COMMENT '英文名',
  `ShortName` varchar(256) DEFAULT NULL COMMENT '简称',
  `SocialCreditCode` varchar(256) NOT NULL COMMENT '社会信用代码',
  `LegalPerson` varchar(256) NOT NULL COMMENT '法人',
  `Industry_ID` int(11) NOT NULL COMMENT '行业',
  `CompanyType_ID` int(11) NOT NULL COMMENT '企业类型',
  `MemberTypeId` int(10) NOT NULL,
  `Website` varchar(256) DEFAULT NULL COMMENT '企业网站',
  `Requirement` int(11) NOT NULL COMMENT '需求码量',
  `RegAddress` varchar(500) NOT NULL COMMENT '注册地址',
  `ExpressAddress` varchar(500) NOT NULL COMMENT '收货地址',
  `Postcode` varchar(45) NOT NULL,
  `Linkman` varchar(45) NOT NULL,
  `Tel` varchar(45) NOT NULL,
  `Mobile` varchar(45) NOT NULL,
  `Fax` varchar(45) DEFAULT NULL,
  `Email` varchar(256) NOT NULL,
  `QQ` varchar(45) DEFAULT NULL,
  `WetChat` varchar(45) DEFAULT NULL,
  `FromDate` datetime DEFAULT NULL COMMENT '有效期',
  `EndDate` datetime DEFAULT NULL,
  `Status` int(11) NOT NULL DEFAULT '0' COMMENT '0申请中/1已拒绝/2/已通过/3已停用',
  `Comment` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKsys_member_region_idx` (`Region_ID`),
  KEY `FK_sys_member_industry_id_idx` (`Industry_ID`),
  KEY `FK_sys_member_company_type_id_idx` (`CompanyType_ID`),
  KEY `FK_sys_member2dict_member_type_idx` (`MemberTypeId`),
  CONSTRAINT `FK_sys_member2dict_member_type` FOREIGN KEY (`MemberTypeId`) REFERENCES `dict_member_type` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_sys_member_company_type_id` FOREIGN KEY (`CompanyType_ID`) REFERENCES `dict_common` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_sys_member_industry_id` FOREIGN KEY (`Industry_ID`) REFERENCES `dict_common` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_sys_member_region` FOREIGN KEY (`Region_ID`) REFERENCES `dict_region` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业会员';

-- 数据导出被取消选择。
-- 导出  表 sourcetracerdb.sys_role 结构
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `Id` varchar(64) NOT NULL,
  `Comment` varchar(256) DEFAULT NULL,
  `Name` varchar(256) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 sourcetracerdb.sys_role_permission 结构
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE IF NOT EXISTS `sys_role_permission` (
  `Role_ID` varchar(64) NOT NULL,
  `Function_ID` varchar(64) NOT NULL,
  PRIMARY KEY (`Role_ID`,`Function_ID`),
  KEY `FK_RolePermission_Role_idx` (`Role_ID`),
  KEY `FK_RolePermission_SystemFunction_idx` (`Function_ID`),
  CONSTRAINT `FK_RolePermission_Role` FOREIGN KEY (`Role_ID`) REFERENCES `sys_role` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_RolePermission_SystemFunction` FOREIGN KEY (`Function_ID`) REFERENCES `dict_system_function` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 sourcetracerdb.sys_user 结构
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user` (
  `UserId` varchar(64) NOT NULL,
  `Comment` varchar(255) DEFAULT NULL,
  `CreateDate` datetime NOT NULL,
  `Email` varchar(256) DEFAULT NULL,
  `FailedPasswordAnswerAttemptCount` int(11) NOT NULL,
  `FailedPasswordAnswerAttemptWindowStart` datetime NOT NULL,
  `FailedPasswordAttemptCount` int(11) NOT NULL,
  `FailedPasswordAttemptWindowStart` datetime NOT NULL,
  `IsAnonymous` bit(1) NOT NULL,
  `IsApproved` bit(1) NOT NULL,
  `IsLockedOut` bit(1) NOT NULL,
  `LastLockoutDate` datetime NOT NULL,
  `LastLoginDate` datetime NOT NULL,
  `LastPasswordChangedDate` datetime NOT NULL,
  `Password` varchar(128) NOT NULL,
  `PasswordAnswer` varchar(128) DEFAULT NULL,
  `PasswordFormat` int(11) DEFAULT NULL,
  `PasswordQuestion` varchar(256) DEFAULT NULL,
  `PasswordSalt` varchar(128) DEFAULT NULL,
  `QQ` varchar(256) DEFAULT NULL,
  `Tel` varchar(256) DEFAULT NULL,
  `UserName` varchar(256) NOT NULL,
  `UserNameCN` varchar(256) NOT NULL,
  `WebChat` varchar(256) DEFAULT NULL,
  `Member_ID` int(4) unsigned zerofill DEFAULT NULL,
  `IsMemberAdmin` bit(1) DEFAULT NULL COMMENT '是否为企业会员的管理员账户',
  PRIMARY KEY (`UserId`),
  KEY `FK_sys_user_Member_ID_idx_idx` (`Member_ID`),
  CONSTRAINT `FK_sys_user_Member_ID_idx` FOREIGN KEY (`Member_ID`) REFERENCES `sys_member` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 sourcetracerdb.sys_user_in_role 结构
DROP TABLE IF EXISTS `sys_user_in_role`;
CREATE TABLE IF NOT EXISTS `sys_user_in_role` (
  `user_id` varchar(64) NOT NULL,
  `role_id` varchar(64) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK3b46wathry8163aibfsrx4288` (`role_id`),
  CONSTRAINT `FK3b46wathry8163aibfsrx4288` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`Id`),
  CONSTRAINT `FKesm0uump9k5h3nerwi4dq2hmg` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 sourcetracerdb.test_order 结构
DROP TABLE IF EXISTS `test_order`;
CREATE TABLE IF NOT EXISTS `test_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_number` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 sourcetracerdb.test_order_item 结构
DROP TABLE IF EXISTS `test_order_item`;
CREATE TABLE IF NOT EXISTS `test_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3cvq7bmdwj8n1a0qt2qtir20h` (`order_id`),
  KEY `FK7vs5efifrlmjv7fwic986eyi5` (`product_id`),
  CONSTRAINT `FK3cvq7bmdwj8n1a0qt2qtir20h` FOREIGN KEY (`order_id`) REFERENCES `test_order` (`id`),
  CONSTRAINT `FK7vs5efifrlmjv7fwic986eyi5` FOREIGN KEY (`product_id`) REFERENCES `test_product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 sourcetracerdb.test_product 结构
DROP TABLE IF EXISTS `test_product`;
CREATE TABLE IF NOT EXISTS `test_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 sourcetracerdb.test_user 结构
DROP TABLE IF EXISTS `test_user`;
CREATE TABLE IF NOT EXISTS `test_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
