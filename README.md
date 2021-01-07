# 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술
인프런의 스프링 강의 내용 정리

## Spring Boot Basis
### 정적 컨텐츠
1. 웹 브라우저 요청(http://~//test.html)이 들어오면 스프링의 내장 톰캣 서버가 요청을 받음 
2. 스프링 컨테이너에서 요청(test)과 관련된 컨트롤러 탐색
3. 컨트롤러가 없다면 `resoure/static/`에서 관련 html 파일(test.html)을 찾아 반환
* `index.html`이 기본 파일
* `resoure/static/`에 없다면 `resource/templates` 탐색
### MVC
### API
