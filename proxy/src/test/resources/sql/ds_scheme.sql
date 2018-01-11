CREATE TABLE `test_user` (
  `id`          BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `user_name`   VARCHAR(32) NOT NULL,
  `age`         SMALLINT(4)          DEFAULT NULL,
  `email`       VARCHAR(32)          DEFAULT NULL,
  `create_time` BIGINT(20)  NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_name` (`user_name`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8