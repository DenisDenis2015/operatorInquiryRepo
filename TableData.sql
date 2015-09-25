INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('1', 'Ремонт');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('2', 'Замена');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('3', 'Починка');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('4', 'Продажа');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('5', 'Полная замена');

INSERT INTO `db_operator`.`inquiry` (`id`, `topic_id`, `description`, `create_date`, `customer_name`) VALUES ('1', '1', 'обращение', '2001-11-11 11:22' ,'Simple Useravich');
INSERT INTO `db_operator`.`attribute_of_inquiry` (`id`, `inquiry_id`, `name`, `value`) VALUES ('1', '1', 'Город', 'Гродно');
INSERT INTO `db_operator`.`attribute_of_inquiry` (`id`, `inquiry_id`, `name`, `value`) VALUES ('2', '1', 'Телефон', 'мобильный');

INSERT INTO `db_operator`.`inquiry` (`id`, `topic_id`, `description`, `create_date`, `customer_name`) VALUES ('2', '3', 'звонок', '2006-11-11 11:22' ,'Иванов');
INSERT INTO `db_operator`.`attribute_of_inquiry` (`id`, `inquiry_id`, `name`, `value`) VALUES ('3', '2', 'Советская', 'Гродно');

;