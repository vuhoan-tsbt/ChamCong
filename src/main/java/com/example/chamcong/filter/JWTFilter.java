package com.example.chamcong.filter;

import com.example.chamcong.repository.UserRepository;
import com.example.chamcong.security.JWTProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class JWTFilter extends OncePerRequestFilter {

    public static final String COOKIE_NAME = "auth_by_cookie";

    public final UserRepository userRepository;

    public final JWTProvider jwtProvider;

    public JWTFilter(UserRepository userRepository, JWTProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader("Authorization");
        if (jwtToken != null && jwtToken.startsWith("Bearer ") && jwtProvider.validateToken(jwtToken)) {
            String email = jwtProvider.getEmailFromJWT(jwtToken.substring(7, jwtToken.length()));
            UserDetails user = getUserWithRoleByEmail(email);
            if (user != null) {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
            }
        }
//        Optional<Cookie> cookieAuth = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
//                .filter(cookie -> COOKIE_NAME.equals(cookie.getName()))
//                .findFirst();
//        if (cookieAuth.isPresent()) {
//            SecurityContextHolder.getContext().setAuthentication(
//                    new PreAuthenticatedAuthenticationToken(cookieAuth.get().getValue(), null));
//        }
        filterChain.doFilter(request, response);
    }

    protected UserDetails getUserWithRoleByEmail(String email) {
        return userRepository.getUserWithRoleByEmail(email);
    }


}
