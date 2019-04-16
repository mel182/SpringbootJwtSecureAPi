package com.melchior.vrolijk.secure_api.Secure.Web.API;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.UserEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.JwtTokenDataRetrieval;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.JwtTokenGenerator;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.JwtTokenValidator;
import com.melchior.vrolijk.secure_api.Secure.Web.API.utilities.EmailValidator;
import com.melchior.vrolijk.secure_api.Secure.Web.API.utilities.Hashing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

@SpringBootApplication
public class SecureWebApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureWebApiApplication.class, args);
	}
}
