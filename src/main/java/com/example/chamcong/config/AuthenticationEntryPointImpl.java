package com.example.chamcong.config;

import com.example.chamcong.model.RootResponse;
import com.example.chamcong.utils.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        RootResponse<Object> root = new RootResponse<Object>();
        root.setMessage(authException.getMessage());
        root.setCode(410);
        response.getWriter().write(JsonUtils.objectToJson(root));

    }
}
