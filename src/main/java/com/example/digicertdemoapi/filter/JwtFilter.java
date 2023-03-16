package com.example.digicertdemoapi.filter;

import com.example.digicertdemoapi.utill.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
public class JwtFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      @NonNull HttpServletResponse httpServletResponse,
      @NonNull FilterChain filterChain) throws ServletException, IOException {

    String authorizationHeader = httpServletRequest.getHeader("Authorization");

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String token = authorizationHeader.substring(7);
      try {
        final Claims claims = JwtUtils.verify(token);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } else {
      throw new AccessDeniedException("Access Denied");
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
