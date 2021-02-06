package termi.termispring.service;

import termi.termispring.domain.Member;

import java.util.Optional;

public interface MemberService {

    void createMember(Member member);
    Optional<Member> findMemberByEmail(String email);

}
