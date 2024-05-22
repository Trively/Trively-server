-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enjoytrip` DEFAULT CHARACTER SET utf8 ;
USE `enjoytrip` ;

-- -----------------------------------------------------
-- Table `enjoytrip`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`member` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`member` (
  `member_id` BIGINT NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`board` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`board` (
  `board_id` BIGINT NOT NULL AUTO_INCREMENT,
  `board_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`board_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`post` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`post` (
  `post_id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  `hit` BIGINT NOT NULL,
  `board_id` BIGINT NOT NULL,
  `member_id` BIGINT NOT NULL,
  PRIMARY KEY (`post_id`),
  INDEX `fk_post_board_idx` (`board_id` ASC) VISIBLE,
  INDEX `fk_post_member1_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_post_board`
    FOREIGN KEY (`board_id`)
    REFERENCES `enjoytrip`.`board` (`board_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `enjoytrip`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`post_image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`post_image` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`post_image` (
  `image_id` BIGINT NOT NULL AUTO_INCREMENT,
  `image` VARCHAR(255) NOT NULL,
  `post_id` BIGINT NOT NULL,
  PRIMARY KEY (`image_id`),
  INDEX `fk_post_image_post1_idx` (`post_id` ASC) VISIBLE,
  CONSTRAINT `fk_post_image_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `enjoytrip`.`post` (`post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`comment` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`comment` (
  `comment_id` BIGINT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(2000) NOT NULL,
  `create_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  `parent_id` BIGINT NOT NULL,
  `post_id` BIGINT NOT NULL,
  `member_id` BIGINT NOT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `fk_comment_comment1_idx` (`parent_id` ASC) VISIBLE,
  INDEX `fk_comment_post1_idx` (`post_id` ASC) VISIBLE,
  INDEX `fk_comment_member1_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_comment_comment1`
    FOREIGN KEY (`parent_id`)
    REFERENCES `enjoytrip`.`comment` (`comment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `enjoytrip`.`post` (`post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `enjoytrip`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`post_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`post_like` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`post_like` (
  `member_id` BIGINT NOT NULL,
  `post_id` BIGINT NOT NULL,
  PRIMARY KEY (`member_id`, `post_id`),
  INDEX `fk_post_like_post1_idx` (`post_id` ASC) VISIBLE,
  CONSTRAINT `fk_post_like_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `enjoytrip`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_like_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `enjoytrip`.`post` (`post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`post_scrap`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`post_scrap` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`post_scrap` (
  `member_id` BIGINT NOT NULL,
  `post_id` BIGINT NOT NULL,
  `create_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (`member_id`, `post_id`),
  INDEX `fk_post_scrap_member1_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_post_scrap_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `enjoytrip`.`post` (`post_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_scrap_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `enjoytrip`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`message_room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`message_room` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`message_room` (
  `room_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  `member_id` BIGINT NOT NULL,
  PRIMARY KEY (`room_id`),
  INDEX `fk_message_room_member1_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_message_room_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `enjoytrip`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`message` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`message` (
  `message_id` BIGINT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT current_timestamp,
  `room_id` BIGINT NOT NULL,
  `member_id` BIGINT NOT NULL,
  PRIMARY KEY (`message_id`),
  INDEX `fk_message_message_room1_idx` (`room_id` ASC) VISIBLE,
  INDEX `fk_message_member1_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_message_message_room1`
    FOREIGN KEY (`room_id`)
    REFERENCES `enjoytrip`.`message_room` (`room_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `enjoytrip`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`sido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`sido` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`sido` (
  `sido_code` INT NOT NULL,
  `sido_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`sido_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`attraction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`attraction` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`attraction` (
  `attraction_id` BIGINT NOT NULL AUTO_INCREMENT,
  `type_id` BIGINT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NULL,
  `image1` VARCHAR(255) NULL,
  `image2` VARCHAR(255) NULL,
  `latitude` DOUBLE NULL,
  `longitude` DOUBLE NULL,
  `sido_code` INT NOT NULL,
  PRIMARY KEY (`attraction_id`, `sido_code`),
  INDEX `fk_attraction_sido1_idx` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `fk_attraction_sido1`
    FOREIGN KEY (`sido_code`)
    REFERENCES `enjoytrip`.`sido` (`sido_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`planlist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`planlist` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`planlist` (
  `planlist_id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP NULL DEFAULT current_timestamp,
  `member_id` BIGINT NOT NULL,
  PRIMARY KEY (`planlist_id`),
  INDEX `fk_planlist_member1_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `fk_planlist_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `enjoytrip`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`plan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`plan` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`plan` (
  `plan_id` BIGINT NOT NULL AUTO_INCREMENT,
  `plan_date` TIMESTAMP NULL,
  `attraction_id` BIGINT NOT NULL,
  `planlist_id` BIGINT NOT NULL,
  PRIMARY KEY (`plan_id`),
  INDEX `fk_plan_attraction1_idx` (`attraction_id` ASC) VISIBLE,
  INDEX `fk_plan_planlist1_idx` (`planlist_id` ASC) VISIBLE,
  CONSTRAINT `fk_plan_attraction1`
    FOREIGN KEY (`attraction_id`)
    REFERENCES `enjoytrip`.`attraction` (`attraction_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_plan_planlist1`
    FOREIGN KEY (`planlist_id`)
    REFERENCES `enjoytrip`.`planlist` (`planlist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `enjoytrip`.`sido` VALUES (0,'기타'), (1,'서울'),(2,'인천'),(3,'대전'),(4,'대구'),(5,'광주'),(6,'부산'),(7,'울산'),(8,'세종특별자치시'),(31,'경기도'),(32,'강원도'),(33,'충청북도'),(34,'충청남도'),(35,'경상북도'),(36,'경상남도'),(37,'전라북도'),(38,'전라남도'),(39,'제주도');
INSERT INTO `board` (board_name)
VALUES
('서울'),
('인천'),
('대전'),
('대구'),
('광주'),
('부산'),
('울산'),
('세종특별자치시'),
('경기도'),
('강원도'),
('충청북도'),
('충청남도'),
('경상북도'),
('경상남도'),
('전라북도'),
('전라남도'),
('제주도');
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
