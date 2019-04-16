package com.melchior.vrolijk.secure_api.Secure.Web.API.utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This is the hashing class which hashing value by using SHA-512 Secure Hash Algorithm
 *
 * @author Melchior Vrolijk
 */
public class Hashing
{
    //region Local instance
    private static final String SHA_512 = "SHA-512";
    private static final String DEFAULT_VALUE = "";
    private static final String ADDITIONAL_STRING_VALUE = "0";
    private static final int SIGNUM = 1;
    private static final int RADIX = 16;
    private static final int HASH_TEXT_MAX_LENGTH = 32;
    //endregion

    //region Hash raw value
    /**
     * Hash raw by using the SHA-512 Hash Algorithm by using MessageDigest
     * @param raw_value The raw value
     * @return The hashed value
     */
    public static String hash(String raw_value)
    {
        try {

            MessageDigest messageDigestInstance = MessageDigest.getInstance(SHA_512);

            byte[] messageDigest = messageDigestInstance.digest(raw_value.getBytes());

            BigInteger signum_presentation = new BigInteger(SIGNUM,messageDigest);

            String hash_text = signum_presentation.toString(RADIX);

            while (hash_text.length() < HASH_TEXT_MAX_LENGTH)
            {
                hash_text = ADDITIONAL_STRING_VALUE + hash_text;
            }

            return hash_text;
        } catch (NoSuchAlgorithmException e)
        {
            return DEFAULT_VALUE;
        }
    }
    //endregion
}
