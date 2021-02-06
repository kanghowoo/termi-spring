package termi.termispring.service;

import termi.termispring.domain.Member;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface LoginService {
    String checkPassword(String plainTextPassword,Member member) throws UnsupportedEncodingException;
}
