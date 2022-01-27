package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // Sequence 는 0,1,2와 같은 key값을 생성해주는 역할이다.


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
        // store에 넣기전에 member애 id 값을 세팅해줌
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // null이 반환될 가능성이 있으면 Optional을 사용해서 null값을 감싸 반환시켜준다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store에 있는 values(members) 를 반환시켜준다.
    }

    public void clearStore() {
        store.clear();
    }
}
