package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //memberController 객체를 만들어 스프링에 생성해 관리함
// == 스프링 컨테이너에서 스프링 빈이 관리된다고 표현
public class MemberController {
    //스프링 컨테이너에 등록하고 그것을 받아서 사용
    private final MemberService memberService;

    @Autowired // Dependency Injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
