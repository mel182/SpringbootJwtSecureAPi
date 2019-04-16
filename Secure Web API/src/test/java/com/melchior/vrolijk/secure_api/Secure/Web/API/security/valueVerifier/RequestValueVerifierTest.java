package com.melchior.vrolijk.secure_api.Secure.Web.API.security.valueVerifier;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestValueVerifierTest
{
    private static final String VALID_DATA = "TEST";
    private static final String BLANK_DATA = "";
    private static final String INVALID_DATA = "TEST OR 1=1;";

    @Test
    public void testRequestValidRequiredValue()
    {
        assertThat(RequestValueVerifier.containInvalidValues(VALID_DATA,true)).isEqualTo(false);
    }

    @Test
    public void testBlankRequiredValue()
    {
        assertThat(RequestValueVerifier.containInvalidValues(BLANK_DATA,true)).isEqualTo(true);
    }

    @Test
    public void testRejectSQLInjectionDataRequest()
    {
        assertThat(RequestValueVerifier.containInvalidValues(INVALID_DATA,true)).isEqualTo(true);
        assertThat(RequestValueVerifier.containInvalidValues(INVALID_DATA,false)).isEqualTo(true);
    }
}