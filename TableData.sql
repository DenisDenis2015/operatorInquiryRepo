INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('1', '������');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('2', '������');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('3', '�������');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('4', '�������');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('5', '������ ������');

INSERT INTO `db_operator`.`inquiry` (`id`, `topic_id`, `description`, `create_date`, `customer_name`) VALUES ('1', '1', '���������', '2001-11-11 11:22' ,'Simple Useravich');
INSERT INTO `db_operator`.`attribute_of_inquiry` (`id`, `inquiry_id`, `name`, `value`) VALUES ('1', '1', '�����', '������');
INSERT INTO `db_operator`.`attribute_of_inquiry` (`id`, `inquiry_id`, `name`, `value`) VALUES ('2', '1', '�������', '���������');

INSERT INTO `db_operator`.`inquiry` (`id`, `topic_id`, `description`, `create_date`, `customer_name`) VALUES ('2', '3', '������', '2006-11-11 11:22' ,'������');
INSERT INTO `db_operator`.`attribute_of_inquiry` (`id`, `inquiry_id`, `name`, `value`) VALUES ('3', '2', '���������', '������');

;