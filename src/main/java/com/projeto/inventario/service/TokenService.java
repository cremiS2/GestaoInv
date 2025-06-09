package com.projeto.inventario.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.projeto.inventario.entities.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenService {

    @Value("${jwt.secret:minha-chave-secreta-super-secreta-de-256-bits-para-jwt-que-deve-ser-mantida-em-segredo}")
    private String secret;

    @Value("${jwt.expiration:86400000}")
    private Long expiration;

    public String gerarToken(Usuario usuario) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .subject(usuario.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    public String getSubject(String token) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(secret.getBytes());

            Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}