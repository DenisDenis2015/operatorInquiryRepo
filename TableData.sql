INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('1', 'Ремонт');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('2', 'Замена');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('3', 'Починка');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('4', 'Продажа');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('5', 'Полная замена');
INSERT INTO `db_operator`.`attribute_of_inquiry` (`id`, `model`, `adress`, `city`) VALUES ('1', 'Сони', 'Советская', 'Гродно');
INSERT INTO `db_operator`.`inquiry` (`id`, `topic_id`, `attribute_of_inquiry_id`, `description`, `create_date`, `customer_name`) VALUES ('1', '1', '1', 'обращение', '2001-11-11' ,'Simple Useravich');

INSERT INTO `db_operator`.`attribute_of_inquiry` (`id`, `model`, `adress`, `city`) VALUES ('2', 'Панасоник', 'Держинсого', 'Гродно');
INSERT INTO `db_operator`.`inquiry` (`id`, `topic_id`, `attribute_of_inquiry_id`, `description`, `create_date`, `customer_name`) VALUES ('2', '2', '2', 'починить, позвонить когда все будет готово', '2012-4-11' ,'Petrov');

INSERT INTO `db_operator`.`attribute_of_inquiry` (`id`, `model`, `adress`, `city`) VALUES ('3', 'Самсунг', 'Карла Маркса', 'Гродно');
INSERT INTO `db_operator`.`inquiry` (`id`, `topic_id`, `attribute_of_inquiry_id`, `description`, `create_date`, `customer_name`) VALUES ('3', '3', '3', 'завезти в ремонт, поменять плату', '2012-06-11' ,'Ivanov');

INSERT INTO `db_operator`.`attribute_of_inquiry` (`id`, `model`, `adress`, `city`) VALUES ('4', 'Нокиа', 'Троецкая', 'Гродно');
INSERT INTO `db_operator`.`inquiry` (`id`, `topic_id`, `attribute_of_inquiry_id`, `description`, `create_date`, `customer_name`) VALUES ('4', '4', '4', 'сломалось все', '2012-06-11' ,'Romorov');