! В одном из последних коммитов была удалена папка .idea. В связи с этим требуется перед запуском заново установить SDK в проекте.

Для запуска тестов используется команда: 
`mvn clean test`

Для создания отчетов используется команда: 
`mvn allure:serve`

В классе CustomAllureSelenide переопределен метод afterEvent(). В случае ошибки он дополнительно к скриншоту ошибки добавляет скриншот "перед ошибкой".
