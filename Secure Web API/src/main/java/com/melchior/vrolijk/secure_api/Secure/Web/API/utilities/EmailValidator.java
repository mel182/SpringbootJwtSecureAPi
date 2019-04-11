package com.melchior.vrolijk.secure_api.Secure.Web.API.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator
{
    private static EmailValidator instance;
    private Matcher matcher;
    private Pattern pattern;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    private EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public static synchronized EmailValidator getInstance()
    {
        if (instance == null)
            instance = new EmailValidator();

        return instance;
    }

    public boolean isValidEmail(String email)
    {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
