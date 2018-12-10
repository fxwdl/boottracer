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

-- 正在导出表  sourcetracerdb.dict_common 的数据：~78 rows (大约)
DELETE FROM `dict_common`;
/*!40000 ALTER TABLE `dict_common` DISABLE KEYS */;
INSERT INTO `dict_common` (`ID`, `DictType`, `Code`, `Name`, `IsDeleted`, `Comment`) VALUES
	(1, 0, 'H001', '家具', b'0', ''),
	(2, 0, 'H002', '五金', b'0', ''),
	(3, 0, 'H003', '服装', b'0', ''),
	(4, 0, 'H004', '饰品', b'0', ''),
	(5, 0, 'H005', '锁具', b'0', ''),
	(6, 0, 'H006', '文具', b'0', ''),
	(7, 0, 'H007', '灯具', b'0', ''),
	(8, 0, 'H008', '厨具', b'0', ''),
	(9, 0, 'H009', '礼品', b'0', ''),
	(10, 0, 'H010', '食品', b'0', ''),
	(11, 0, 'H011', '玩具', b'0', ''),
	(12, 0, 'H012', '数码', b'0', ''),
	(13, 0, 'H013', '家电', b'0', ''),
	(14, 0, 'H014', '化妆品', b'0', ''),
	(15, 0, 'H015', '汽车', b'0', ''),
	(16, 0, 'H016', '装修', b'0', ''),
	(17, 0, 'H017', '纺织', b'0', ''),
	(18, 0, 'H018', '制鞋', b'0', ''),
	(19, 0, 'H019', '印刷', b'0', ''),
	(20, 0, 'H020', '音响灯光', b'0', ''),
	(21, 0, 'H021', '运动', b'0', ''),
	(22, 0, 'H022', '建材', b'0', ''),
	(23, 0, 'H023', '造纸', b'0', ''),
	(24, 0, 'H024', '洁具', b'0', ''),
	(25, 0, 'H025', '塑料', b'0', ''),
	(26, 0, 'H026', '电子', b'0', ''),
	(27, 0, 'H027', '皮具', b'0', ''),
	(28, 0, 'H028', '网络媒体', b'0', ''),
	(29, 0, 'H029', '物流', b'0', ''),
	(30, 0, 'H030', '能源', b'0', ''),
	(31, 0, 'H031', '化工', b'0', ''),
	(32, 0, 'H032', '涂料', b'0', ''),
	(33, 0, 'H033', '广电', b'0', ''),
	(34, 0, 'H034', '通信', b'0', ''),
	(35, 0, 'H035', '农业', b'0', ''),
	(36, 0, 'H036', '冶金机械', b'0', ''),
	(37, 0, 'H037', '暖通制冷', b'0', ''),
	(38, 0, 'H038', '安防', b'0', ''),
	(39, 0, 'H039', '消防', b'0', ''),
	(40, 0, 'H040', '防伪', b'0', ''),
	(41, 0, 'H041', '电气', b'0', ''),
	(42, 0, 'H042', '广告', b'0', ''),
	(43, 0, 'H043', '旅游', b'0', ''),
	(44, 0, 'H044', '餐饮', b'0', ''),
	(45, 0, 'H045', '酒店', b'0', ''),
	(46, 0, 'H046', '房地产', b'0', ''),
	(47, 0, 'H047', '医院', b'0', ''),
	(48, 0, 'H048', '教育', b'0', ''),
	(49, 0, 'H049', '电脑', b'0', ''),
	(50, 0, 'H050', '中介', b'0', ''),
	(51, 0, 'H051', '商场', b'0', ''),
	(52, 0, 'H052', '日用品', b'0', ''),
	(53, 0, 'H053', '金融', b'0', ''),
	(54, 0, 'H054', '软件', b'0', ''),
	(55, 0, 'H055', '商务服务', b'0', ''),
	(56, 0, 'H056', '医药', b'0', ''),
	(57, 0, 'H057', '事业机构', b'0', ''),
	(58, 0, 'H058', '美容美发', b'0', ''),
	(59, 0, 'H059', '环保', b'0', ''),
	(60, 0, 'H060', '自行车', b'0', ''),
	(61, 0, 'H061', '税务', b'0', ''),
	(62, 0, 'H062', '仪器仪表', b'0', ''),
	(63, 0, 'H063', '休闲娱乐', b'0', ''),
	(64, 0, 'H064', '生活服务', b'0', ''),
	(65, 0, 'H065', '刀剪', b'0', ''),
	(66, 0, 'H066', '文学', b'0', ''),
	(67, 0, 'H067', '摄影', b'0', ''),
	(68, 0, 'H068', '书画', b'0', ''),
	(69, 0, 'H069', '医疗器械', b'0', ''),
	(70, 0, 'H070', '贸易', b'0', ''),
	(71, 1, 'Q001', '私营企业', b'0', ''),
	(72, 1, 'Q002', '股份有限公司', b'0', ''),
	(73, 1, 'Q003', '国有企业', b'0', ''),
	(74, 1, 'Q004', '集体企业', b'0', ''),
	(75, 1, 'Q005', '联营企业', b'0', ''),
	(76, 1, 'Q006', '有限责任公司', b'0', ''),
	(77, 1, 'Q007', '股份合作企业', b'0', ''),
	(78, 1, 'Q007', '其他企业', b'0', '');
