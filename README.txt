# operatorInquiry

1.В mySQL запускаем скрипт CreateTable.sql затем TableData.sql
2.Пароль и имя для входа в БД должен быть test и test соответственно,
если у вас другие, все легко настраивается в файле application.properties
3.Для запуска я использую TomCat, необходимо задеплоить два приложения. 
operatorInquiry - сервер с данными.
operatorClientService - веб часть, общается с севером по RestFull, отвечает по адрессу
http://localhost:8080/operatorClientService/



