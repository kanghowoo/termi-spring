package termi.termispring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import termi.termispring.domain.Member;
import termi.termispring.dto.LoginRequest;
import termi.termispring.service.LoginService;
import termi.termispring.service.MemberService;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class LoginController {
    private final LoginService loginService;
    private final MemberService memberService;

    @Autowired
    public LoginController(LoginService loginService,MemberService memberService) {
        this.loginService = loginService;
        this.memberService = memberService;
    }

    @PostMapping(value="/login")
    public ResponseEntity<?> loginRequest(@RequestBody LoginRequest request) throws UnsupportedEncodingException {
        Optional<Member> member = memberService.findMemberByEmail(request.getEmail());

        Map<String,String> result = new HashMap<>();

        String tokenResult = loginService.checkPassword(request.getPassword(),member.get());
        result.put("result",tokenResult);

        return ResponseEntity.ok(result);
    }
}
