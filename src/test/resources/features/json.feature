#language: ru

Функция: postApi
  Сценарий: Создание пользователя и добавление новых полей

    Дано объект из файла .json
    Когда отправляем POST запрос с новыми полями объекта
    Тогда проверяем, что полученный response имеет валидные данные по значениям key и value