package br.com.zupacademy.fabio.mercadolivre.shared.config.security;

import br.com.zupacademy.fabio.mercadolivre.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtil {

    @Value("${banco.jwt.expiration}")
    private String expirationTime;
    @Value("${banco.jwt.expiration}")
    private String secret;

    public String generateToken(Authentication authentication){
        User loggedUser = (User) authentication.getPrincipal();
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + Long.parseLong(expirationTime));
        return Jwts.builder()
                .setIssuer("API Desafio Mercado Livre")
                .setSubject(loggedUser.getId().toString())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String retrieveToken(HttpServletRequest httpServletRequest) {
        String headerAuth = httpServletRequest.getHeader("Authorization");
        if(headerAuth == null || headerAuth.isEmpty() || !headerAuth.startsWith("Bearer ")){
            return null;
        }
        return headerAuth.substring(7); //Exatamente depois de 'Bearer '
    }

    public boolean isValidToken(String token) {
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public long getUserId(String token) {
        // aqui acessamos novamente o atributo subject que contêm o id do usuario
        String subject = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        return Long.parseLong(subject);
    }
}
