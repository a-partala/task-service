package net.partala.taskservice.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import net.partala.taskservice.config.JwtProperties;
import net.partala.taskservice.user.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@ConfigurationProperties(prefix = "app.jwt")
public class JwtService {

    private final Logger log = LoggerFactory.getLogger(JwtService.class);
    private final JwtProperties properties;

    public JwtService(JwtProperties properties) {
        this.properties = properties;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(properties.secret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Long extractUserId(String token) {
        return parseAllClaims(token).get("userId", Long.class);
    }

    public TokenPurpose extractPurpose(String token) {
        String purposeStr = parseAllClaims(token).get("purpose", String.class);
        try {
            return TokenPurpose.valueOf(purposeStr);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new BadCredentialsException("Invalid token format or signature");
        }
    }

    public String extractUsername(String token) {

        return parseAllClaims(token).getSubject();
    }

    public Instant extractExpiration(String token) {

        Date expire = parseAllClaims(token).getExpiration();
        return expire.toInstant();
    }

    private Claims parseAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Set<UserRole> extractRoles(String token) {

        List<String> roleNames = parseAllClaims(token).get("roles", List.class);
        return roleNames.stream()
                .map(UserRole::valueOf)
                .collect(Collectors.toSet());
    }
}
