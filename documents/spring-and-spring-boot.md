# Spring과 Spring Boot

<br>

## Spring
* 자바 프레임워크
* 특징
  * 경량 컨테이너
  * IoC(Invertion of Control: 제어의 역전): 제어권이 개발자가 아닌 컨테이너에 있음
  * DI(Dependency Injection: 의존성 주입): 객체간의 의존성을 외부에서 주입
  * AOP(Aspect-Oriented Programming: 관점지향 프로그래밍)
* 단점
  * 환경 설정이 복잡함

<br>

## Spring Boot 정의
* 스프링의 환경 설정 최소화 -> 생산성 향상

<br>

## Spring과 Spring Boot의 차이
* 스프링 부트는 Embeded Tomcat 사용(스프링 부트는 Tomcat이 내부에 포함)
* Starter를 통한 Dependency 자동화
* XML 설정 필요 없음

<br>

## Spring Boot Starter
* 의존성 자동화
* `build.gradle`에 의존성 추가 = `spring-boot-starter-*`