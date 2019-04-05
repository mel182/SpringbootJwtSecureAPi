package com.melchior.vrolijk.secure_api.Secure.Web.API.confguration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SwaggerConfigurationTest {

    //region Instances
    private SwaggerConfiguration swaggerConfiguration;
    //endregion

    //region Create instances ('@Before')
    /**
     * Create the necessary instances for conducting the tests
     */
    @Before
    public void createInstances()
    {
        swaggerConfiguration = new SwaggerConfiguration();
    }
    //endregion

    //region Test swagger docket api
    /**
     * Test the swagger docket api by creating an instance and check if it successfully created.
     */
    @Test
    public void TestSwaggerDocketApi() {
        assertThat(swaggerConfiguration.api()).isNotEqualTo(null);
    }
    //endregion

    //region De-initialize instances
    /**
     * De-initialize instances
     */
    @After
    public void deInitializeInstances()
    {
        if (swaggerConfiguration != null)
            swaggerConfiguration = null;
    }
    //endregion
}