package com.melchior.vrolijk.secure_api.Secure.Web.API;

import com.melchior.vrolijk.secure_api.Secure.Web.API.security.JwtTokenDataRetrieval;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.JwtTokenGenerator;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.JwtTokenValidator;
import com.melchior.vrolijk.secure_api.Secure.Web.API.utilities.Hashing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

@SpringBootApplication
public class SecureWebApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureWebApiApplication.class, args);


//		String test = Hashing.hash("123");
//		System.out.println("Hash value: "+test);


//		String token = JwtTokenGenerator.createToken("test","admin");
//
//		System.out.println("token: "+token);
//
//		System.out.println("Valid token: "+ JwtTokenValidator.isTokenvalid(token));
//
//		String username = JwtTokenDataRetrieval.getUserName(token);
//
//		System.out.println("User name: "+username);

//		Authentication authentication = new JwtTokenGenerator().getAuthentication(token);
//
//		System.out.println("Principle: "+authentication.getPrincipal().toString());
//
//		System.out.println("Authorities: "+authentication.getAuthorities().toString());
//
//		System.out.println("Credentials: "+authentication.getCredentials().toString());
//
//		System.out.println("Details: "+authentication.getDetails().toString());
//
//		System.out.println("Is authenticated: "+authentication.isAuthenticated());
	}

}
