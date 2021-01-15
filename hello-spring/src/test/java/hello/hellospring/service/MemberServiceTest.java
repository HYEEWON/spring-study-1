package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    // 주석의 방법은 코드와 테스트가 다른 객체를 사용
    //MemberService memberService = new MemberService();
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); //Dependency Injection(DI)
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test //테스트트는 한글로 적기도 함
    void 회원가입() {
        //given: 이런 상황에서
        Member member = new Member();
        member.setName("hello");
        //when: 실행했을때
        Long saveId = memberService.join(member);
        //then: 어떤 결과가 나옴
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //메시지 확인
        /*try{
            memberService.join(member2);
            fail(); //예외가 발생해야 함
        } catch (IllegalStateException e) {
            // 예외가 정상적으로 발생
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}