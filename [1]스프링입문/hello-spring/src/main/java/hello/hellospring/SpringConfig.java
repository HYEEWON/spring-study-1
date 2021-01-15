package hello.hellospring;

import hello.hellospring.aop.TimeTraceApp;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//직접 스프링 빈 등록
@Configuration
public class SpringConfig {

    /*
    private DataSource dataSource;


    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */

    /*private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /*@Bean
    public TimeTraceApp timeTraceApp() {
        return new TimeTraceApp();
    }*/
    /*@Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository(); //메모리
        //return new JdbcMemberRepository(dataSource); //DB
        //return new JdbcTemplateMemberRepository(dataSource); //JdbcTemplate
        return new JpaMemberRepository(em);
    } //다형성*/
}
