-- 点加班餐sql
CREATE TABLE IF NOT EXISTS `dinner`(
   `id` BIGINT UNSIGNED AUTO_INCREMENT COMMENT '主键ID',
   `user_name` VARCHAR(100) NOT NULL COMMENT '用户名称',
   `number` SMALLINT NOT NULL COMMENT '点餐数量',
   `status` SMALLINT NOT NULL COMMENT '状态 1：吃鸡， 0：不吃',
   `create_date` DATETIME COMMENT '创建时间',
   `update_date` DATETIME COMMENT '更新时间',
   `year` INT NOT NULL COMMENT '年',
   `month` INT NOT NULL COMMENT '月',
   `day` INT NOT NULL COMMENT '日',
   `week` INT NOT NULL	COMMENT '周',
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;