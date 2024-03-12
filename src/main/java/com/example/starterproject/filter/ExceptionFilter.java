package com.example.starterproject.filter;

import com.example.starterproject.exception.TokenNotFoundException;
import com.example.starterproject.exception.TokenNotValidException;
import com.example.starterproject.exception.common.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

/**
 * Filter 에서 발생하는 Exception은 Controller Adivce로 처리가 불가능하므로 필터를 하나 더 생성해서 처리
 */
@Slf4j
public class ExceptionFilter extends OncePerRequestFilter {
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String [] excludePath = {"/api/signup", "/api/login", "/swagger", "/v3/api" , "/h2-console", "/error"};
        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (TokenNotFoundException e) {
            setResponse(response, ErrorCode.TOKEN_NOT_FOUND);
        } catch (TokenNotValidException e) {
            setResponse(response, ErrorCode.TOKEN_NOT_VALID);
        }
    }

    private void setResponse(HttpServletResponse response, ErrorCode err) {
        response.setStatus(err.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            response.getWriter().write(err.getMessage());
        } catch (IOException e) {
            log.error("FilterChain IOException",e);
        }
    }
}
