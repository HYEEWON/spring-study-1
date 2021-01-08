## Spring 
* 인프런의 스프링 강의 내용 정리
* 추가적으로 내용 보충

## Contents
- [스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술](#---------------------------mvc--db------)
  * [Spring Boot Basis](#spring,-boot-basis)
## 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술
### Spring, Boot Basis
#### 정적 컨텐츠
1. 웹 브라우저 요청(http://~//test.html)이 들어오면 스프링 부트의 내장 톰켓 서버가 요청을 받음 
2. 스프링에서 요청(test)과 관련된 컨트롤러 탐색
3. 컨트롤러가 없다면 `resoures/static/`에서 관련 html 파일(test.html)을 찾아 웹브라우저에 반환
* `index.html`이 기본 파일
* `resoures/static/`에 없다면 `resources/templates` 탐색
#### MVC와 템플릿 엔진
* MVC
  * Model: 비지니스 로직 처리, 데이터 및 DB와 관련, 데이터 처리 및 변경 등
  * View: 화면과 관련, Controller에서 받은 Model의 결과를 이용해 화면을 제작 -> 화면을 웹 브라우저에 전달하여 출력되도록 함
  * Controller: 비지니스 로직 처리, Model에서 데이터를 받아 View에게 전달, View에서 받은 화면을 웹 서버에 전달
  * `Web Browser` -> 웹 실행 요청 -> `Web Server` -> `Controller` -> 모델 호출 또는 데이터 전달 -> `Model`(<->DB) -> 데이터로 만든 값 객체 -> `Controller` -> 전달 -> `View` -> 화면 -> `Controller` -> 전달 -> `Web Server` -> `Web Browser` 로 출력
* 템플릿 엔진 처리 과정
  1. 웹 브라우저 요청(http://~//test)이 들어오면 스프링 부트의 내장 톰켓 서버가 요청을 받음 
  2. 스프링에서 요청(test)과 관련된 컨트롤러가 Model을 통해 값을 넘김
  3. `viewResolver`가 `resoures/templates/`에서 관련 html 파일(test.html)을 찾아 변환(템플릿 처리)하여 웹브라우저에 반환
#### API
  1. 웹 브라우저 요청(http://~//test)이 들어오면 스프링 부트의 내장 톰켓 서버가 요청을 받음 
  2. 스프링에서 요청(test)과 관련된 컨트롤러가 동작 수행
  3. `@ResponseBody`가 있으면 HttpMessageConverter가 동작 -> 객체: `MappingJackson2HttpMessageConverter`, 문자(열): `StringHttpMessageConverter` 동작
  4. 객체는 JSON, 문자(열)는 문자(열)로 변환하여 웹 브라우저에 반환