/*!40000 ALTER TABLE `dict_common` ENABLE KEYS */;

-- 正在导出表  sourcetracerdb.dict_region 的数据：~70 rows (大约)
DELETE FROM `dict_region`;
/*!40000 ALTER TABLE `dict_region` DISABLE KEYS */;
INSERT INTO `dict_region` (`ID`, `Code`, `Name`, `Parent_ID`, `Level`, `Order`, `Name_En`, `ShortName_En`) VALUES
	(1, '中国', '中国', NULL, 3, 0, 'Zhong Guo', '2'),
	(7, '210000', '辽宁省', 1, 1, 0, 'Liaoning Sheng', 'LN'),
	(8, '220000', '吉林省', 1, 1, 0, 'Jilin Sheng', 'JL'),
	(71, '210100', '沈阳市', 7, 2, 0, 'Shenyang Shi', 'SHE'),
	(72, '210200', '大连市', 7, 2, 0, 'Dalian Shi', 'DLC'),
	(73, '210300', '鞍山市', 7, 2, 0, 'AnShan Shi', 'ASN'),
	(85, '220100', '长春市', 8, 2, 0, 'Changchun Shi ', 'CGQ'),
	(86, '220200', '吉林市', 8, 2, 0, 'Jilin Shi ', 'JLS'),
	(87, '220300', '四平市', 8, 2, 0, 'Siping Shi', 'SPS'),
	(836, '210101', '市辖区', 71, 3, 0, 'Shixiaqu', '2'),
	(837, '210102', '和平区', 71, 3, 0, 'Heping Qu', 'HEP'),
	(838, '210103', '沈河区', 71, 3, 0, 'Shenhe Qu ', 'SHQ'),
	(839, '210104', '大东区', 71, 3, 0, 'Dadong Qu ', 'DDQ'),
	(840, '210105', '皇姑区', 71, 3, 0, 'Huanggu Qu', 'HGU'),
	(841, '210106', '铁西区', 71, 3, 0, 'Tiexi Qu', 'TXI'),
	(842, '210111', '苏家屯区', 71, 3, 0, 'Sujiatun Qu', 'SJT'),
	(843, '210112', '东陵区', 71, 3, 0, 'Dongling Qu ', 'DLQ'),
	(844, '210113', '沈北新区', 71, 3, 0, 'Xinchengzi Qu', '2'),
	(845, '210114', '于洪区', 71, 3, 0, 'Yuhong Qu ', 'YHQ'),
	(846, '210122', '辽中县', 71, 3, 0, 'Liaozhong Xian', 'LZL'),
	(847, '210123', '康平县', 71, 3, 0, 'Kangping Xian', 'KPG'),
	(848, '210124', '法库县', 71, 3, 0, 'Faku Xian', 'FKU'),
	(849, '210181', '新民市', 71, 3, 0, 'Xinmin Shi', 'XMS'),
	(850, '210201', '市辖区', 72, 3, 0, 'Shixiaqu', '2'),
	(851, '210202', '中山区', 72, 3, 0, 'Zhongshan Qu', 'ZSD'),
	(852, '210203', '西岗区', 72, 3, 0, 'Xigang Qu', 'XGD'),
	(853, '210204', '沙河口区', 72, 3, 0, 'Shahekou Qu', 'SHK'),
	(854, '210211', '甘井子区', 72, 3, 0, 'Ganjingzi Qu', 'GJZ'),
	(855, '210212', '旅顺口区', 72, 3, 0, 'Lvshunkou Qu ', 'LSK'),
	(856, '210213', '金州区', 72, 3, 0, 'Jinzhou Qu', 'JZH'),
	(857, '210224', '长海县', 72, 3, 0, 'Changhai Xian', 'CHX'),
	(858, '210281', '瓦房店市', 72, 3, 0, 'Wafangdian Shi', 'WFD'),
	(859, '210282', '普兰店市', 72, 3, 0, 'Pulandian Shi', 'PLD'),
	(860, '210283', '庄河市', 72, 3, 0, 'Zhuanghe Shi', 'ZHH'),
	(861, '210301', '市辖区', 73, 3, 0, 'Shixiaqu', '2'),
	(862, '210302', '铁东区', 73, 3, 0, 'Tiedong Qu ', 'TED'),
	(863, '210303', '铁西区', 73, 3, 0, 'Tiexi Qu', 'TXL'),
	(864, '210304', '立山区', 73, 3, 0, 'Lishan Qu', 'LAS'),
	(865, '210311', '千山区', 73, 3, 0, 'Qianshan Qu ', 'QSQ'),
	(866, '210321', '台安县', 73, 3, 0, 'Tai,an Xian', 'TAX'),
	(867, '210323', '岫岩满族自治县', 73, 3, 0, 'Xiuyan Manzu Zizhixian', 'XYL'),
	(868, '210381', '海城市', 73, 3, 0, 'Haicheng Shi', 'HCL'),
	(950, '220101', '市辖区', 85, 3, 0, 'Shixiaqu', '2'),
	(951, '220102', '南关区', 85, 3, 0, 'Nanguan Qu', 'NGN'),
	(952, '220103', '宽城区', 85, 3, 0, 'Kuancheng Qu', 'KCQ'),
	(953, '220104', '朝阳区', 85, 3, 0, 'Chaoyang Qu ', 'CYC'),
	(954, '220105', '二道区', 85, 3, 0, 'Erdao Qu', 'EDQ'),
	(955, '220106', '绿园区', 85, 3, 0, 'Lvyuan Qu', 'LYQ'),
	(956, '220112', '双阳区', 85, 3, 0, 'Shuangyang Qu', 'SYQ'),
	(957, '220122', '农安县', 85, 3, 0, 'Nong,an Xian ', 'NAJ'),
	(958, '220181', '九台市', 85, 3, 0, 'Jiutai Shi', '2'),
	(959, '220182', '榆树市', 85, 3, 0, 'Yushu Shi', 'YSS'),
	(960, '220183', '德惠市', 85, 3, 0, 'Dehui Shi', 'DEH'),
	(961, '220201', '市辖区', 86, 3, 0, 'Shixiaqu', '2'),
	(962, '220202', '昌邑区', 86, 3, 0, 'Changyi Qu', 'CYI'),
	(963, '220203', '龙潭区', 86, 3, 0, 'Longtan Qu', 'LTQ'),
	(964, '220204', '船营区', 86, 3, 0, 'Chuanying Qu', 'CYJ'),
	(965, '220211', '丰满区', 86, 3, 0, 'Fengman Qu', 'FMQ'),
	(966, '220221', '永吉县', 86, 3, 0, 'Yongji Xian', 'YOJ'),
	(967, '220281', '蛟河市', 86, 3, 0, 'Jiaohe Shi', 'JHJ'),
	(968, '220282', '桦甸市', 86, 3, 0, 'Huadian Shi', 'HDJ'),
	(969, '220283', '舒兰市', 86, 3, 0, 'Shulan Shi', 'SLN'),
	(970, '220284', '磐石市', 86, 3, 0, 'Panshi Shi', 'PSI'),
	(971, '220301', '市辖区', 87, 3, 0, 'Shixiaqu', '2'),
	(972, '220302', '铁西区', 87, 3, 0, 'Tiexi Qu', 'TXS'),
	(973, '220303', '铁东区', 87, 3, 0, 'Tiedong Qu ', 'TDQ'),
	(974, '220322', '梨树县', 87, 3, 0, 'Lishu Xian', 'LSU'),
	(975, '220323', '伊通满族自治县', 87, 3, 0, 'Yitong Manzu Zizhixian', 'YTO'),
	(976, '220381', '公主岭市', 87, 3, 0, 'Gongzhuling Shi', 'GZL'),
	(977, '220382', '双辽市', 87, 3, 0, 'Shuangliao Shi', 'SLS');
