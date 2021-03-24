package example.hellosecurity.security.conf;

import example.hellosecurity.security.filter.CusAuthenticationProcessingFilter;
import example.hellosecurity.security.provider.CusAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * 配置用户
     * @return
     * @throws Exception
     */
    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("user").roles("USER").build());
        manager.createUser(users.username("admin").password("admin").roles("USER","ADMIN").build());
        return manager;
    }

    /**
     * 配置 web安全配置适配器
     */
    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        CusAuthenticationProcessingFilter cusAuthenticationProcessingFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .formLogin(loginConf -> loginConf
                            .loginPage("/login.html")
                            .loginProcessingUrl("/my/login")
                    )
                    .logout(logoutConf -> logoutConf
                            .logoutUrl("/my/logout")
                    )
                    .authorizeRequests(accessConfig -> accessConfig
                            .antMatchers("/login.html").permitAll()
//                            .anyRequest().authenticated()
                            .anyRequest().permitAll()
                    )
                    .addFilterAt(cusAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class)
                    ;

        }
    }

    //    /**
//     * 配置 web安全配置适配器
//     */
//    @Configuration
//    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.csrf().disable()
//
//                    //1、登录配置
//                    .formLogin()
//                    .loginPage("/login.html").loginProcessingUrl("/my/login")
//                    .and()
//
//                    //2、登出配置
//                    .logout()
//                    .logoutUrl("/my/logout")
////                    .addLogoutHandler(new CustomLogoutHandler())
////                    .logoutSuccessHandler(new CustomLogoutSuccessHandler())
//                    .and()
//
//                    //3、请求认证配置
//                    .authorizeRequests()
//                    .antMatchers("/login.html").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                    ;
//        }
//    }


}