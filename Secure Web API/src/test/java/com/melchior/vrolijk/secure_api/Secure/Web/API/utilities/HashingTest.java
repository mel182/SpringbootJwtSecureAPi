package com.melchior.vrolijk.secure_api.Secure.Web.API.utilities;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HashingTest
{
    private static final String TEST_VALUE = "test value";

    @Test
    public void hashingTest()
    {
        assertThat(Hashing.hash(TEST_VALUE)).isNotEqualTo(null);
    }

}