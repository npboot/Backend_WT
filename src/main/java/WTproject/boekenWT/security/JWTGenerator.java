package WTproject.boekenWT.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class JWTGenerator {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication authentication){
        String username = authentication.getName();

        // bit of a hacky way to add authorities to the JWT
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> authorityStrings = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // Create a map to hold the claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", authorityStrings);
        // the hacky part end here


        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() +  SecurityConst.JWT_EXPIRATION);

        // Generate a secure key for HS512
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        String token = Jwts.builder()
                .setSubject(username)
                .addClaims(claims) // Add claims here (this contains user role)
                .setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS512)

                .compact();
        return token;
    }
    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

                return claims.getSubject();
    }

    public boolean validationToken(String token){
        try{
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (Exception exception) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }
}
