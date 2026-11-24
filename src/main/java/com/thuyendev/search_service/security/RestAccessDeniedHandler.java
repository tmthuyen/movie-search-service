package com.thuyendev.search_service.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        String message = accessDeniedException.getMessage() != null ? accessDeniedException.getMessage().replace("\"", "\\\"") : "Access Denied";
        String path = request.getRequestURI();

        String jsonResponse = String.format(
            "{\"timestamp\":\"%s\",\"status\":%d,\"error\":\"Forbidden\",\"message\":\"%s\",\"path\":\"%s\"}",
            Instant.now(),
            HttpServletResponse.SC_FORBIDDEN,
            message,
            path
        );

        response.getWriter().write(jsonResponse);
    }
}
