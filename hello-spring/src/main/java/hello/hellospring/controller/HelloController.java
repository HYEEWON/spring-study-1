package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //GET
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; //hello.html에 데이터를 넘김
        //경로: resources/templates/hello.html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name", required=false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http 통신 프로트콜의 응답 body에 return의 내용을 넣음
    public String hellostring(@RequestParam("name") String name) {
        return "hello "+name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체가 JSON으로 변환
    }

    static class Hello {
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}
