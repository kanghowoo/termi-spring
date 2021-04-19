package termi.termispring.repository;

import termi.termispring.domain.AccessToken;

import java.util.Optional;

public interface AccessTokenRepository {
    void createToken(AccessToken accessToken);
    void updateToken(AccessToken accessToken);
    Optional<AccessToken> findAccessTokenByMemberId(Long memberId);
    Optional<AccessToken> findAccessTokenByToken(String token);
}
