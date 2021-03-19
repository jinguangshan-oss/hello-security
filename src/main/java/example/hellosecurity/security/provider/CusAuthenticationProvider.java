package example.hellosecurity.security.provider;

import example.hellosecurity.security.authorities.CusGrantedAuthority;
import example.hellosecurity.security.entity.CusUserDetails;
import example.hellosecurity.security.service.CusUserDetailsService;
import example.hellosecurity.security.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CusAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CusUserDetailsService cusUserDetailsService;

    @Autowired
    CusAuthenticationManager cusAuthenticationManager;

    /**
     * 注入Manager
     */
    @PostConstruct
    public void init(){
        cusAuthenticationManager.register(this);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //获取登录用户信息
        String loginUserName = (String) authentication.getPrincipal();
        String loginPassword = (String) authentication.getCredentials();

        //获取数据库用户信息
        CusUserDetails userDetails = cusUserDetailsService.loadUserByUsername(loginUserName);

        //验证密码
        boolean boo = PasswordUtil.isValidPassword(loginPassword,userDetails.getPassword(),null);
        if (!boo) {
            throw new BadCredentialsException("密码错误！");
        }

        //封装Authentication并返回
        return new UsernamePasswordAuthenticationToken(userDetails, loginPassword, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        if(authentication == UsernamePasswordAuthenticationToken.class){
            return true;
        }
        return false;
    }
}
