package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// interface 가 interface를 받을 때는 extends를 사용한다.
// interface : 다중 생성 가능
public interface SpringDataJpaMemberRepository extends JpaRepository <Member, Long>, MemberRepository{

    // JSQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
