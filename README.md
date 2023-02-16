## 1. О проекте:

### Проект - UrlShortCut.
Задача: Обеспечить безопасность пользователей путём замены ссылок сайта на ссылки данного сервиса.
Сервис реализуется через REST API.\
**Стек технологий:** Java 17, SpringBoot 2.7.8, REST, Maven 3.5.4, PostgreSQL 14.2, 
Liquibase 3.6.2, JWT 3.4.0, H2, Lombok, JUnit, Spring (Web, Security, Data, Validation)

**Необходимое окружение:**
* Java JDK 17.0.2
* PostgreSQL 14.2 (логин - postgres, пароль - password)
* Maven 3.8.4
* Postman


Функционал (доступен через Postman):
1. **Регистрация сайта**\
   POST запрос на адрес http://localhost:8080/registration c телом JSON объекта:
   {"url" : "http://example.site"}
   в ответе: {registration : true/false, login : УНИКАЛЬНЫЙ_ЛОГИН, password : УНИКАЛЬНЫЙ_ПАРОЛЬ}
   false - означает что данный сайт уже зарегистрирован.
2. **Авторизация через JWT**\
   POST запрос на адрес http://localhost:8080/login c телом JSON объекта:
   {"login" : "УНИКАЛЬНЫЙ_ЛОГИН", "password" : "УНИКАЛЬНЫЙ_ПАРОЛЬ"}
   Сервис возвращает ключ для авторизации, который неодходимо отправить в блоке HEAD, вида:
   Authorization: Bearer s1o2m3e4.....c5o6d7e8
3. **Регистрация URL**\
   После авторизации доступна регистрация ссылки. 
   POST запрос на адрес http://localhost:8080/convert c телом JSON объекта:
   {"url" : "http://example.site/index"}
   в ответе: {code : УНИКАЛЬНЫЙ_КОД}
4. **Переадресация**\
   Доступна без авторизации.
   GET запрос на адрес http://localhost:8080/redirect/УНИКАЛЬНЫЙ_КОД
   перенаправляет на соотвествующий адрес.
5. **Статистика**\
   Доступна авторизованному полоьзователю.
   GET запрос на адрес http://localhost:8080/statistic
   в ответе - количество вызовов каждого адреса.

### 2. Сборка jar через Maven:
```bash
mvn install
```

### 3. Запуск и остановка приложения:

Создать в командной строке базу данных командой (при запросе пароля ввести слово password)
```bash
createdb --username=postgres shortcut
```
Запустить в командной строке из корневой папки проекта командой
```bash
mvn spring-boot:run
```
или
```bash
java -jar target/url_shortcut.jar
```
или запустить jar стандартным способом через менеджер файлов (Проводник, Explorer)

Использование через веб интерфейс по адресу:
<a>http://localhost:8080/

Принудительное завершение программы:
```bash
jps
```
Найти номер процесса(###), относящийся к url_shortcut.jar, затем выполнить завершение:
```bash
taskkill -f /PID ###
```
### 4. Контакты:
Архип(grfoger) aka Тишенков Алексей\
Моб.тел.: +7(967)064-19-20\
[![alt-text](https://img.shields.io/badge/-telegram-grey?style=flat&logo=telegram&logoColor=white)](https://t.me/grfoger)
[![alt-text](https://img.shields.io/badge/@%20email-005FED?style=flat&logo=mail&logoColor=white)](mailto:grfoger@gmail.com)
