-- 节点定义
CREATE TABLE `crm_promotion_point_def` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '节点名称',
  `user_scope_key` varchar(100) NOT NULL COMMENT '用户选择',
  `execute_key` varchar(100) NOT NULL COMMENT '执行逻辑',
  `type` smallint(6) NOT NULL COMMENT '节点类型',
  `status` smallint(6) NOT NULL COMMENT '状态 1：可用， 0：不可用',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 节点
CREATE TABLE `crm_promotion_point` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `def_id` bigint(20) unsigned NOT NULL COMMENT '节点定义ID',
  `name` varchar(100) NOT NULL COMMENT '节点名称',
  `execute_parame_json` varchar(100) DEFAULT '' COMMENT '执行逻辑',
  `user_scope_parame_json` varchar(100) DEFAULT NULL COMMENT '参数',
  `type` smallint(6) NOT NULL COMMENT '节点类型',
  `status` smallint(6) NOT NULL COMMENT '状态 1：可用， 0：不可用',
  `extra_json` varchar(100) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;