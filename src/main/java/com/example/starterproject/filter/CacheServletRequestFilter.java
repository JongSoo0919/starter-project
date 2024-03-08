package com.example.starterproject.filter;

import com.example.starterproject.util.CacheServletRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CacheServletRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CacheServletRequest cacheServletRequest = new CacheServletRequest(request);
        filterChain.doFilter(cacheServletRequest, response);
    }
}
