SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `new` DEFAULT CHARACTER SET utf8 ;
USE `new` ;

-- -----------------------------------------------------
-- Table `new`.`Author`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `new`.`Author` (
  `author_id` INT(10) NOT NULL AUTO_INCREMENT ,
  `firstname` VARCHAR(45) NULL DEFAULT NULL ,
  `lastname` VARCHAR(45) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `photo` VARCHAR(255) NULL ,
  `price` DECIMAL(6,2) NULL ,
  PRIMARY KEY (`author_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new`.`UserGroup`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `new`.`UserGroup` (
  `userGroup_id` INT(10) NOT NULL AUTO_INCREMENT ,
  `type` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`userGroup_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new`.`User`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `new`.`User` (
  `usr_id` INT(10) NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(45) NULL DEFAULT NULL ,
  `password` VARCHAR(45) NULL DEFAULT NULL ,
  `name` VARCHAR(50) NULL DEFAULT NULL ,
  `group_id` INT(10) NULL DEFAULT NULL ,
  PRIMARY KEY (`usr_id`) ,
  INDEX `User_fk1` (`group_id` ASC) ,
  CONSTRAINT `User_fk1`
    FOREIGN KEY (`group_id` )
    REFERENCES `new`.`UserGroup` (`userGroup_id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new`.`Genre`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `new`.`Genre` (
  `genre_id` INT(10) NOT NULL AUTO_INCREMENT ,
  `type` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`genre_id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new`.`Distributor`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `new`.`Distributor` (
  `distr_id` INT(10) NOT NULL AUTO_INCREMENT ,
  `url` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`distr_id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new`.`Book`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `new`.`Book` (
  `book_id` INT(10) NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NULL DEFAULT NULL ,
  `autor_id` INT(10) NULL DEFAULT NULL ,
  `user_id` INT(10) NULL DEFAULT NULL ,
  `date_pub` TIMESTAMP NULL DEFAULT NULL ,
  `genre_id` INT(10) NULL DEFAULT NULL ,
  `downloads_cnt` INT(5) NULL DEFAULT NULL ,
  `review_cnt` INT(5) NULL DEFAULT NULL ,
  `pages_cnt` INT(5) NULL DEFAULT NULL ,
  `description` LONGTEXT NULL DEFAULT NULL ,
  `distr_id` INT(10) NULL DEFAULT NULL ,
  `cover` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`book_id`) ,
  INDEX `Book_fk1` (`autor_id` ASC) ,
  INDEX `Book_fk2` (`user_id` ASC) ,
  INDEX `Book_fk3` (`genre_id` ASC) ,
  INDEX `Book_fk4` (`distr_id` ASC) ,
  CONSTRAINT `Book_fk1`
    FOREIGN KEY (`autor_id` )
    REFERENCES `new`.`Author` (`author_id` ),
  CONSTRAINT `Book_fk2`
    FOREIGN KEY (`user_id` )
    REFERENCES `new`.`User` (`usr_id` ),
  CONSTRAINT `Book_fk3`
    FOREIGN KEY (`genre_id` )
    REFERENCES `new`.`Genre` (`genre_id` ),
  CONSTRAINT `Book_fk4`
    FOREIGN KEY (`distr_id` )
    REFERENCES `new`.`Distributor` (`distr_id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new`.`Comment`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `new`.`Comment` (
  `comm_id` INT(10) NOT NULL AUTO_INCREMENT ,
  `user_id` INT(10) NULL DEFAULT NULL ,
  `book_id` INT(10) NULL DEFAULT NULL ,
  `date` TIMESTAMP NULL DEFAULT NULL ,
  PRIMARY KEY (`comm_id`) ,
  INDEX `Comment_fk1` (`user_id` ASC) ,
  INDEX `Comment_fk2` (`book_id` ASC) ,
  CONSTRAINT `Comment_fk1`
    FOREIGN KEY (`user_id` )
    REFERENCES `new`.`User` (`usr_id` ),
  CONSTRAINT `Comment_fk2`
    FOREIGN KEY (`book_id` )
    REFERENCES `new`.`Book` (`book_id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `new`.`PurchasedBook`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `new`.`PurchasedBook` (
  `pur_id` INT(10) NOT NULL AUTO_INCREMENT ,
  `user_id` INT(10) NULL DEFAULT NULL ,
  `book_id` INT(10) NULL DEFAULT NULL ,
  `date` TIMESTAMP NULL DEFAULT NULL ,
  PRIMARY KEY (`pur_id`) ,
  INDEX `PurchasedBook_fk1` (`user_id` ASC) ,
  INDEX `PurchasedBook_fk2` (`book_id` ASC) ,
  CONSTRAINT `PurchasedBook_fk1`
    FOREIGN KEY (`user_id` )
    REFERENCES `new`.`User` (`usr_id` ),
  CONSTRAINT `PurchasedBook_fk2`
    FOREIGN KEY (`book_id` )
    REFERENCES `new`.`Book` (`book_id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
