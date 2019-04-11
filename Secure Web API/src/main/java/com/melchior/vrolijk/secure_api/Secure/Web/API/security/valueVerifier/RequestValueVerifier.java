package com.melchior.vrolijk.secure_api.Secure.Web.API.security.valueVerifier;

import java.util.Arrays;

public class RequestValueVerifier
{

    private static final String[] InvalidValues = new String[] {"OR",";","="};

    public static boolean containInvalidValues(String value, boolean required)
    {
        System.out.println("value: "+value);
        if (required && value.equals(""))
            return true;

        System.out.println(Arrays.stream(InvalidValues).anyMatch(value::contains));
        return Arrays.stream(InvalidValues).anyMatch(value::contains);
    }

}
