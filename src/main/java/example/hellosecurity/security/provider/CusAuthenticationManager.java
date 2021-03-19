package example.hellosecurity.security.provider;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class CusAuthenticationManager implements AuthenticationManager{

    Collection<CusAuthenticationProvider> cusAuthenticationProviders = new ArrayList<>();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //遍历provider集合进行认证，认证成功则返回
        Authentication result = null;
        for(CusAuthenticationProvider cusAuthenticationProvider : cusAuthenticationProviders){
            if(cusAuthenticationProvider.supports(authentication.getClass())){
                result = cusAuthenticationProvider.authenticate(authentication);
                if(result.isAuthenticated()){
                    break;
                }
            }
        }
        return result == null ? authentication : result;
    }

    public void register(CusAuthenticationProvider provider){
        cusAuthenticationProviders.add(provider);
    }
}
