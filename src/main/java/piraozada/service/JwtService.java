package piraozada.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")          // pega a chave secreta do application.properties
    private String secret;

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());  // converte a string em chave criptográfica
    }

    // Gera um token JWT para o usuário
    public String gerarToken(String username, String role) {
        return Jwts.builder()
                .subject(username)                                         // quem é o dono do token
                .claim("role", role)                                       // qual o papel do usuário
                .issuedAt(new Date())                                      // quando foi gerado
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // expira em 24h
                .signWith(getKey())                                        // assina com a chave secreta
                .compact();                                                // gera a string final
    }

    // Extrai o username do token
    public String extrairUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Extrai o role do token
    public String extrairRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    // Verifica se o token é válido
    public boolean tokenValido(String token) {
        try {
            getClaims(token);   // se não lançar exceção, é válido
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Lê os dados dentro do token
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}