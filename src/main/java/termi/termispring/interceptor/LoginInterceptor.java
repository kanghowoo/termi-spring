package termi.termispring.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import termi.termispring.service.TokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        System.out.println("interceptor test");

        if (token == null) {
            System.out.println("token is null");
        } else {
            if (tokenService.isUsableToken(token)) {
                System.out.println("token : " + token);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
