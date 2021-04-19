package termi.termispring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import termi.termispring.domain.AccessToken;
import termi.termispring.repository.AccessTokenRepository;

import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    private final AccessTokenRepository accessTokenRepository;

    @Autowired
    public TokenServiceImpl(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }

    @Override
    public Optional<AccessToken> findAccessTokenByToken(String token) {
        return accessTokenRepository.findAccessTokenByToken(token);

    }

    @Override
    public boolean isUsableToken(String token) {
        Optional<AccessToken> accessToken = accessTokenRepository.findAccessTokenByToken(token);

        if (accessToken != null) return true;
        else return false;
    }
}
