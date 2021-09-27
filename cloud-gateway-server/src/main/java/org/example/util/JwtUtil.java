package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class JwtUtil {

    public static final Long JWT_TTL = 3600000L;
    public static final String JWT_KEY = "35bf1958";

    public static String createJWT(String id, String subject, Long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long now = System.currentTimeMillis();
        Date nowDate = new Date(now);
        if (!Optional.ofNullable(ttlMillis).isPresent()) {
            ttlMillis = JwtUtil.JWT_TTL;
        }

        long expire = now + ttlMillis;
        Date expireDate = new Date(expire);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuer("admin")
                .setIssuedAt(nowDate)
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expireDate);
        return builder.compact();
    }

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static void main(String[] args) {
        System.out.println(createJWT(UUID.randomUUID().toString(), "{\"userName\":\"chenkaikai\"}", 10000l));
    }
}
