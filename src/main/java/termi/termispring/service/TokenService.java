package termi.termispring.service;

import termi.termispring.domain.AccessToken;

import java.util.Optional;

public interface TokenService {

    Optional<AccessToken> findAccessTokenByToken(String token);
    boolean isUsableToken(String token);
}
