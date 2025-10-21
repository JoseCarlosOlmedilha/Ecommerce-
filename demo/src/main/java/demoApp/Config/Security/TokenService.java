package demoApp.Config.Security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import demoApp.Entities.Usuario;
import demoApp.Exception.TokenException;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    
    public String gerarToken(Usuario usuario){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer("API TCC")
                .withSubject(usuario.getLogin())
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new TokenException("Erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenjwt){
        try {
           var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
            .withIssuer("API TCC")
            .build()
            .verify(tokenjwt)
            .getSubject();
        } catch (JWTVerificationException exception){
            throw new TokenException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
