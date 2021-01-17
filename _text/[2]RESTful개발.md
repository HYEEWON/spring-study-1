# Spring Boot를 이용한 RESTful Web Services 개발

## Web Service & Web Application
### 마이크로 서비스 아키텍처
* 하나의 서비스를 작게 나누어 개발
* 모든 요소가 독립적
### Web Service와 Web Application
* Web Service: 서로 다른 기기들이 네트워크 상에서 통신하기 위한 시스템
* Web Application: Web Browser에 의해 실행되는 프로그램
* Web Application -> `Request` -> Web Service
* Web Service <- `Response` <- Web Application
* `JSON`을 주로 사용
### SOAP(Simple Object Access Protocol)
* `XML` 기반의 메시지 전송을 위한 서비스
* 메시지의 구조가 복잡해 오버헤드 존재
### RESTful
* `REST API`를 제공하는 웹 서비스
#### REST(REpresentational State Transfer)
* Resource의 Representation에 의한 `상태` 전달
* HTTP Method를 통해 자원을 처리 
  * HTTP: HTTP Method(GET, PUT, POST, DELETE) + HTTP Status Code(200, 404 등)