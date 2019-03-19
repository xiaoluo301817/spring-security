package cn.xiaoluo.security.springsecurity.config;

import cn.xiaoluo.security.springsecurity.filter.ValidateCodeFilter;
import cn.xiaoluo.security.springsecurity.handler.SecurityFailHandler;
import cn.xiaoluo.security.springsecurity.handler.SecuritySuccessHandler;
import org.apache.activemq.broker.region.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author luowenqin
 * @create 2019-03-12 8:58
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {




    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder ();
    }



    @Autowired
    private SecuritySuccessHandler securitySuccessHandler;


    @Autowired
    private  SecurityFailHandler securityFailHandler;






    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter =new ValidateCodeFilter();
        validateCodeFilter.setSecurityFailHandler(securityFailHandler);
        http.
                addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class).
                formLogin().                                //表单请求
                loginPage("/login.html").                   //登录页
                loginProcessingUrl("/authentication/form").
                successHandler(securitySuccessHandler).
                failureHandler(securityFailHandler).
                and().
                authorizeRequests().                        //请求授权
                antMatchers("/login.html","/admin/code").permitAll().
                anyRequest().                               // 任何请求
                authenticated()
                .and().
                csrf().
                disable();                            // 都需要授权



        http.logout();







        //http.formLogin()
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }
}
