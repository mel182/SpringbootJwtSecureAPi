package com.melchior.vrolijk.secure_api.Secure.Web.API.security.valueVerifier;

import java.util.Arrays;

/**
 * This is the request value varifier class
 * @author Melchior Vrolijk
 */
@SuppressWarnings("ALL")
public class RequestValueVerifier
{
    //region Local instances
    private static final String[] InvalidValues = new String[] {"OR",";","="};
    //endregion

    //region Determine if raw value contain invalid values
    /**
     * Determine if value contain invalid values
     * @param value The raw value
     * @param required 'True' if it is a required value and 'False' if not
     * @return 'True' if it contain invalid values and 'False' if it does not contain invalid values
     */
    public static boolean containInvalidValues(String value, boolean required)
    {
        if (required && value.equals(""))
            return true;

        return Arrays.stream(InvalidValues).anyMatch(value::contains);
    }
    //endregion
}
