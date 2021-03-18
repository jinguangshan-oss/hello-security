package example.hellosecurity.security.provider;

import example.hellosecurity.security.service.CusUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CusAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    CusUserDetailsService cusUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String) authentication.getPrincipal();
        UserDetails userDetails = cusUserDetailsService.loadUserByUsername(userName);

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
