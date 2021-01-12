package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // Null을 받을 때, Optional 사용
    List<Member> findAll(); //모든 회원 리스트 반환
}
