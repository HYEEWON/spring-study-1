## Spring 
* 인프런의 스프링 강의 내용 정리
* 추가적으로 내용 보충

## Contents
- [스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술](#스프링-입문---코드로-배우는-스프링-부트-웹-mvc-db-접근-기술)

## 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술
```
* Java 11
* Spring boot
* Thymeleaf
```
### Spring Boot Basis
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
  3. `@ResponseBody`가 있으면 `HttpMessageConverter`가 동작 -> 객체: `MappingJackson2HttpMessageConverter`, 문자(열): `StringHttpMessageConverter` 동작
  4. 객체는 JSON, 문자(열)는 문자(열)로 변환하여 웹 브라우저에 반환
  * 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러의 반환 타입 정보를 조햅해 `HttpMessageConverter`가 선택됨
### 회원 관리 예제 - 백앤드 개발
  * 일반적인 웹 애플리케이션 계층 구조<br>
    `컨트롤러` -> `서비스` -> `리포지토리` -> `DB`<br>
             └>  └> `도메인` <┘ <br>
    * 서비스: 핵심 비지니스 로직
    * 리포지토리: 도메인 객체를 DB에 저장하고 관리
    * 도메인: 비지니스 도메인 객체, ex) 회원, 주문, 쿠폰 등 DB에 저장되고 관리됨
