package termi.termispring.repository;

import termi.termispring.domain.AccessToken;

import java.io.UnsupportedEncodingException;

public interface AccessTokenRepository {
    void createToken(AccessToken accessToken) throws UnsupportedEncodingException;
    void updateToken(AccessToken accessToken);
}
