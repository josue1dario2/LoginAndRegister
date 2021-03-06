package com.app.loginAndRegister.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UseDetailsServiceImpl useDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(useDetailsService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/css/**","/js/**","/img/**").permitAll()
                    .and()
                        .formLogin()
                            .loginPage("/login")
                            .loginProcessingUrl("/logincheck")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/index")
                            .failureUrl("/login?error=error")
                            .permitAll()
                    .and()
                        .logout()
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/")
                            .permitAll()
                    .and().csrf()
                        .disable();
    }


}
