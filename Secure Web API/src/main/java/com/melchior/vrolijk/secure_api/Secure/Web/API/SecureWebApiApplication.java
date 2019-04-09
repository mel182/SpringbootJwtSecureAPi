package com.melchior.vrolijk.secure_api.Secure.Web.API;

import com.melchior.vrolijk.secure_api.Secure.Web.API.security.JwtTokenDataRetrieval;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.JwtTokenGenerator;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.JwtTokenValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecureWebApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureWebApiApplication.class, args);

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
