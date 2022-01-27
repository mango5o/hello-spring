package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
// 테스트는 순서와 상관없이 서로 의존관계없이 설계되어야 한다.
// 그러기 위해서는 하나의 테스트(혹은 공용 저장소)가 끝날 때 마다 clear 되어야 한다.
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 한 후 코드를 clear 해주는 코드. / callback method라고도 부른다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
       // System.out.println("result = " + (result == member));
        // 글자로 보기 싫어서 하단과 같이 assertion을 사용함
        // Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result); // assert = member (둘이 동일하다.)
        // 요즘 가장 많이 씀
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member1.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
