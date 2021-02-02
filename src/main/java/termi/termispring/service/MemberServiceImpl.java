package termi.termispring.service;

import org.springframework.stereotype.Service;
import termi.termispring.Hashing;
import termi.termispring.domain.Member;
import termi.termispring.repository.MemberRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void createMember(Member member) {
        member.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        String initPassword = member.getPassword();
        member.setPassword(Hashing.hasingPassword(initPassword));
        memberRepository.createMember(member);
    }
}
