package com.melchior.vrolijk.secure_api.Secure.Web.API.utilities;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class EmailValidatorTest {

    @Test
    public void getInstance() {
        assertThat(EmailValidator.getInstance()).isNotEqualTo(null);
    }

    @Test
    public void validEmailTest() {
        String testEmail = "test@test.com";
        assertThat(EmailValidator.getInstance().isValidEmail(testEmail)).isEqualTo(true);
    }

    @Test
    public void InValidEmailWithoutAtSignTest() {
        String testEmail = "test_capgemini.com";
        assertThat(EmailValidator.getInstance().isValidEmail(testEmail)).isEqualTo(false);
    }

    @Test
    public void InValidEmailWithoutDomainTest() {
        String testEmail = "test@capgeminicom";
        assertThat(EmailValidator.getInstance().isValidEmail(testEmail)).isEqualTo(false);
    }
}