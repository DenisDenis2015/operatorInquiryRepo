INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('1', '������');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('2', '������');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('3', '�������');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('4', '�������');
INSERT INTO `db_operator`.`topic` (`id`, `name`) VALUES ('5', '������ ������');
INSERT INTO `db_operator`.`attribute_of_inquiry` (`id`, `model`, `adress`, `city`) VALUES ('1', '����', '���������', '������');
INSERT INTO `db_operator`.`inquiry` (`id`, `topic_id`, `attribute_of_inquiry_id`, `description`, `create_date`, `customer_name`) VALUES ('1', '1', '1', '���������', '2001-11-11' ,'Simple Useravich');

INSERT INTO `db_operator`.`attribute_of_inquiry` (`id`, `model`, `adress`, `city`) VALUES ('2', '���������', '����������', '������');
INSERT INTO `db_operator`.`inquiry` (`id`, `topic_id`, `attribute_of_inquiry_id`, `description`, `create_date`, `customer_name`) VALUES ('2', '2', '2', '��������, ��������� ����� ��� ����� ������', '2012-4-11' ,'Petrov');

INSERT INTO `db_operator`.`attribute_of_inquiry` (`id`, `model`, `adress`, `city`) VALUES ('3', '�������', '����� ������', '������');
INSERT INTO `db_operator`.`inquiry` (`id`, `topic_id`, `attribute_of_inquiry_id`, `description`, `create_date`, `customer_name`) VALUES ('3', '3', '3', '������� � ������, �������� �����', '2012-06-11' ,'Ivanov');

INSERT INTO `db_operator`.`attribute_of_inquiry` (`id`, `model`, `adress`, `city`) VALUES ('4', '�����', '��������', '������');
INSERT INTO `db_operator`.`inquiry` (`id`, `topic_id`, `attribute_of_inquiry_id`, `description`, `create_date`, `customer_name`) VALUES ('4', '4', '4', '��������� ���', '2012-06-11' ,'Romorov');