package com.Matheus.GestaoFinanceira.Config;

import com.Matheus.GestaoFinanceira.Exceptions.security.TokenCreateException;
import com.Matheus.GestaoFinanceira.User.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class TokenService {

    @Value("${JWT_SECRET}")
    private String secretToken;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretToken);
            String token = JWT.create()
                    .withIssuer("b7-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expireDate())
                    .sign(algorithm);
            return token;

        } catch (JWTCreationException exception) {
            throw new TokenCreateException();
        }
    }

    public String verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretToken);
            return JWT.require(algorithm)
                    .withIssuer("b7-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant expireDate(){
        return Instant.now().plus(2, ChronoUnit.HOURS);
    }
}
