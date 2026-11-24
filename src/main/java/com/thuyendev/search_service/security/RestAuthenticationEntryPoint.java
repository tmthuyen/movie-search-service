package com.thuyendev.search_service.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String message = authException.getMessage() != null ? authException.getMessage().replace("\"", "\\\"") : "Unauthorized";
        String path = request.getRequestURI();

        String jsonResponse = String.format(
            "{\"timestamp\":\"%s\",\"status\":%d,\"error\":\"Unauthorized\",\"message\":\"%s\",\"path\":\"%s\"}",
            Instant.now(),
            HttpServletResponse.SC_UNAUTHORIZED,
            message,
            path
        );

        response.getWriter().write(jsonResponse);
    }
}
