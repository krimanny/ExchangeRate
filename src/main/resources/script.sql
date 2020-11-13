CREATE TABLE tbl_base_rates (
    `base_id` bigint NOT NULL AUTO_INCREMENT,
    `base` varchar(255) DEFAULT NULL,
    `date_acquired` date DEFAULT (CURRENT_DATE),
    `rate_from` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`base_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tbl_exchange_rates` (
  `rate_id` bigint NOT NULL AUTO_INCREMENT,
  `rate` double(17,10) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `base_id` bigint NOT NULL,
  PRIMARY KEY (`rate_id`),
  KEY FK_base_rate (base_id),
  CONSTRAINT FK_base_rate FOREIGN KEY (base_id) REFERENCES tbl_base_rates (base_id)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET foreign_key_checks = 0;