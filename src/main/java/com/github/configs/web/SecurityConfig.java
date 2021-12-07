package com.github.configs.web;

import com.github.security.jwt.JwtConfigurer;
import com.github.security.jwt.token.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private static final String AUTH_ENDPOINT = "/senla/auth";
    private static final String EVENT_ENDPOINT = "/senla/event-management/**";
    private static final String LOCATION_ENDPOINT = "/senla/location-management/**";
    private static final String TICKET_ENDPOINT = "/senla/ticket-management/**";
    private static final String USER_ENDPOINT = "/senla/user-management/**";

    private static final String USER_ROLE = "USER";
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String ARTIST_ROLE = "ARTIST";

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, AUTH_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.POST, EVENT_ENDPOINT).hasRole(ARTIST_ROLE)
                .antMatchers(HttpMethod.POST, LOCATION_ENDPOINT).hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.POST, TICKET_ENDPOINT).hasRole(USER_ROLE)
                .antMatchers(HttpMethod.POST, USER_ENDPOINT).hasRole(USER_ROLE)
                .antMatchers(HttpMethod.GET, EVENT_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.GET, LOCATION_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.GET, TICKET_ENDPOINT).hasRole(USER_ROLE)
                .antMatchers(HttpMethod.GET, USER_ENDPOINT).hasRole(USER_ROLE)
                .antMatchers(HttpMethod.DELETE, EVENT_ENDPOINT).hasRole(ARTIST_ROLE)
                .antMatchers(HttpMethod.DELETE, LOCATION_ENDPOINT).hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.DELETE, TICKET_ENDPOINT).hasRole(USER_ROLE)
                .antMatchers(HttpMethod.DELETE, USER_ENDPOINT).hasRole(USER_ROLE)
                .antMatchers(HttpMethod.PUT, EVENT_ENDPOINT).hasRole(ARTIST_ROLE)
                .antMatchers(HttpMethod.PUT, LOCATION_ENDPOINT).hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.PUT, TICKET_ENDPOINT).hasRole(USER_ROLE)
                .antMatchers(HttpMethod.PUT, USER_ENDPOINT).hasRole(USER_ROLE)
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

}
