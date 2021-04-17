package termi.termispring.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");


        if(token==null) {
            System.out.println("token null"); // <-로그인 필요한 화면으로 이동

        }
        else {
            System.out.println("token test : " +token);
        }
        return true;
    }
}
