ALTER TABLE `ivoterdb`.`voter` 
CHANGE COLUMN `state` `state` VARCHAR(2) NOT NULL ,
CHANGE COLUMN `zipcode` `zipcode` INT(6) NOT NULL ,
CHANGE COLUMN `phone` `phone` INT(11) NOT NULL ;