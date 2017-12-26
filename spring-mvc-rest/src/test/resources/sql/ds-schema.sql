CREATE TABLE IF NOT EXISTS `user` (
  `uid`      BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(32) NOT NULL COMMENT '用户名',
  `password` VARCHAR(64) NOT NULL COMMENT '密码，demo未加密',
  PRIMARY KEY (`uid`),
  UNIQUE KEY uk_username(username)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;