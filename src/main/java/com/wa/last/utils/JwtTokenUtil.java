package com.wa.last.utils;


import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKey;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtTokenUtil {

    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("key.jks"); // 寻找证书文件
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;
    public static final int ONE_DAY_MILLISECOND = 86400000;
    public static Map<String, Object> headers = Maps.newHashMap();

    static { // 将证书文件里边的私钥公钥拿出来
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS"); // java key store 固定常量
            keyStore.load(inputStream, "seeMXzhj5bxc7loWLso10u5Wzq".toCharArray());
            headers.put("typ", "JWT");


            KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS512);
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateToken(Map<String, Object> additionalInformation, String subject, int expirationSeconds) {
        return Jwts.builder().setHeader(headers)
                .setClaims(additionalInformation)
                .setSubject(subject)
//                不要过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000L))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    public static String generateToken(Map<String, Object> additionalInformation, String subject, String expirationSeconds) {
        //过期时间为空
        if (StringUtils.isBlank(expirationSeconds)) {
            return generateToken(additionalInformation, subject);
        }
        return Jwts.builder().setHeader(headers)
                .setClaims(additionalInformation)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expirationSeconds) * 1000L))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    public static String generateToken(Map<String, Object> additionalInformation, String subject) {
        return Jwts.builder()
                .setHeader(headers)
                .setClaims(additionalInformation)
                .setSubject(subject)
//                不要过期时间
//                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(privateKey)
                .compact();
    }

    public static String parseToken(String token) {
        String subject = null;
        try {
//            Claims claims = Jwts.parser()
//                    .setSigningKey(publicKey)
//                    .parseClaimsJws(token)
//                    .getBody();
//            subject = claims.getSubject();
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token);
            System.out.println();
        } catch (Exception e) {
//            if (e instanceof ExpiredJwtException) {
//                throw e;
//            }
            log.error("ddd",e);
        }
        return subject;
    }

    public static String parseTokenMdid(String token) {
        String mdid = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();
            mdid = claims.get("mdid", String.class);
        } catch (Exception e) {
        }
        return mdid;
    }

    public static String parseTokenCliId(String token) {
        String clientId = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();
            clientId = claims.get("client_id", String.class);
        } catch (Exception e) {
        }
        return clientId;
    }



}
