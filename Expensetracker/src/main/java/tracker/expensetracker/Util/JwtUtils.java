package tracker.expensetracker.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
@Slf4j
public class JwtUtils {
    private JwtUtils() {
    }
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static String generateExcessToken(String email) {
        var issuedAt = new Date();

        return Jwts.builder()
                .issuer("ET_USER_AUTH_SERVICE")
                .subject(email)
                .id(UUID.randomUUID().toString())
                .issuedAt(issuedAt)
                .expiration(Date.from(
                        Instant.now()
                                .plus(15 , ChronoUnit.MINUTES)
                )).signWith(SECRET_KEY)
                .compact();
    }

    public static Optional<String> getUsernameFromToken(String accessToken) {
        return parseToken(accessToken)
                .map(Claims::getSubject);
    }

    private static Optional<Claims> parseToken(String accessToken) {
       var jwtParser = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build();

       try {
           return Optional.of( jwtParser.parseSignedClaims(accessToken)
                   .getPayload());
       } catch (JwtException | IllegalArgumentException e) {
           return Optional.empty();
       }
    }
}
