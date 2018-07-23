DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `mp_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openId` varchar(32) DEFAULT NULL COMMENT '用户ID，直接使用微信的openId',
  `nickName` varchar(64) DEFAULT NULL COMMENT '用户昵称',
  `unionId` varchar(32) DEFAULT NULL COMMENT '微信的unionId标识',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公众号用户信息表';

