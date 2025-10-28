ExcelNthMinService - сервис для поиска N-го минимального числа из локального Excel файла.
Используется алгоритм QuickSelect.

Инструкции для сборки и запуска:

Если есть Docker:
(ссылка на хаб https://hub.docker.com/r/basanton/excel-nth-min-service)
1) Стянуть образ командой: docker pull basanton/excel-nth-min-service
2) Запустить контейнер на порте 8080.
3) Использовать http://localhost:8080/swagger-ui/index.html

Или стянуть код с гита и ввести команды в консоли:
1) Для сборки выполнить команду: mvn clean package
2) Для запуска выполнить команду: mvn spring-boot:run
3) Можно так же запустить собранный jar командой: java -jar target/excel-nth-min-service-0.0.1-SNAPSHOT.jar
4) Использовать http://localhost:8080/swagger-ui/index.html
