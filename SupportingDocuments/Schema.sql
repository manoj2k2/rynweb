CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Code` varchar(45) DEFAULT NULL,
  `Title` varchar(45) DEFAULT NULL,
  `Price` decimal(10,0) DEFAULT NULL,
  `base_currency` varchar(45) DEFAULT NULL,
  `category_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(45) DEFAULT NULL,
  `Age` varchar(45) DEFAULT NULL,
  `Gender` varchar(45) DEFAULT NULL,
  `Division` varchar(45) DEFAULT NULL,
  `Brand` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `attributes` (
  `Attr_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Color_Code` varchar(45) DEFAULT NULL,
  `Size_Code` varchar(45) DEFAULT NULL,
  `Product_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Attr_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
