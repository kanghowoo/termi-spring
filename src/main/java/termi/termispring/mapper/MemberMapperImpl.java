package termi.termispring.mapper;

import org.springframework.stereotype.Component;
import termi.termispring.domain.Member;
import termi.termispring.dto.MemberCreateRequest;

@Component
public class MemberMapperImpl implements MemberMapper{
    @Override
    public Member requestToMember(MemberCreateRequest request) {
        Member member = new Member();

        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPassword(request.getPassword());

        return member;
    }
}
