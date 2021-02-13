package com.example.restfulwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    //@RequestMapping(method= RequestMethod.GET, path="//hello-world")
    @GetMapping(path = "/hello-world")
    public String HelloWorld() {
        return "Hello-World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean HelloWorldBean() {
        return new HelloWorldBean("Hello World"); //JSON
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean HelloWorldBean(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello, %s", name));
    }

    @GetMapping(path = "/hello-world-bean/path-variable2/{name}")
    public HelloWorldBean HelloWorldBean2(@PathVariable(value="name") String names) {
        return new HelloWorldBean(String.format("Hello, %s", names));
    }

    @GetMapping(path="/hello-world-internationalized") //다국어 처리 함수
    public String helloWorldInternationalized
            (@RequestHeader(name="Accept-Language", required=false) Locale locale) {
        // 요청 헤더에 Accept-Language가 없으면 Locale의 기본값인 한국어 사용
        return messageSource.getMessage("greeting.message", null, locale);
    } // 한국어는 utf-8 인코딩 설정 필요
}
