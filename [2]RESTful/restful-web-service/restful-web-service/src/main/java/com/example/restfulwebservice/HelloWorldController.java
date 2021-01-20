package com.example.restfulwebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
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
}
