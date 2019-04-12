package com.melchior.vrolijk.secure_api.Secure.Web.API.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.USER_ID;
import static com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValue.USER_ROLE;

@Component
public class JwtTokenGenerator {

    @Value("${security.jwt.token.secret-key:secret}")
    private static final String SECRET_KEY = "MmZiZjM2YWVmYzU1MTZkZDY1MzUwNDRkYzU5NDNiZTg5ZTBiNTRjMjZkMGViNzg3YmI1ZTIwNDFkNTcwMGYxMg==";

    @Value("${security.jwt.token.expire-length:1800000}")
    private static final long VALIDITY_IN_MILLISECONDS = 1800000;

    public String createToken(UserEntity userEntity)
    {
        Date createdDate = new Date();
        Date validity = new Date(createdDate.getTime() + VALIDITY_IN_MILLISECONDS);

        System.out.println("Role: "+userEntity.getRole());
        return JWT
                .create()
                .withSubject(userEntity.getEmail())
                .withClaim(USER_ROLE,userEntity.getRole().toString())
                .withClaim(USER_ID,userEntity.getId())
                .withIssuedAt(createdDate)
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }






}
