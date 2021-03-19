package example.hellosecurity.security.filter;

import example.hellosecurity.security.handler.CusAuthenticationFailureHandler;
import example.hellosecurity.security.handler.CusAuthenticationSuccessHandler;
import example.hellosecurity.security.provider.CusAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CusAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public CusAuthenticationProcessingFilter(CusAuthenticationManager authenticationManager,
                                             CusAuthenticationFailureHandler failureHandler,
                                             CusAuthenticationSuccessHandler successHandler) {
        super(new AntPathRequestMatcher("/my/login", "POST"), authenticationManager);
        this.setAuthenticationFailureHandler(failureHandler);
        this.setAuthenticationSuccessHandler(successHandler);
    }

    /**
     * Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //创建Authentication
        String principal = request.getParameter("username");
        String credentials = request.getParameter("password");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, credentials);
        authentication.setDetails(this.authenticationDetailsSource.buildDetails(request));

        //交给AuthenticationManager进行认证处理
        return getAuthenticationManager().authenticate(authentication);
    }
}
