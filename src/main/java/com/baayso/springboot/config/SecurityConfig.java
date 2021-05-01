package com.baayso.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baayso.commons.security.AESCoder;

/**
 * Spring Security Config.
 *
 * @author ChenFangjie (2021/4/5 21:19)
 * @since 4.0.0
 */
@Configuration
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    @Bean
    public AESCoder aesCoder() {
        return new AESCoder("G7jX/RpqbqzbvUoxJ2fEaVdhk8e/axGXbhEXli2dR0TI=");
    }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.formLogin()
    //             .loginPage("/login.html")
    //             .loginProcessingUrl("/auth/form")
    //             .and()
    //             .authorizeRequests()
    //             .antMatchers("/login.html").permitAll()
    //             .anyRequest()
    //             .authenticated()
    //             .and()
    //             .csrf().disable();
    // }

}
