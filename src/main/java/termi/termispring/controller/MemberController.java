package termi.termispring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import termi.termispring.domain.Member;
import termi.termispring.dto.MemberCreateRequest;
import termi.termispring.mapper.MemberMapper;
import termi.termispring.service.MemberService;

@RestController
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberController(MemberService memberService,MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @PostMapping(value="/members")
    public String createMember(@RequestBody MemberCreateRequest request) {
        Member member = memberMapper.requestToMember(request);
        memberService.createMember(member);

        return request.getName() + " 님 회원가입이 완료 되었습니다.";
    }
}
