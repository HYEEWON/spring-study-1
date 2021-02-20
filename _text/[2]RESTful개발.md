# Spring Boot를 이용한 RESTful Web Services 개발

## Table of Contents
* [Web Service & Web Application](#web-service-web-application)
* [Spring Boot로 개발하는 RESTful Service](#spring-boot로-개발하는-restful-service)
* [User Service API 구현](#user-service-api-구현)
* [RESTful Service 기능 확장](#restful-service-기능-확장)
* [Spring Boot API 사용](#spring-boot-api-사용)

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

## Spring Boot로 개발하는 RESTful Service
### Spring Boot 원리
#### 설정 파일
* `application.yml`: 설정이름=값
```
logging:
  level:
    org.springframework: DEBUG
```
* `application.properties`: 설정이름:값
```
logging.level.org.springframework=DEBUG
```
### DispatcherServlet
* 클라이언트의 모든 요청을 한 곳으로 받아서 처리
* 요청에 맞는 Handler로 요청을 전달 -> Handler의 실행 결과를 HTTPResponse 형태로 만들어서 반환
* Request -> `Dispatcher Servlet` -> Handler Mapping -> `Controller` ->`ModelAndView` -> `ViewResolver` -> `View` -> Client
### RestController
* `@RestController`: `@Controller` + `@ResponseBody` (Spring4 부터 지원)
* View를 갖지 않는 REST Data(JSON/XML)를 반환
### Lombok
```java
@Data //set,get 등을 자동 생성
@AllArgsConstructor // 생성자 자동 생성
@NoArgsConstructor // 디폴트 생성자 자동 생성
```
### Path Variable
```java
@GetMapping(path = "/hello-world-bean/path-variable/{name}")
public HelloWorldBean HelloWorldBean(@PathVariable String name) {
    return new HelloWorldBean(String.format("Hello, %s", name));
}
```
```java
@GetMapping(path = "/hello-world-bean/path-variable/{name}")
public HelloWorldBean HelloWorldBean(@PathVariable(value="name") String names) {
    return new HelloWorldBean(String.format("Hello, %s", names));
}
```

## User Service API 구현
### CRUD
* POST: `@PostMapping("/")`
* GET: `@GetMapping("/")`
* PUT: `@PUTMapping("/")`
* DELETE: `@DeleteMapping("/")`
### ServletUriComponentsBuilder
* 사용자의 요청을 받아 응답할 때, 특정 값을 포함한 URI를 전달할 때 사용
* URI는 Response Header의 `Location`에서 얻을 수 있음
```java
@PostMapping("/users")
public ResponseEntity<User> createUser(@RequestBody User user) {
    User savedUser = service.save(user)

    URI location = ServletUriComponentsBuilder.fromCurrentRequest() // 사용자 요청 URI
            .path("/{id}").buildAndExpand(savedUser.getId()) // buildAndExpand()에서 얻은 값을 path() 변수에 넣음
            .toUri(); // URI 완성
    return ResponseEntity.created(location).build();
}
```
### HTTP Status Code
* 2xx: OK
* 4xx: Client 오류
* 5xx: Server 오류
### HTTP Status Code와 관련된 REST API 설계 고려 사항
* 서버로부터 상태코드 반환하는 것이 좋음 -> 모두 200이 아닌 201 등 다양하게 사용
* 예외를 모두 클라이언트에게 노출하는 것도 좋지는 않음 -> 보안 이슈
* HTTP 상태 코드를 원하는 것으로 사용 가능
```java
@ResponseStatus(HttpStatus.NOT_FOUND) // 404 NOT_FOUND
```
### Spring의 AOP를 이용한 Exception Handling
```java
@RestController
@ControllerAdvice // 모든 컨트롤러가 실행시, 해당 bean이 먼저 실행
public class Main {

    @ExceptionHandler(Exception.class) // 처리할 Exception 종류
    public final ResponseEntity<Object> handelAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(~, ~, request.getDescription(false));
        // 에러 객체 지정 가능
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }
```

## RESTful Service 기능 확장
### 유효성 체크를 위한 Validation API 사용
* `도메인` 클래스에서 데이터의 유효 범위 지정
  * 예시: `@Size`, `@Past`
* 컨트롤러의 함수의 전달인자에 `@Valid` 사용
### 다국어 처리를 위한 Internationalization 구현
* resource 폴더의 application.yml 수정
```
spring:
  messages:
    basename: messages // 기본 폴더명: messages
```
### Response 데이터 형식 변환
* 요청의 헤더: Key='Accept' Value=`application/xml`, `application/json`
  * XML로 데이터를 반환하기 위해 `pom.xml`에 의존성 추가
  ```
  <dependency>
      <groupId>com.fasterxml.jackson.dataformat</groupId>
  	  <artifactId>jackson-dataformat-xml</artifactId>
	  <version>2.10.2</version>
  </dependency>
  ```
### Filtering
* Annotation 사용
  * 도메인 클래스에 `@JsonIgnoreProperties(value={"password", "ssn"})` 적용 -> 전달하는 데이터 중, value에 해당하는 것은 전달X
  * 도메인 클래스의 변수에 `@JsonIgnore` 적용 -> 해당 변수가 전달X 
* `@JsonFilter("filterName")`와 `FilterProvider`사용
  * 도메인 클래스에  `@JsonFilter("filterName")` 적용
  * Controller에서 필터를 추가 (이 작업을 하지 않으면 에러 발생)
  ```java
  SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "password"); // 표현할 속성
  
  // 필터를 적용할 빈을 지정
  FilterProvider filters = new SimpleFilterProvider().addFilter("filterName", filter);
  MappingJacksonValue mapping = new MappingJacksonValue(users);
  mapping.setFilters(filters
  return mapping;
  ```
  * `SimpleBeanPropertyFilter.filterOutAllExcept`: 지정된 필드들만 JSON 변환
  * `SimpleBeanPropertyFilter.serializeAllExcept`: 지정되지 않은 필드들만 JSON 변환 -> 추후에 필드가 추가되면 오류가 발생할 수 있어 추천X
### REST API 버전 관리
* 의미: 사용자에게 보여지는 항목 제어, REST 및 앱의 변화에 따라 버전 관리 필요
* 주의 사항: URI Pollution, Caching, API Documentation, Misuse of HTTP Headers, 브라우저에서 실행 가능 여부
* 종류
  * URI Versioning: 일반 브라우저에서 실행 가능
  ```java
  @GetMapping("/v1/users/{id}")
  ```
  * Request Parameter Versioning: 일반 브라우저에서 실행 가능
  ```java
  @GetMapping(value="/users/{id}/", params="version=1") 
  //http://localhost:8088/admin/users/?version=1
  ```
  * Media Type Versioning(Content Negotiation, Accept Header): 일반 브라우저에서 실행 불가
  ```java
  @GetMapping(value = "/users/{id}", headers = "API-VERSION=1")
  // 요청 헤더: API-VERSION=1 // 키=값
  ```
  * Header Versioning: 일반 브라우저에서 실행 가능
  ```java
  @GetMapping(value = "/users/{id}/", produces = "application/vnd.company.appv1+json")
  // 요청 헤더: Accept=application/vnd.company.appv1+json // 키=값
  ```

## Spring Boot API 사용
### Hypermedia As The Engine Of Application state(HATEOAS)
* 현재 리소스와 연관된(호출 가능한) `자원 상태 정보`를 제공
* 상태를 전이할 수 있는 메커니즘 제공 -> `링크들에 대한 레퍼런스`를 서버 측에서 전송 -> 실제 링크없이 상태 전이 가능
  * `상태 전이`: 어떤 페이지에서 `링크`를 통해 다른 페이지로 가는 것
* 사용자는 여러 정보를 동시에 얻을 수 있다는 장점이 있음
* 의존성 추가
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-hateoas</artifactId>
</dependency>
```
* 예시 코드
```java
EntityModel<User> entityModel = new EntityModel<>(user);
WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
entityModel.add(linkTo.withRel("all-users"));
return entityModel;
```
* 실행 결과
```
"_links": {
       "all-users": {
           "href": "http://localhost:8088/users"
       }
   }
```