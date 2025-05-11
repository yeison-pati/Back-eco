package com.itm.ecosurprise.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final List<String> publicPaths = Arrays.asList(
        "/auth/login",
        "/auth/register"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String path = request.getRequestURI();
        if (publicPaths.stream().anyMatch(path::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Por ahora, permitimos todas las peticiones autenticadas
        filterChain.doFilter(request, response);
    }
} 