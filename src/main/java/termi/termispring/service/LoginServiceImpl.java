package termi.termispring.service;

import org.springframework.stereotype.Service;
import termi.termispring.domain.AccessToken;
import termi.termispring.domain.Member;
import termi.termispring.repository.AccessTokenRepository;
import termi.termispring.util.AccessTokenHelper;
import termi.termispring.util.HashingBCrypt;

import java.io.UnsupportedEncodingException;

@Service
public class LoginServiceImpl implements LoginService {

    private final AccessTokenRepository accessTokenRepository;

    public LoginServiceImpl(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }

    @Override
    public String checkPassword(String plainTextPassword,Member member) throws UnsupportedEncodingException {

        if (HashingBCrypt.checkpassword(plainTextPassword, member.getPassword())) {
            String token = AccessTokenHelper.createToken(member.getEmail());

            AccessToken accessToken = new AccessToken();
            accessToken.setAccessToken(token);
            accessToken.setMemberId(member.getId());
            accessTokenRepository.createToken(accessToken);

            return token;

        } else {
            return "not corrected";
        }
    }
}
