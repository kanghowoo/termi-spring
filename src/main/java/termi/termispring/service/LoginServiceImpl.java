package termi.termispring.service;

import org.springframework.stereotype.Service;
import termi.termispring.domain.AccessToken;
import termi.termispring.domain.Member;
import termi.termispring.repository.AccessTokenRepository;
import termi.termispring.util.AccessTokenHelper;
import termi.termispring.util.HashingBCrypt;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final AccessTokenRepository accessTokenRepository;

    public LoginServiceImpl(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }

    @Override
    public String checkPassword(String plainTextPassword,Member member) {

        if (HashingBCrypt.checkpassword(plainTextPassword, member.getPassword())) {

            String token = AccessTokenHelper.createToken();
            AccessToken newToken = new AccessToken();
            newToken.setAccessToken(token);
            newToken.setMemberId(member.getId());

            Optional<AccessToken> accessToken = accessTokenRepository.findAccessTokenByMemberId(member.getId());

            if(accessToken != null) {
                accessTokenRepository.updateToken(newToken);
            } else{
                accessTokenRepository.createToken(newToken);
            }
            return token;

        } else {
            return "not corrected";
        }
    }
}
