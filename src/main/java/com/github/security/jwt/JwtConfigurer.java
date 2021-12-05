package com.github.security.jwt;

import com.github.security.jwt.token.JwtTokenFilter;
import com.github.security.jwt.token.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenProvider tokenProvider;

    public void configurer(HttpSecurity httpSecurity) throws Exception {
        JwtTokenFilter tokenFilter = new JwtTokenFilter(tokenProvider);
        httpSecurity.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
