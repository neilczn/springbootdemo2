database mybatis
CREATE TABLE `blog` (
  `id` int(11) NOT NULL,
  `title` varchar(30) DEFAULT 'My Blog',
  `author_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

database mybatis2
CREATE TABLE `user_info` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8
