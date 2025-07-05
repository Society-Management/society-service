package com.societymanagement.society_service.filter;

import com.societymanagement.society_service.client.AuthClient;
import com.societymanagement.society_service.dtos.TokenRequest;
import com.societymanagement.society_service.dtos.TokenResponse;
import com.societymanagement.society_service.exception.CustomException;
import com.societymanagement.society_service.utils.JwtUserPrincipal;
import com.societymanagement.society_service.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtFilter  extends OncePerRequestFilter{

    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    private AuthClient authClient;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                TokenRequest req = new TokenRequest(token);
                TokenResponse res = authClient.validateToken(req).getBody();

                if (res != null && res.isValid()) {
                    Claims claims = jwtUtil.extractAllClaims(token);
                    Long userId = ((Number) claims.get("userId")).longValue();
                    Long societyId = ((Number) claims.get("societyId")).longValue();
                    List<String> roles = (List<String>) claims.get("roles");
                    List<GrantedAuthority> authorities = roles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .collect(Collectors.toList());
                    JwtUserPrincipal principal = new JwtUserPrincipal(userId,societyId);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(principal, null, authorities);

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                throw new CustomException("Invalid token");
            }
        }

        chain.doFilter(request, response);
    }

}