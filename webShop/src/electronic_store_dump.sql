/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `manufacture` varchar(45) NOT NULL,
  `description` varchar(200) NOT NULL,
  `category` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `category_fk_idx` (`category`),
  CONSTRAINT `category_fk` FOREIGN KEY (`category`) REFERENCES `product_category` (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Asus ROG Strix G531GT-BQ132',900,'Asus','14\'9/RAM 8GB/SSD 256GB/Intel core i5-8300H',1),(2,'HP Pavilion Gaming 15-cx0027ua',850,'HP','17\'\'/RAM 4GB/SSD 512GB/Intel Pentium n3537',1),(3,'Acer Aspire 5 A515-54G-50EQ',845,'Acer','13\'\'/RAM 6GB/SSD 512GB/AMD Ryzen 7-3700U',1),(4,'HP Pavilion Gaming 15-bc504ur',800,'HP','15\'\'/RAM 10GB/SSD 1024GB/Intel core i7-9600A',1),(5,'Lenovo IdeaPad 330-15AST',250,'Lenovo','16\'\'/RAM 16GB/SSD 128GB/AMD Ryzen 6-5200T',1),(6,'Samsung Galaxy S9 Midnight Black',500,'Samsung','6.2\'\'/RAM 4GB/SnapDragon 439/ 5G/Android 10',2),(7,'Samsung Galaxy M31 Blue',300,'Samsung','5.9\'\'/RAM 6GB/SnapDragon 355/ 4G/Android 7',2),(8,'Honor 10 Lite Midnight Black',245,'Honor','6.0\'\'/RAM 4GB/SnapDragon 439/ 5G/Android 10',2),(9,'Huawei P Smart Pro Black',325,'Huawei','5.5\'\'/RAM 8GB/SnapDragon 535/ 5G/Android 10',2),(10,'Apple iPhone Xs 64GB Space Gray',800,'Apple','5.9\'\'/RAM 4GB/A10/ 5G/IOS 12',2),(11,'Samsung Galaxy A10s Black',200,'Samsung','6\'\'/RAM 4GB/SnapDragon 855/ 4G/Android 10',2),(12,'OPPO A5s Black',315,'OPPO','4.5\'\'/RAM 4GB/SnapDragon 555/4G/Android 10',2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
);

/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,'notebook'),(2,'smartphone');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_UNIQUE` (`role`)
);

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (2,'admin'),(1,'user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `role_fk_idx` (`role_id`),
  CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
);

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (47,'Ivan','Ivanov','login1','ivan1@gmail.com','QWJhbmRvbkAxMg==',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;