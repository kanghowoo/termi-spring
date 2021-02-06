package termi.termispring.repository;

import termi.termispring.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    void createMember(Member member);
    Optional<Member> findMemberByEmail(String email);
}
