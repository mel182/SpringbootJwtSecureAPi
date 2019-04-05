package com.melchior.vrolijk.secure_api.Secure.Web.API;

import com.melchior.vrolijk.secure_api.Secure.Web.API.confguration.SwaggerConfigurationTest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.confguration.TomCatRedirectConfigurationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		TomCatRedirectConfigurationTest.class,
		SwaggerConfigurationTest.class
})
@SpringBootTest
@EnableAutoConfiguration
public class SecureWebApiApplicationTests
{

}
