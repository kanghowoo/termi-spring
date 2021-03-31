package termi.termispring.service;

import org.springframework.stereotype.Service;
import termi.termispring.domain.AccessToken;
import termi.termispring.domain.Member;
import termi.termispring.repository.AccessTokenRepository;
import termi.termispring.repository.MemberRepository;
import termi.termispring.util.AccessTokenHelper;
import termi.termispring.util.HashingBCrypt;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AccessTokenRepository accessTokenRepository;

    public MemberServiceImpl(MemberRepository memberRepository,AccessTokenRepository accessTokenRepository) {
        this.memberRepository = memberRepository;
        this.accessTokenRepository = accessTokenRepository;
    }


    @Override
    public void createMember(Member member) {
        member.setCreatedAt(Timestamp.valueOf(LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        String initPassword = member.getPassword();
        member.setPassword(HashingBCrypt.hashPassword(initPassword));
        memberRepository.createMember(member);

        String token = AccessTokenHelper.createToken();

        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(token);
        accessToken.setMemberId(member.getId());

    }

    @Override
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findMemberByEmail(email);
    }
}
