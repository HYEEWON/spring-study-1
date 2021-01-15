package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

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

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository(); //메모리
        //return new JdbcMemberRepository(dataSource); //DB
        //return new JdbcTemplateMemberRepository(dataSource); //JdbcTemplate
        return new JpaMemberRepository(em);
    } //다형성
}