/*!40000 ALTER TABLE `dict_region` ENABLE KEYS */;

-- 正在导出表  sourcetracerdb.dict_system_function 的数据：~2 rows (大约)
DELETE FROM `dict_system_function`;
/*!40000 ALTER TABLE `dict_system_function` DISABLE KEYS */;
INSERT INTO `dict_system_function` (`Id`, `CssClass`, `DisplayName`, `FullName`, `FunType`, `Name`, `Page`, `Seq`, `ParentId`) VALUES
	('2a5c67c2-f888-11e8-8fb3-3c970ea599ca', 'fa fa-book', '通用字典', 'mgn-system-dict_common_list', 1, 'dict_common_list', 'mgn/system/dict_common_list.html', 'Z_01', 'e589bb14-f887-11e8-8fb3-3c970ea599ca'),
	('e589bb14-f887-11e8-8fb3-3c970ea599ca', 'fa fa-dashboard', '系统管理', 'mgn-system', 0, 'system', '#', 'Z', NULL);
/*!40000 ALTER TABLE `dict_system_function` ENABLE KEYS */;

-- 正在导出表  sourcetracerdb.sys_member 的数据：~0 rows (大约)
DELETE FROM `sys_member`;
/*!40000 ALTER TABLE `sys_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_member` ENABLE KEYS */;

