package example.hellosecurity.security.service;

import example.hellosecurity.mybatis.entity.User;
import example.hellosecurity.mybatis.mapper.UserMapper;
import example.hellosecurity.security.entity.CusGrantedAuthority;
import example.hellosecurity.security.entity.CusUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class CusUserDetailsService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public CusUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户
        User param = new User();
        param.setUsername(username);
        User user = userMapper.getUser(param);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在！");
        }

        //获取用户名，密码，用户权限
        String userName = user.getUsername();
        String password = user.getPassword();
        Collection<CusGrantedAuthority> cusGrantedAuthoritys = new ArrayList<>();
        String[] roles = user.getRole().split(",");
        for(String role : roles){
            cusGrantedAuthoritys.add(new CusGrantedAuthority(role));
        }

        //初始化CusUserDetails并返回
        return new CusUserDetails(userName, password, cusGrantedAuthoritys);
    }
}
