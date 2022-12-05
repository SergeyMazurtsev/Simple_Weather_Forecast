[![Typing SVG](https://readme-typing-svg.herokuapp.com?size=35&color=C54DF7FF&lines=Клиент+прогноза+погоды)](https://git.io/typing-svg)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

Весь пакет состоит из двух частей:
- Клиентская десктопная часть. *(текущая часть)*
- Серверная API часть;

***

<h3>Серверная часть</h3>
Для работы с текущим клиентом используется [***серверный API***][server].
<br>

***

<h3>Клиентская часть</h3>

Клиентская часть состоит из двух окон:
* Окно выбора типа запроса погоды (Civil и Civil light) и города России
* Окно отображения прогноза погоды

После выбора типа прогноза погоды, клиент отправляет запрос на [***сервер***][server], с соответствующими *headers* (долгота и широта города). 

Полученный ответ от *сервера* сериализуется в DTO и размещается в окне прогноза погоды.

В качестве адресов сервера для запросов указан: ***http://localhost:9090***

В планах доделать работу еще по друм вариантам запроса:
* Astro (7Times)
* Yandex Weather

Так же, необходимо сделать unit тесты на все классы.

[server]: https://github.com/SergeyMazurtsev/Server_Weather_Forecast "Серверная часть"