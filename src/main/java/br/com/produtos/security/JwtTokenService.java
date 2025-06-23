package br.com.produtos.security;

import br.com.produtos.entidade.UsuarioDetailsImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {
    @Value("${jwt.key.secret}")
    private String kEY_SECRET;
    private static final String ISSUER="produto";

    public String generateToken(UsuarioDetailsImpl usuarioDetails){
        Algorithm algorithm = Algorithm.HMAC256(kEY_SECRET);
        try {
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(expirationDate())
                    .withSubject(usuarioDetails.getUsername())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new JWTCreationException("Erro ao gerar Token",e);
        }

    }
    public String getSubjectFromToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(kEY_SECRET);
        try {
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Token inválido ou expirado.",e);
        }
    }
    private Instant creationDate(){
        return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
    }
    private Instant expirationDate(){
        return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(4).toInstant();
    }
}
