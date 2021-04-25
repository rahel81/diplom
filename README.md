## Запуск приложения:

1. Клонировать репозиторий __`git clone`__
2. Для запуска контейнеров с __MySql__, __PostgreSQL__ и __Node.js__ использовать команду __`docker-compose up -d`__     
* ***Для запуска под MySQL использовать команду***  
      `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar`  
* ***Для запуска под PostgreSQL использовать команду***  
      `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres -jar artifacts/aqa-shop.jar`
 #### Приложение запускается на порту: 8080

## Запуск тестов (Allure):  
* ***Для запуска под MySQL использовать команду***  
    `gradlew -Ddb.url=jdbc:mysql://localhost:3306/app clean test` 
* ***Для запуска под PostgreSQL использовать команду***  
    `gradlew -Ddb.url=jdbc:postgresql://localhost:5432/app clean test` 
    
Для получения отчета Allure необходимо сначала выполнить команду __`gradlew allureReport`__, для подготовки Allure. 
Затем запустить автотесты командой __`gradlew clean build allureReport`__. Для просмотра отчета Allure необходимо 
выполнить команду __`gradlew allureServe`__ и дождаться открытия отчета в браузере.

После окончания тестов завершить работу приложения (Ctrl+C), остановить контейнеры командой __`docker-compose down`__
