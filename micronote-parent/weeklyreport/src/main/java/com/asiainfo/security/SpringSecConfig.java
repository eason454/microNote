package com.asiainfo.security;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by eason on 2017/2/21.
 */
@Configuration
public class SpringSecConfig extends WebSecurityConfigurerAdapter {
    //Remember that authentication is “Who I am?” as a user to the system. While authorization is “You are either allowed or not to do this” from the system.
    private AuthenticationProvider authenticationProvider;

    /**
     * AuthenticationManager是处理authentication的策略，它的通用实现是ProviderManager，ProviderManager会把Authentication
     * 委派给一个链式的AuthenticationProvider实例处理，这里使用自己的AuthenticationProvider
     * @param authenticationProvider
     */

    @Autowired
    @Qualifier("daoAuthenticationProvider")
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(StrongPasswordEncryptor passwordEncryptor){
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        passwordEncoder.setPasswordEncryptor(passwordEncryptor);
        return passwordEncoder;
    }
    /*
        配置安全访问规则
     */
    @Override
    protected void configure(HttpSecurity security) throws Exception{
        //Allows all requests to the /microNote
        //Secures all other paths of the application to require authentication
//        security.authorizeRequests().antMatchers("/microNote").permitAll().anyRequest().authenticated().and().httpBasic();
        security.authorizeRequests().anyRequest().permitAll();
        security.csrf().disable();
        security.headers().frameOptions().disable();
    }
   /* @Bean
    public StrongPasswordEncryptor strongEncryptor(){
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor;
    }*/
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    /**
     * configureAuthManager名字不重要，但是只能在@EnableWebSecurity, @EnableGlobalMethodSecurity, or @EnableGlobalAuthentication
     * 注解下的来配置AuthenticationManagerBuilder
     * @param authenticationManagerBuilder
     */
    @Autowired
    public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    }
}
