package WTproject.boekenWT.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JWTGenerator {

    public String generateToken(Authentication authentication){
        String username = authentication.name();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() +  SecurityConst.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.ES512, SecurityConst.JWT_SECRET)
                .compact();
        return token;
    }
    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConst.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

                return claims.getSubject();
    }

    public boolean validationToken(String token){
        try{
            Jwts.parser().setSigningKey(SecurityConst.JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception exception) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }
}
