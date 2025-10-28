ExcelNthMinService - сервис для поиска N-го минимального числа из локального Excel файла.
Используется алгоритм QuickSelect.

Инструкция для сборки и запуска:
Команды выполнять в консоли
1) Для сборки выполнить команду: mvn clean package
2) Для запуска выполнить команду: mvn spring-boot:run
3) Можно так же запустить собранный jar командой: java -jar target/excel-nth-min-service-0.0.1-SNAPSHOT.jar

Так же есть вариант запуска через докер (докер файл реализован в проекте):
1) Сборка образа: docker build -t excel-nth-min-service .
2) Запуск контейнера: docker run -p 8080:8080 excel-nth-min-service