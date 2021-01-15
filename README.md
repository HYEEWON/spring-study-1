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
* Spring Boot를 이용한 RESTful Web Services 개발

## Spring 과 Spring Boot
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