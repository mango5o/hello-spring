package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    // 생성자를 빼고 필드에 autowired 주입하는 방법도 있다. (필드주입)

    private MemberService memberService;

/*    // Setter injection 방식
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }*/

    @Autowired
    // @Autowired 역할 : memberService를 spring contianer 에 있는 memberService 를 가져와 연결시켜준다.
    // 생성자를 통해 의존관계를 주입시켜준다.
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
    // @PostMapping : data 를 form 과 같은 곳에 넣어서 전달할 때 사용하는 mapping이다.
    // @GetMapping : 조회를 할 때 주로 사용한다.
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        // console 창에서 확인
        //System.out.println("member = " + member.getName());

        memberService.join(member);

        return  "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
