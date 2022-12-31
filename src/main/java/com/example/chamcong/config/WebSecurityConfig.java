package com.example.chamcong.config;

import com.example.chamcong.filter.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SuppressWarnings("deprecation")
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JWTFilter jwtFilter;

    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    public WebSecurityConfig(JWTFilter jwtFilter, AuthenticationEntryPointImpl authenticationEntryPoint) {
        this.jwtFilter = jwtFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/api/user/register").permitAll()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers("/api/auth/reset_password").permitAll()
                .antMatchers("/api/auth/forgot_password").permitAll()
                .antMatchers("/api/auth/check_login").permitAll()
                .antMatchers("/api/auth/forgot-otp").permitAll()
                .antMatchers("/api/auth/reset-otp").permitAll()
                .antMatchers("/api/avatar/upload_images").permitAll()
                .antMatchers("/get-image/{fileName}").permitAll()
                //.antMatchers("/api/meeting").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and().logout().deleteCookies(JWTFilter.COOKIE_NAME)
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
