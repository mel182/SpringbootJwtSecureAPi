package com.melchior.vrolijk.secure_api.Secure.Web.API.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The e-mail validator singleton class
 *
 * @author Melchior Vrolijk
 */
public class EmailValidator
{
    //region Local instances
    private static EmailValidator instance;
    private Matcher matcher;
    private Pattern pattern;
    private static final String EMAIL_PATTERN = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
            +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
            +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

    //endregion

    //region E-mail validator private constructor
    /**
     * Email validator private constructor
     */
    private EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }
    //endregion

    //region Get email validator instance
    /**
     * Get email validator instance
     * @return {@link EmailValidator} instances
     */
    public static synchronized EmailValidator getInstance()
    {
        if (instance == null)
            instance = new EmailValidator();

        return instance;
    }
    //endregion

    //region Determine if e-mail address is valid
    /**
     * Determine if an email address is valid
     * @param email The raw e-mail address
     * @return 'True' if e-mail is valid and 'False' if invalid
     */
    public boolean isValidEmail(String email)
    {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    //endregion
}
