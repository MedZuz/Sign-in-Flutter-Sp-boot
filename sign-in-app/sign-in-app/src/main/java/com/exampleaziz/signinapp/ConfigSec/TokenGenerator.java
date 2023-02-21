package com.exampleaziz.signinapp.ConfigSec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {

    public String generaToken(Authentication authentication){

        String username = authentication.getName();
        Date currentDate = new Date();
        Long JWTexpirationDate = Long.valueOf(70000);

        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRE);


        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
                .compact();

        return  token;

    }

    public String getUsernameFromJWT (String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
            return true ;
        }catch (Exception ex){
                throw new AuthenticationCredentialsNotFoundException(" jwt expired or incorrect username ") ;
        }
    }

}
