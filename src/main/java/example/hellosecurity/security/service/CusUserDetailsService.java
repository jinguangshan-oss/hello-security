package example.hellosecurity.security.service;

import example.hellosecurity.mybatis.entity.User;
import example.hellosecurity.mybatis.mapper.UserMapper;
import example.hellosecurity.security.entity.CusUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CusUserDetailsService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User param = new User();
        param.setName(username);
        User user = userMapper.getUser(param);
        return new CusUserDetails(user);
    }
}
