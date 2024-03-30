#language: ru

Функция: getApi
  Сценарий: Сравнение местонахождения и расы двух персонажей

    Когда отправляем GET запрос с именем "Morty" и получаем его id
    Также отправляем GET запрос с id Морти и получаем его URL
    Также отправляем GET запрос с URL Морти и получаем данные о местонахождении Морти
    Также отправляем GET запрос с URL Морти и получаем данные о расе Морти

    Когда отправляем GET запрос с id Морти и получаем URL его последнего эпизода
    Также отправляем GET запрос с URL последнего эпизода Морти и получаем URL последнего персонажа в этом эпизоде
    Также отправляем GET запрос с URL последнего персонажа и получаем данные о его местонахождении
    Также отправляем GET запрос с URL последнего персонажа и получаем данные о его расе

    Тогда проверяем, что раса Морти и последнего персонажа совпадают
    Тогда проверяем, что местонахождение Морти и последнего персонажа не совпадают