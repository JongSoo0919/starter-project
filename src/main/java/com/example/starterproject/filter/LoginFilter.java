package com.example.starterproject.filter;

import com.example.starterproject.dto.user.UserLoginDetails;
import com.example.starterproject.dto.user.request.UserLoginRequest;
import com.example.starterproject.exception.ParameterNotValidException;
import com.example.starterproject.exception.common.ErrorCode;
import com.example.starterproject.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        try {
            UserLoginRequest userLoginRequest = OBJECT_MAPPER.readValue(request.getInputStream(), UserLoginRequest.class);
            String userId = userLoginRequest.email() == null ? "" : userLoginRequest.email().trim();
            String password = userLoginRequest.password() == null ? "" : userLoginRequest.password();

            StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userId, password, null));
        } catch (IOException e) {
            throw new ParameterNotValidException(ErrorCode.PARAMETER_NOT_VALID);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserLoginDetails userLoginDetails = (UserLoginDetails) authResult.getPrincipal();
        String email = userLoginDetails.getUsername();
        String name = userLoginDetails.getName();

        String token = jwtUtil.createToken(email, name);
        response.addHeader("Authorization", "Bearer " + token);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(userLoginDetails, null, userLoginDetails.getAuthorities());
        userLoginDetails.setAccessToken(token);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }
}
