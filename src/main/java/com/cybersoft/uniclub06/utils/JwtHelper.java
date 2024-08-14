package com.cybersoft.uniclub06.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.xml.crypto.Data;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtHelper { // class này giải quyết các giải mã token

    @Value("${jwts.key}")
    private String strKey;

    // tạo biến quy định thời gian hết hạn, đơn vị phải là mili giây
    private int expriredTime = 1 * 60 * 60 * 1000;

    public String generateToken(String data) {
        // Biến key kiểu string đã lưu trữ trước đó thành secretkey
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(strKey));

        Date currentData = new Date();
        Long miliSecondFuture = currentData.getTime() + expriredTime;
        Date dateFuture = new Date(miliSecondFuture);

        String token = Jwts.builder().signWith(secretKey).subject(data).compact();

        return token;
    }

    public String decodeToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(strKey));
        return  Jwts.parser()
                .verifyWith(secretKey).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


}
