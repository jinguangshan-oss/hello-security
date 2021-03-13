package example.hellosecurity.conf;

import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig {
    ////配置正常认证
    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        // ensure the passwords are encoded properly
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("user").roles("USER").build());
        manager.createUser(users.username("admin").password("admin").roles("USER","ADMIN").build());
        return manager;
    }

    //创建一个包含@Order的WebSecurityConfigurerAdapter实例，以指定应该首先考虑哪个WebSecurityConfigurerAdapter。
    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            //http.antMatcher声明这个HttpSecurity将只适用于以/api/开头的url
            http
                .antMatcher("/admin/**")
                .authorizeRequests(authorize -> authorize.anyRequest().hasRole("ADMIN"))
                .httpBasic();
//            http.authorizeRequests() // 开启 HttpSecurity 配置
//                    .antMatchers("/admin/**").hasRole("ADMIN") // admin/** 模式URL必须具备ADMIN角色
//                    .antMatchers("/user/**").access("hasAnyRole('ADMIN','USER')") // 该模式需要ADMIN或USER角色
//                    .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')") // 需ADMIN和DBA角色
//                    .anyRequest().authenticated() // 用户访问其它URL都必须认证后访问（登录后访问）
//                    .and().formLogin().loginProcessingUrl("/login").permitAll() // 开启表单登录并配置登录接口
//                    .and().csrf().disable(); // 关闭csrf
        }
    }

    //创建WebSecurityConfigurerAdapter的另一个实例。如果URL不是以/api/开头，将使用此配置。此配置在ApiWebSecurityConfigurationAdapter之后被考虑，因为有一个@Order值在1之后(没有@Order则默认为last，即最后一个)。
    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/user/**")
                .authorizeRequests(authorize -> authorize.anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").permitAll());
        }
    }
}