План автоматизации тестирования комплексного сервиса 
============

## Введение
Для тестирования предоставлены карты (файл data.json):    
* 4444 4444 4444 4441, status APPROVED  
* 4444 4444 4444 4442, status DECLINED  

Все сценарии выполняются для СУБД:  
* MySql  
* PostgreSQL

Валидными данными при заполнении полей ввода следует считать:  
1.	Номер карты: 16 цифр, в формате **** **** **** ****  
2.	Месяц: 01-12, но не ранее текущего месяца в случае, если указан текущий год  
3.	Год: последние две цифры порядкового номера года, не ранее текущего года, не более 3-х лет от текущего года  
4.	Владелец: Имя и Фамилия на латинском алфавите  
5.	CVC: 3 цифры

## 1.	Автоматизируемые сценарии  
### Кейс 1. Успешная оплата по дебетовой карте.   
  * Оплата по карте со статусом «approved»    
Номер карты 4444 4444 4444 4441, в остальных полях валидные данные. 
*__Ожидаемый результат:__ Сообщение операция одобрена банком. Правильно заполнены поля в базе данных.*  
### Кейс 2. Успешная выдача кредита по данным банковской карты. 
  * Кредит по карте со статусом «approved»    
Номер карты 4444 4444 4444 4441, в остальных полях валидные данные. 
*__Ожидаемый результат:__ Сообщение заявка одобрена банком. Правильно заполнены поля в базе данных.*  
### Кейс 3. Неудачная оплата по дебетовой карте. 
* Сценарий с отклонённой картой  
  * Оплата по карте со статусом «declined»  
Номер карты 4444 4444 4444 4442, в остальных полях валидные данные. 
*__Ожидаемый результат:__ Сообщение об ошибке, в проведении операции отказано. Правильно заполнены поля в базе данных*  
* Сценарии с невалидным вводом данных  
   
   _1. Ввод невалидных данных в поле «Владелец»_    
В поле «Владелец» ввести данные с использованием недопустимых символов, либо букв латинского алфавита, в остальных полях валидные данные. 
*__Ожидаемый результат:__ сообщение об ошибке, в базе данных новая запись отсутствует*    
 
   _2. Пустое поле «Владелец»_  
Оставить поле «Владелец» пустым, в остальных полях валидные данные. 
*__Ожидаемый результат:__ сообщение об обязательности поля. Отсутствие сообщения о том, что заявка одобрена банком. В базе данных новая запись отсутствует*    
 
   _3. Ввод невалидных данных в поле «Номер карты»_  
В поле «Номер карты» ввести данные больше/меньше 16 цифр, в остальных полях валидные данные. 
*__Ожидаемый результат:__ сообщение об ошибке «Неверный формат», в базе данных но-вая запись отсутствует*    

  _4. Пустое поле «Номер карты»_    
Оставить поле «Номер карты» пустым, в остальных полях валидные данные. 
*__Ожидаемый результат:__ сообщение об обязательности поля. Отсутствие сообщения о том, что заявка одобрена банком. В базе данных новая запись отсутствует*     

  _5. Ввод невалидных данных в поле «Месяц»_  
В поле «Месяц» ввести данные больше 12, в остальных полях валидные данные. 
*__Ожидаемый результат:__ сообщение об ошибке «Неверный формат», в базе данных нoвая запись отсутствует*    

  _6. Пустое поле «Месяц»_  
Оставить поле «Месяц» пустым, в остальных полях валидные данные. 
*__Ожидаемый результат:__ сообщение об обязательности поля. Отсутствие сообщения о том, что заявка одобрена банком. В базе данных новая запись отсутствует*    
  
  _7. Ввод невалидных данных в поле «Год»_  
В поле «Год» ввести данные позже/раньше 3-х лет от текущего года, в остальных полях валидные данные. 
*__Ожидаемый результат:__ сообщение об ошибке «Неверный срок действия карты», в базе данных новая запись отсутствует*    
 
  _8. Пустое поле «Год»_  
Оставить поле «год» пустым, в остальных полях валидные данные. 
*__Ожидаемый результат:__ сообщение об обязательности поля. Отсутствие сообщения о том, что заявка одобрена банком. В базе данных новая запись отсутствует*    
  
  _9. Ввод невалидных данных в поле «CVC»_  
