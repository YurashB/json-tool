package com.jsontool.interceptors;

import com.jsontool.errors.NoBearerTokenError;
import com.jsontool.errors.UnauthenticatedError;
import com.jsontool.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@AllArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null) {
            throw new UnauthenticatedError();
        }
        if (!authorizationHeader.startsWith("Bearer ")) {
            throw new NoBearerTokenError();
        }

        request.setAttribute("user", authService.getUserFromToken(authorizationHeader.substring(7)));

        return true;
    }
}
