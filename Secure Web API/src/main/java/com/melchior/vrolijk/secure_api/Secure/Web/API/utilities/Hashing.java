package com.melchior.vrolijk.secure_api.Secure.Web.API.utilities;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing
{
    // Hash password using the SHA-512 by using MessageDigest
    public static String hash(String raw_password)
    {
        try {

            MessageDigest messageDigestInstance = MessageDigest.getInstance("SHA-512");

            byte[] messageDigest = messageDigestInstance.digest(raw_password.getBytes());

            BigInteger signum_presentation = new BigInteger(1,messageDigest);

            String hash_text = signum_presentation.toString(16);

            while (hash_text.length() < 32)
            {
                hash_text = "0" + hash_text;
            }

            return hash_text;

        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
