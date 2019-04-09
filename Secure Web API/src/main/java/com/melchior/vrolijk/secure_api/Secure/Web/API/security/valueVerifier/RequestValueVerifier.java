package com.melchior.vrolijk.secure_api.Secure.Web.API.security.valueVerifier;

import java.util.Arrays;

public class RequestValueVerifier
{

    private static final String[] InvalidValues = new String[] {"OR",";","="};

    public static boolean containInvalidValues(String value, boolean required)
    {
        if (required && value.equals(""))
            return true;

        return Arrays.stream(InvalidValues).anyMatch(value::equals);
    }

}
