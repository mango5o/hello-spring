package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired // 생성자가 1개인 경우에는 생략가능하다.
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*// 하단과 같이 작성하는 경우 Spring에서 자동으로 DI 해준다.
        private EntityManager em;

        @Autowired
        public SpringConfig(EntityManager em) {
            this.em = em;
        }
    */
   /* private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
*/
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }


//    @Bean
//    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        // MemberRepository 는 interface 라서 MemoryMemberRepository (구현체) 를 사용한다.
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em); // entitymanager 가 필요함

//    }
}
