# Spring 
* [인프런](https://www.inflearn.com)의 스프링 강의 내용 정리
* 추가적으로 내용 보충

## Contents
* [Spring과 Spring Boot](#spring과-spring-boot)
* [스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술](https://github.com/HYEEWON/spring-study/blob/master/_text/%5B1%5D%EC%8A%A4%ED%94%84%EB%A7%81%EC%9E%85%EB%AC%B8.md)
  * Spring Boot Basis
  * 회원 관리 예제 - 백앤드 개발
  * 스프링 빈과 의존관계
  * 웹 MVC 개발 - 회원 관리 예제
  * 스프링DB 접근 기술
  * Aspect Oriented Programming(AOP, 관점 지향 프로그래밍)
* [REST](#rest)
* [Spring Boot를 이용한 RESTful Web Services 개발](https://github.com/HYEEWON/spring-study/blob/master/_text/%5B2%5DRESTful%EA%B0%9C%EB%B0%9C.md)

## Spring과 Spring Boot
### Spring
* 자바 프레임워크
* 특징
  * 경량 컨테이너
  * IoC(Invertion of Control: 제어의 역전): 제어권이 개발자가 아닌 컨테이너에 있음
  * DI(Dependency Injection: 의존성 주입): 객체간의 의존성을 외부에서 주입
  * AOP(Aspect-Oriented Programming: 관점지향 프로그래밍)
* 단점
  * 환경 설정이 복잡함
### Spring Boot 정의
* 스프링의 환경 설정 최소화 -> 생산성 향상
### Spring과 Spring Boot의 차이
* 스프링 부트는 Embeded Tomcat 사용(스프링 부트는 Tomcat이 내부에 포함)
* Starter를 통한 Dependency 자동화
* XML 설정 필요 없음
### Spring Boot Starter
* 의존성 자동화
* `build.gradle`에 의존성 추가 = `spring-boot-starter-*`

### REST
#### 정의
* 자원을 이름(표현)으로 구분하여 자원의 상태(정보)를 주고 받는 것
* 자원의 표현에 의한 상태 전달
* `HTTP URI`를 통해 자원을 명시하고 `HTTP Method(POST, GET, PUT, DELETE)`를 통해 해당 자원에 대한 `CRUD` 연산을 적용
  * Create: POST, Read: GET, Update: PUT, Delete: DELETE
* 웹 사이트의 이미지, 텍스트, DB 등의 모든 자원에 고유한 ID인 HTTP URI를 부여
* HTTP 프로토콜의 인프라를 그대로 사용하므로 REST API 사용을 위한 별도의 인프라를 구축할 필요가 없음
#### 구성 요소
* 자원: URI
  * 모든 자원에는 고유의 `ID(HTTP URI)` 존재
* 행위: HTTP Method(POST, GET, PUT, DELETE)
* 표현: JSON, XML 등
#### 특징
* 서버-클라이언트 구조: 서버가 자원을 소유, 클라이언트가 자원을 요청
* Stateless(무상태)
  * 클라이언트의 Context(쿠키, 세션)를 서버에 저장하지 않음
  * 서버는 각 요청을 별개로 처리 -> 요청들이 서로에 영향X
* Cacheable
* 계층화
#### RESTful
* REST 아키텍처를 구현하는 웹 서비스
* REST 원리를 따르는 시스템
* 목적: 이해하기 쉽고 사용하기 쉬운 REST API 제작, 일관성을 통한 이해도 및 호환성 향상
* RESTful하지 않은 것: CRUD를 모두 POST로 처리, Route에 Resource, ID외의 정보가 사용되는 경우