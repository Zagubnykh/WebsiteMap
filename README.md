# WebsiteMap

Приложение, которое в многопоточном режиме формирует карту заданного сайта (список ссылок), и записывает её в файл.  

Ссылки на дочерние страницы располагаются в файле с отступами на одну табуляцию относительно родительских. 

В файле находятся ссылки на страницы, размещённые на том же домене.  

В списке нет:  

- ссылок на другие сайты и поддомены,
- ссылок на внутренние элементы страниц.
  
При запросе страниц выдерживаются паузы (с помощью метода sleep() у потока), чтобы сайт не заблокировал доступ приложения. Используются значения от 100 до 150 мс.  

Исключена возможность циклического перебора ссылок. 
