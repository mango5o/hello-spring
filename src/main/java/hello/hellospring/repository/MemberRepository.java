package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save (Member member);
    Optional<Member> findById (Long id); // id로 회원을 찾는 방법
    // Optional은 JAVA8 에 들어간 기능 (id나 name이 null일 경우 그대로 반환하지 않고,
    // Optional을 사용해 감싸서 반환해주는 기능.
    Optional<Member> findByName (String name);
    List<Member> findAll();
}
