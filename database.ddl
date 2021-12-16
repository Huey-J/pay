CREATE TABLE `ledger`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id`       bigint(20) DEFAULT NULL,
    `created_date`  datetime(6) DEFAULT NULL,
    `is_deleted`    bit(1) NOT NULL,
    `memo`          text         DEFAULT NULL,
    `modified_date` datetime(6) DEFAULT NULL,
    `price`         int(11) DEFAULT NULL,
    `title`         varchar(255) DEFAULT NULL,
    `paid_date`     datetime(6) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `email`        varchar(255) DEFAULT NULL,
    `password`     varchar(255) DEFAULT NULL,
    `created_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;