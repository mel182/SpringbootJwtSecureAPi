package com.melchior.vrolijk.secure_api.Secure.Web.API.confguration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TomCatRedirectConfigurationTest {

    //region Necessary instances
    private TomCatRedirectConfiguration tomCatRedirectConfiguration;
    //endregion

    //region Create necessary instance (containing '@Before' annotation)
    /**
     * This function create the necessary instances used within the tests
     */
    @Before
    public void createNecessaryInstance()
    {
        tomCatRedirectConfiguration = new TomCatRedirectConfiguration();
    }
    //endregion

    //region Test the servlet container which redirect 'http' calls to 'https'
    /**
     * <p>Test the creation process of the TomCat server servlet container instance which redirect 'http' calls to 'https'</p>
     * <p>In case the servlet container returns 'null' the test failed other wise it will passed.</p>
     * <p>At the moment testing if it indeed redirect 'http' calls to 'https' is done through PostMan</p>
     */
    @Test
    public void TestServletContainer()
    {
        assertThat(tomCatRedirectConfiguration.servletContainer()).isNotEqualTo(null);
    }
    //endregion

    //region de-initialize all necessary instance (containing '@After' annotation)
    /**
     * This function de-initialize all the necessary instance used within all tasks
     */
    @After
    public void deInitializeNecessaryInstance()
    {
        deInitializeTomCatRedirectConfiguration();
    }
    //endregion

    //region De-initialize tom cat redirect configuration
    /**
     * De-initialize the Tom Cat redirect configuration instance
     */
    private void deInitializeTomCatRedirectConfiguration()
    {
        if (this.tomCatRedirectConfiguration != null)
        {
            tomCatRedirectConfiguration = null;
        }
    }
    //endregion
}