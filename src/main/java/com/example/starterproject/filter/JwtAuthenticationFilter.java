package com.example.starterproject.filter;

import com.example.starterproject.dto.user.UserLoginDetails;
import com.example.starterproject.exception.TokenNotFoundException;
import com.example.starterproject.exception.TokenNotValidException;
import com.example.starterproject.exception.UserNotFoundException;
import com.example.starterproject.exception.common.ErrorCode;
import com.example.starterproject.service.user.UserService;
import com.example.starterproject.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String [] excludePath = {"/api/signup", "/api/login", "/swagger", "/v3/api" , "/h2-console", "/error"};
        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization == null || ObjectUtils.isEmpty(authorization)) {
            throw new TokenNotFoundException(ErrorCode.TOKEN_NOT_FOUND);
        }

        String token = authorization.split("Bearer ")[1];

        if (token == null || ObjectUtils.isEmpty(token) || !jwtUtil.validateToken(token)) {
            throw new TokenNotValidException(ErrorCode.TOKEN_NOT_VALID);
        }

        String email = jwtUtil.getEmail(token);
        UserLoginDetails userLoginDetails = (UserLoginDetails) userService.loadUserByUsername(email);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userLoginDetails, null, userLoginDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request, response);
    }
}
