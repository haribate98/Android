-- MySQL Script generated by MySQL Workbench
-- Tue Aug 27 15:18:12 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema racunarske_komponente_shop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema racunarske_komponente_shop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `racunarske_komponente_shop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `racunarske_komponente_shop` ;

-- -----------------------------------------------------
-- Table `racunarske_komponente_shop`.`komponente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `racunarske_komponente_shop`.`komponente` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tip` TEXT NOT NULL,
  `naziv` TEXT NOT NULL,
  `cena` FLOAT NOT NULL,
  `sadrzaj` LONGTEXT NOT NULL,
  `slika` TEXT NOT NULL,
  `kolicina` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO komponente VALUES(0,"Maticna" ,"ASROCK-23155x", 9900, "Maticna stvorena za gaming","https://www.winwin.rs/media/catalog/product/cache/5/image/420x/17f82f742ffe127f42dca9de82fb58b1/1/1/1191194.jpg", 44);
INSERT INTO komponente VALUES(0,"Maticna" ,"GYGABYTE-2455", 29900, "Maticna stvorena za gaming","https://images-na.ssl-images-amazon.com/images/I/91gpOA%2B7CKL._SL1500_.jpg", 44);
INSERT INTO komponente VALUES(0,"Maticna" ,"ASROCK-3330f", 49900, "Maticna stvorena za gaming","data:image/jpeg;base64,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", 44);
INSERT INTO komponente VALUES(0,"Maticna" ,"MSI-B440", 39900, "Maticna stvorena za gaming","https://azcd.harveynorman.com.au/media/catalog/product/cache/21/image/992x558/9df78eab33525d08d6e5fb8d27136e95/m/s/msi-b450m-gaming-plus-motherboard-boxhost.jpg", 44);


INSERT INTO komponente VALUES(0, "Graficka","NVIDIA770-WF-OC", 34999, "Graficka kartica za svakodnevnu uptrebu", "https://www.primeabgb.com/wp-content/uploads/2019/04/Gigabyte-GeForce-GTX-1660-GAMING-6G-Gaming-Graphic-Card-GV-N1660GAMING-6GD.jpg", 41);
INSERT INTO komponente VALUES(0, "Graficka","NVIDIA GTX 1080ti", 74999, "Graficka kartica za prave gejmere", "https://images-na.ssl-images-amazon.com/images/I/818R7E0341L._SX355_.jpg", 41);
INSERT INTO komponente VALUES(0, "Graficka","AMD Vega 7", 44999, "Graficka kartica za prave gejmere", "data:image/jpeg;base64,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", 41);
INSERT INTO komponente VALUES(0, "Graficka","AMD Radeon 7 Series", 24999, "Graficka kartica za svakodnevnu uptrebu", "https://pcper.com/wp-content/uploads/2013/08/567e-amd-radeon-7970-ghz-edition-1000x640.jpg", 41);


INSERT INTO komponente VALUES(0, "Procesor","Intel I7 4770k", 34999, "Procesor za svakodnevnu uptrebu", "https://images-na.ssl-images-amazon.com/images/I/813W0xTYnJL._SL1462_.jpg", 41);
INSERT INTO komponente VALUES(0, "Procesor","AMD FX 6300", 34999, "Procesor za svakodnevnu uptrebu", "https://c.76.my/Malaysia/amd-fx-6300-desktop-processor-3-80ghz-6-cores-14mb-cache-am3-socket-chenchen89-1806-07-F1014320_1.jpg", 41);
INSERT INTO komponente VALUES(0, "Procesor","Intel Xeon W-2175", 34999, "Procesor visokih performansi", "https://static.bhphoto.com/images/images2500x2500/1533745607_1427420.jpg", 41);
INSERT INTO komponente VALUES(0, "Procesor","Intel I9 9900k", 34999, "Procesor za svakodnevnu uptrebu", "https://assets.pcmag.com/media/images/613142-intel-core-i9-9900k-box-3.jpg?thumb=y&width=980&height=753", 41);


-- -----------------------------------------------------
-- Table `racunarske_komponente_shop`.`gotove_konfiguracije`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `racunarske_komponente_shop`.`gotove_konfiguracije` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `naziv` TEXT NOT NULL,
  `maticna_id` INT(11) NOT NULL,
  `graficka_id` INT(11) NOT NULL,
  `procesor_id` INT(11) NOT NULL,
  `cena` FLOAT NOT NULL,
  `slika` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `maticna_idi` (`maticna_id` ASC) INVISIBLE,
  INDEX `graficka_idi` (`graficka_id` ASC) INVISIBLE,
  INDEX `procesor_idi` (`procesor_id` ASC) VISIBLE,
  CONSTRAINT `graficka_fkey`
    FOREIGN KEY (`graficka_id`)
    REFERENCES `racunarske_komponente_shop`.`komponente` (`id`),
  CONSTRAINT `maticna_fkey`
    FOREIGN KEY (`maticna_id`)
    REFERENCES `racunarske_komponente_shop`.`komponente` (`id`),
  CONSTRAINT `procesor_fkey`
    FOREIGN KEY (`procesor_id`)
    REFERENCES `racunarske_komponente_shop`.`komponente` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO gotove_konfiguracije VALUES(0, "AMD Ripper 6300x", 2, 7, 11, 75000, "http://static1.caseking.de/media/image/thumbnail/sipc-910_sipc_910_1g_800x800.jpg");
INSERT INTO gotove_konfiguracije VALUES(0, "Intel Beast 2", 3, 6, 12, 95000, "https://www.extremepc.co.nz/image/cache/catalog/autoEx/174294-1000x1000.jpg");
INSERT INTO gotove_konfiguracije VALUES(0, "Intel Budget", 1, 8, 9, 45000, "https://www.wepc.com/wp-content/uploads/2018/11/best-full-tower-case.jpg");


-- -----------------------------------------------------
-- Table `racunarske_komponente_shop`.`korisnici`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `racunarske_komponente_shop`.`korisnici` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `korisnicko_ime` TEXT NOT NULL,
  `lozinka` TEXT NOT NULL,
  `ime` TEXT NOT NULL,
  `prezime` TEXT NOT NULL,
  `adresa` TEXT NOT NULL,
  `email` TEXT NOT NULL,
  `ulogovan` TINYINT(4) NULL DEFAULT NULL,
  `admin` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO korisnici VALUES(0, "admin", "admin", "Admin", "", "","" ,0 , 1);

-- -----------------------------------------------------
-- Table `racunarske_komponente_shop`.`kupovine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `racunarske_komponente_shop`.`kupovine` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `datum_vreme` DATETIME NOT NULL,
  `id_korisnika` INT(11) NOT NULL,
  `naziv_proizvoda` TEXT NOT NULL,
  `cena` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_korisnika` (`id_korisnika` ASC) VISIBLE,
  CONSTRAINT `key_1`
    FOREIGN KEY (`id_korisnika`)
    REFERENCES `racunarske_komponente_shop`.`korisnici` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;