-- 正在导出表  sourcetracerdb.sys_role 的数据：~1 rows (大约)
DELETE FROM `sys_role`;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`Id`, `Comment`, `Name`) VALUES
	('841edad4-f7d7-11e8-a03b-3c970ea599ca', NULL, '管理员');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 正在导出表  sourcetracerdb.sys_role_permission 的数据：~2 rows (大约)
DELETE FROM `sys_role_permission`;
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
INSERT INTO `sys_role_permission` (`Role_ID`, `Function_ID`) VALUES
	('841edad4-f7d7-11e8-a03b-3c970ea599ca', '2a5c67c2-f888-11e8-8fb3-3c970ea599ca'),
	('841edad4-f7d7-11e8-a03b-3c970ea599ca', 'e589bb14-f887-11e8-8fb3-3c970ea599ca');
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;

-- 正在导出表  sourcetracerdb.sys_user 的数据：~1 rows (大约)
DELETE FROM `sys_user`;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`UserId`, `Comment`, `CreateDate`, `Email`, `FailedPasswordAnswerAttemptCount`, `FailedPasswordAnswerAttemptWindowStart`, `FailedPasswordAttemptCount`, `FailedPasswordAttemptWindowStart`, `IsAnonymous`, `IsApproved`, `IsLockedOut`, `LastLockoutDate`, `LastLoginDate`, `LastPasswordChangedDate`, `Password`, `PasswordAnswer`, `PasswordFormat`, `PasswordQuestion`, `PasswordSalt`, `QQ`, `Tel`, `UserName`, `UserNameCN`, `WebChat`, `Member_ID`, `IsMemberAdmin`) VALUES
	('25367d3b-f7d7-11e8-a03b-3c970ea599ca', NULL, '2018-12-04 23:06:21', NULL, 0, '2018-12-04 23:06:36', 0, '2018-12-04 23:06:42', b'0', b'1', b'0', '2018-12-04 23:06:59', '2018-12-04 23:07:07', '2018-12-04 23:07:09', '{bcrypt}$2a$10$4ZNTtNTXi3Is4AtxiuKin.P.XTyJ9so7ACJzxU0mZSHWVmGWWOfuS', '', NULL, NULL, NULL, NULL, NULL, 'admin', '系统管理员', NULL, NULL, NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 正在导出表  sourcetracerdb.sys_user_in_role 的数据：~1 rows (大约)
DELETE FROM `sys_user_in_role`;
/*!40000 ALTER TABLE `sys_user_in_role` DISABLE KEYS */;
INSERT INTO `sys_user_in_role` (`user_id`, `role_id`) VALUES
	('25367d3b-f7d7-11e8-a03b-3c970ea599ca', '841edad4-f7d7-11e8-a03b-3c970ea599ca');
/*!40000 ALTER TABLE `sys_user_in_role` ENABLE KEYS */;

-- 正在导出表  sourcetracerdb.test_order 的数据：~1 rows (大约)
DELETE FROM `test_order`;
/*!40000 ALTER TABLE `test_order` DISABLE KEYS */;
INSERT INTO `test_order` (`id`, `order_number`, `version`, `created_at`, `updated_at`, `createdAt`, `updatedAt`) VALUES
	(3, '2018-001', 0, '2018-11-22 12:36:57', '2018-11-22 12:36:57', NULL, NULL);
/*!40000 ALTER TABLE `test_order` ENABLE KEYS */;

-- 正在导出表  sourcetracerdb.test_order_item 的数据：~2 rows (大约)
DELETE FROM `test_order_item`;
/*!40000 ALTER TABLE `test_order_item` DISABLE KEYS */;
INSERT INTO `test_order_item` (`id`, `quantity`, `version`, `order_id`, `product_id`) VALUES
	(5, 10, 0, 3, 5),
	(6, 5, 0, 3, 6);
/*!40000 ALTER TABLE `test_order_item` ENABLE KEYS */;

-- 正在导出表  sourcetracerdb.test_product 的数据：~2 rows (大约)
DELETE FROM `test_product`;
/*!40000 ALTER TABLE `test_product` DISABLE KEYS */;
INSERT INTO `test_product` (`id`, `name`, `version`) VALUES
	(5, '电脑', 0),
	(6, '办公桌', 0);
/*!40000 ALTER TABLE `test_product` ENABLE KEYS */;

-- 正在导出表  sourcetracerdb.test_user 的数据：~2 rows (大约)
DELETE FROM `test_user`;
/*!40000 ALTER TABLE `test_user` DISABLE KEYS */;
INSERT INTO `test_user` (`id`, `age`, `name`) VALUES
	(1, 1, 'a'),
	(2, 2, 'b');
/*!40000 ALTER TABLE `test_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
