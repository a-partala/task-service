package net.partala.taskservice.auth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.partala.taskservice.auth.SecurityUser;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private static final String TOKEN_TYPE = "Bearer ";
    private final AuthenticationEntryPoint authEntryPoint;

    public JwtAuthenticationFilter(JwtService jwtService, AuthenticationEntryPoint authEntryPoint) {
        this.jwtService = jwtService;
        this.authEntryPoint = authEntryPoint;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith(TOKEN_TYPE)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(TOKEN_TYPE.length());

        try {
            if(SecurityContextHolder.getContext().getAuthentication() == null) {

                if(jwtService.extractPurpose(token) != TokenPurpose.ACCESS) {
                    throw new BadCredentialsException("Invalid token format or signature");
                }

                var userId = jwtService.extractUserId(token);
                var roles = jwtService.extractRoles(token);
                var securityUser = new SecurityUser(userId, roles);
                var auth = new UsernamePasswordAuthenticationToken(
                        securityUser,
                        null,
                        securityUser.getAuthorities()
                );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (ExpiredJwtException e) {
            authEntryPoint.commence(request, response, new CredentialsExpiredException("Token expired"));
        } catch (Exception e) {
            authEntryPoint.commence(request, response, new BadCredentialsException("Invalid token format or signature"));
        }

        filterChain.doFilter(request, response);
    }
}
