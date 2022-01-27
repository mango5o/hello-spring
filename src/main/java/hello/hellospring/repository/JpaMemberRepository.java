package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // JPA 는 EntityManager 으로 모든 동작을 한다.
  /*  build.gradle 에서 implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    lib 으로 추가해줌으로서 spring-boot가 자동으로 EntityManager 를 생성해준다. (내부에서 작동)
    다 만들어진 data 를 injection 받으면 된다. */
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override // findById : PK(ID) 조회
    public Optional<Member> findById(Long id) {
        Member member =  em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override // findByName : JPQL 이라는 객체지향쿼리언어를 사용해야함
    // sql 과 차이점은 객체(Entitiy)를 대상으로 Query를 연결해준다.
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        //findByName 을 하나만 찾을 때는 .stream 을 사용하여 반환한다.
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // entity(m) 자체를 select한다.
                .getResultList();
    }
}