В поле «CVC» ввести данные с использованием недопустимых символов, либо букв латинского алфавита, в остальных полях валидные данные. 
*__Ожидаемый результат:__ сообщение об ошибке «Неверный формат», в базе данных новая запись отсутствует*    
  
  _10. Пустое поле «CVC»_  
Оставить поле «CVC» пустым, в остальных полях валидные данные. 
*__Ожидаемый результат:__ сообщение об обязательности поля. Отсутствие сообщения о том, что заявка одобрена банком. В базе данных новая запись отсутствует*    
  
  _11. Оплата по несуществующей карте_  
В поле «Номер карты» ввести 4444 4444 4444 4444, в остальных полях валидные данные. 
*__Ожидаемый результат:__ сообщение об ошибке, банк отказал в проведении операции. В базе данных новая запись отсутствует*    
  
  _12. Все поля заявки пустые_  
Оставить все поля формы пустыми. 
*__Ожидаемый результат:__ у полей "Номер карты", "Месяц/Год", "CVC" появляются сооб-щения об ошибке "Неверный формат", у поля "Владелец" сообщение об ошибке "Поле обязательно для заполнения". Заявка не отправляется. В базе данных новая запись отсутствует*    
### Кейс 4. Неудачная выдача кредита по данным банковской карты.  
* Сценарий с отклонённой картой.    
  * Оплата по карте со статусом «declined»_  
Номер карты 4444 4444 4444 4442, в остальных полях валидные данные. 
*__Ожидаемый результат:__ Сообщение об ошибке, в проведении операции отказано. Правильно заполнены поля в базе данных*    
* Сценарий с невалидным вводом данных  
  * Аналогично п. 1-12 из кейса 3

## 2.	Инструменты для тестирования:  
•	**JUnit 5** - мощный и удобный фреймворк для автотестов  
•	**Java 11** - язык написания автотестов;  
•	**IntelliJ IDEA** - платформа ориентирована на язык Java, обладает интуитивным интер-фейсом, поддерживает все необходимые для тестирования инструменты;   
•	**Gradle** - система автоматизации сборки и управления зависимостями;  
•	***Selenide** - так как работаем с веб-страницей и ищем появившиеся значения с помощью html и css, создание Page Objects, заполнение формы через веб-интерфейс  
•	**Lombok**- библиотека для сокращения количества шаблонного кода, для объявления локальной переменной вместо указания реального типа;   
•	**Faker** - генерация недостающих данных для тестирования  
•	**RestAssured** - отправка запросов симулятору банковских сервисов, проверка того, что он принимает запросы и генерирует ответы,
хорошо подходит для проверки структуры ответов  
•	**Docker** - для развертывания и запуска приложения в контейнере. Можно одновремен-но запускать на одном компьютере несколько контейнеров, при этом будет потреб-ляться меньше ресурсов, чем для виртуализации. Удобно настраивать контейнеры через Dockerfile, по которому другие члены команды могут запустить точно такой же контейнер  
•	**GitHub** - для хранения проекта, код легко скачать и запустить, код доступен для совместного использования и разработки, имеется система CI  
•	**Allure** - используем для наглядного изображения прохождения тестов и ошибок  
•	**СУБД MySQL и PostgreSQL** - требование задачи  

## 3.	Возможные риски:
1.	Техническая сложность настройки окружения. Для работы приложения нужно использовать две разных базы данных MySQL и PostgreSQL, а также развернуть симулятор платежной системы. Нужно разобраться с тем как он работает, как взаимодей-ствует с нашими БД и ОС. Поэтому в ходе настройки окружения могут возникнуть сложности, требующие дополнительных временных затрат  
2.	Комплексный характер тестирования. Потребуется больше времени на дополнительные проверки и оформление баг-репортов  
3.	Могут возникнуть сложности с поиском элементов на странице (неуникальные идентификаторы)  
4.	Реальная система и реальные данные скорее всего будут отличаться от SUT и тестовых данных, в таком случае автоматизация может быть бессмысленна  

## 4.	Интервальная оценка с учётом рисков:

Написание плана – 6 часов  
Настройка тестового окружения – 10  
Написание и отладка автотестов - 48  
Проведение тестов  - 4  
Устранение замечаний - 20  
Подготовка отчета по результатам автоматизированного тестирования - 12  
**Итого с учетом рисков: 100 часов**
       
## 5. План сдачи работ:
•	авто-тесты - 24.04.2021г.  
•	результаты прогона авто-тестов - 30.04.2021г.  
•	отчёт по автоматизации - 04.05.2021г.  
