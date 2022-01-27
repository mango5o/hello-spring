package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service 안에 @Component 가 포함되어 있다.
@Transactional
// @Transactional : data 를 저장하거나 변경할 때 사용한다.
public class MemberService {

    private  final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입 코드
    public Long join(Member member) {

        // 같은 이름인 중복 회원 X
        // Optional 안에 Member 객체가 있는 것이다.
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return  member.getId(); // member 반환
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    // 전체 회원 조회
    public List<Member> findMembers() {
       return memberRepository.findAll();
    }

    public Optional <Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
