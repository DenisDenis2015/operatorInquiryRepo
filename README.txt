# operatorInquiry

1.В mySQL запускаем скрипт для создания структуры данных CreateTable.sql затем TableData.sql

2.Пароль и имя для входа в БД должен быть test и test соответственно,
если у вас другие, все легко настраивается в файле operatorInquiry/src/main/resources/application.properties

3.Для запуска я использую TomCat 8.0, необходимо задеплоить два приложения. 
operatorInquiry - сервер с данными.
operatorClientService - веб часть, общается с севером по RestFul, отвечает по адрессу
http://localhost:8080/operatorClientService/

4.Проект был на писан по заданию рассположеном в файле "Тестовое задание на позицию Java.DOCX"



