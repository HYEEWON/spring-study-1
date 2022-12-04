# 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술

```
* Java 11
* Spring boot
* Thymeleaf
* H2
```

<br>

## 목차
* [Spring Boot Basis](#spring-boot-basis)
  * 정적 컨텐츠
  * MVC와 템플릿 엔진
  * API
* [회원 관리 예제 - 백앤드 개발](#회원-관리-예제---백앤드-개발)
* [스프링 빈과 의존관계](#스프링-빈과-의존관계)
  * Dependency Injection(DI, 의존성 주입)
  * 스프링 빈
  * 스프링 빈 등록 방법: 컴포넌트 스캔(자동 의존관계 설정)
  * 스프링 빈 등록 방법: 자바 코드로 직접 등록
* [웹 MVC 개발 - 회원 관리 예제](#웹-mvc-개발---회원-관리-예제)
* [스프링DB 접근 기술](#스프링-db-접근-기술)
  * 스프링 통합 테스트
  * 스프링 JdbcTemplate
  *JdbcTemplate
  * RowMapper 인터페이스
  * JPA
  * 스프링 데이터 JPA
* [Aspect Oriented Programming(AOP, 관점 지향 프로그래밍)](#aspect-oriented-programmingaop-관점-지향-프로그래밍)

<br>

## Spring Boot Basis
### 정적 컨텐츠
1. 웹 브라우저 요청(http://~//test.html)이 들어오면 스프링 부트의 내장 톰켓 서버가 요청을 받음 
2. 스프링에서 요청(test)과 관련된 컨트롤러 탐색
3. 컨트롤러가 없다면 `resoures/static/`에서 관련 html 파일(test.html)을 찾아 웹브라우저에 반환
* `index.html`이 기본 파일
* `resoures/static/`에 없다면 `resources/templates` 탐색
### MVC와 템플릿 엔진
* MVC
  * Model: 비지니스 로직 처리, 데이터 및 DB와 관련, 데이터 처리 및 변경 등
  * View: 화면과 관련, Controller에서 받은 Model의 결과를 이용해 화면을 제작 -> 화면을 웹 브라우저에 전달하여 출력되도록 함
  * Controller: 비지니스 로직 처리, Model에서 데이터를 받아 View에게 전달, View에서 받은 화면을 웹 서버에 전달
  * `Web Browser` -> 웹 실행 요청 -> `Web Server` -> `Controller` -> 모델 호출 또는 데이터 전달 -> `Model`(<->DB) -> 데이터로 만든 값 객체 -> `Controller` -> 전달 -> `View` -> 화면 -> `Controller` -> 전달 -> `Web Server` -> `Web Browser` 로 출력
* 템플릿 엔진 처리 과정
  1. 웹 브라우저 요청(http://~//test)이 들어오면 스프링 부트의 내장 톰켓 서버가 요청을 받음 
  2. 스프링에서 요청(test)과 관련된 컨트롤러가 Model을 통해 값을 넘김
  3. `viewResolver`가 `resoures/templates/`에서 관련 html 파일(test.html)을 찾아 변환(템플릿 처리)하여 웹브라우저에 반환
### API
  1. 웹 브라우저 요청(http://~//test)이 들어오면 스프링 부트의 내장 톰켓 서버가 요청을 받음 
  2. 스프링에서 요청(test)과 관련된 컨트롤러가 동작 수행
  3. `@ResponseBody`가 있으면 `HttpMessageConverter`가 동작 -> 객체: `MappingJackson2HttpMessageConverter`, 문자(열): `StringHttpMessageConverter` 동작
  4. 객체는 JSON, 문자(열)는 문자(열)로 변환하여 웹 브라우저에 반환
  * 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러의 반환 타입 정보를 조햅해 `HttpMessageConverter`가 선택됨

<br>

## 회원 관리 예제 - 백앤드 개발
* 일반적인 웹 애플리케이션 계층 구조<br>
  ```
  컨트롤러 -> 서비스 -> 리포지토리 -> DB
         └>  └> 도메인 <┘
  ```
  * 컨트롤러: 웹 MVC의 컨트롤러
  * 서비스: 핵심 비지니스 로직
  * 리포지토리: 도메인 객체를 DB에 저장하고 관리
  * 도메인: 비지니스 도메인 객체, ex) 회원, 주문, 쿠폰 등 DB에 저장되고 관리됨
* 테스트 케이스 작성
  * `@Test`: 테스트를 위한 함수 작성
  * 하나의 클래스에 테스트 함수가 여러 개인 경우, 테스트 함수의 실행 순서는 랜덤
  * 순서의 의존성이 없도록 테스트가 끝날 때마다 데이터 클리어 필요 -> `@AfterEach`
  * 테스트 코드와 실제 코드에서 각각 객체 생성시 서로 다른 객체를 사용 -> `@BeforeEach`에서 `Dependency Injection(DI)`
* 코드에서 `domain, repository, service, test`

<br>

## 스프링 빈과 의존관계
### Dependency Injection(DI, 의존성 주입)
* 의존관계를 외부에서 넣어주는 것
* 단방향 의존성: `Controller` → `Service` → `Repository`
* DI를 하지 않을 경우
  ```java
  public class MemberService {
      private final MemberRepository memberRepository = new MemoryMemberRepository();
  }
  ```
  * 개발자가 객체를 직접 생성해 의존성 연결
  * 단위 테스트가 어렵고, 결합도를 높이는 단점 존재
* DI를 사용할 경우
  ```java
  public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired //생성자
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
  }
  ```
  * 의존성 연결을 스프링이 함
  * 생성자에 `@Autowired`가 있으면 객체 생성 시점에 `스프링이 의존관계 부여` -> 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌
  * 생성자가 1개면 `@Autowired` 생략 가능
* 종류
  * 필드 주입
  ```java
  @Controller
  public class MemberController {
    @Autowired private MemberService memberService;
  }
  ```
  * setter 주입
  ```java
  @Controller
  public class MemberController {
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
  }
  ```
    * public에 노출되어 중간에 문제가 될 가능성이 있음
  * 생성자 주입: `권장`
  ```java
  @Controller
  public class MemberController {
    private MemberService memberService;

    @Autowired // Dependency Injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
  }
  ```
    의존관계가 실행중에 동적으로 변하는 경우는 거의(아예) 없음
### 스프링 빈
* 스프링 컨테이너에 의해 만들어진 자바 객체
* 스프링에 의해 생명주기 관리
* 정점: 결합도 적음, 코드 단순화
* (+) 스프링은 스프링 빈으로 등록된 객체만 관리 가능
### 스프링 빈 등록 방법: 컴포넌트 스캔(자동 의존관계 설정)
* `@Component` 주석이 있으면 스프링 빈으로 자동 등록됨
* `@Controller`, `@Service`, `@Repository`는 `@Component`를 포함하고 있어 스프링 빈으로 자동 등록
* 과정
  (1) `@SpringBootApplication` 안에는 `@ComponentScan`이 있는데, 어디부터 `@Component`를 찾을지 알려줌  
  (2) `@ComponentScan`이 있는 클래스의 패키지부터 하위 패키지의 모든 클래스를 탐색  
  (3) `@Component` 주석이 있는 클래스를 찾아 스프링 빈 등록
* (+) 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤 등록(= 유일하게 1개를 등록하여 공유) -> 같은 스프링 빈이면 모두 같은 인스턴스
### 스프링 빈 등록 방법: 자바 코드로 직접 등록
* `Java class` 파일을 생성해 스프링 빈 등록
```java
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
```
* 상황에 따라 클래스를 변경할 때 사용
* (+) XML로 설정하는 방법도 있지만 최근에는 잘 사용하지 않음

<br>

## 웹 MVC 개발 - 회원 관리 예제
* 회원 등록, 조회 기능 개발

<br>

## 스프링 DB 접근 기술
* [H2](https://www.h2database.com) DB 사용
* `DataSource` 객체는 DB 커넥션을 획득할 때, 사용
* 기존 메모리 저장 -> DB 저장으로 변경
  * 기존 코드는 변경하지 않고 `config` 파일만 변경하면 됨
  * 개방-폐쇄 원칙(OCP, Open-Closed Principle): 확장에는 열려있고, 수정(변경)에는 닫혀있다.
### 스프링 통합 테스트
* `@SpringBootTest`: 스프리 컨테이너와 테스트 실행
* `@Transactional`: 테스트 시작 전에 트랜잭션 수행, 테스트 이후에 롤백-> DB에 데이터가 남지 안하 다음 테스트에 영향을 주지 않음
* `@Commit`: 실제로 DB에 데이터 저장
### 스프링 JdbcTemplate
#### JdbcTemplate
* SQL 연산을 제고하는 JDBC 코드용 기본 템플릿
* 순수 Jdbc와 동일한 환경설정
* 반복 코드는 대부분 제거 해줌, SQL은 직접 작성 필요
```java
public class Main {
  private final JdbcTemplate jdbcTemplate;
  public Main(DataSource dataSource) { //생성자의 파라미터로 DataSource사용
      jdbcTemplate = new JdbcTemplate(dataSource);
  }
```
#### RowMapper 인터페이스
* 원하는 형태의 결과값 반환 가능
* mapRow 메소드
```java
Ins mapRow(ResultSet rs, int count);
```
  * ResultSet에 값을 담아 특정 객체(Ins)에 저장을 count만큼 반복

### JPA
* 기본적인 SQL도 JPA가 만들어서 실행
* SQL과 데이터 중심 설계 -> `객체 중심 설계`
* `@Entity`: 엔티티 매핑
  * `@Id`: DB의 기본키를 의미
  
### 스프링 데이터 JPA
* 구현 클래스 없이 `인터페이스`만으로도 개발 가능
* `CRUD`를 스프링 데이터 JPA에서 제공

<br>

## Aspect Oriented Programming(AOP, 관점 지향 프로그래밍)
* 공통 관심 사항(Cross-Cutting Concern)과 핵심 관심 사항(Core Concern)을 분리
* 예시
  * 특정 로직을 모든 메소드에 적용하고 싶을 때 -> 모든 메소드에 일일이 로직을 추가하는 것이 아니라, 로직을 만들어서 모든 메소드에 적용
* 사용 방법
  ```java
  @Component
  @Aspect
  public class AOPClass {

      @Around("execution(* hello.hellospring..*(..))")
      public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

      }
  }
  ```
  * `@Component`: 스프링 빈 추가, config 파일에서 직접 추가해도 됨
  * `@Aspect`: AOP 등록
  * `@Around`: AOP의 적용 범위 지정
* AOP 의존 관계
  * AOP 없을 때: `Controller` -> `Service`
  * AOP 있을 때: `Controller` -> `프록시 Service` -> joinPoint.proceed() -> `Service`