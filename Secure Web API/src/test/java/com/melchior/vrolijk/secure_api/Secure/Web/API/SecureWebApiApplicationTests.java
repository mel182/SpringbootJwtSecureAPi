package com.melchior.vrolijk.secure_api.Secure.Web.API;

import com.melchior.vrolijk.secure_api.Secure.Web.API.confguration.SwaggerConfigurationTest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.confguration.TomCatRedirectConfigurationTest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.converter.ConverterTest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant.SecurityConstantValueTest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.security.valueVerifier.RequestValueVerifierTest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.services.authenticatedUserService.AuthenticatedUserServiceIntegrationTest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.utilities.EmailValidatorTest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.utilities.HashingTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		SecureWebApiApplicationMainTest.class,
		AuthenticatedUserServiceIntegrationTest.class,
		TomCatRedirectConfigurationTest.class,
		SwaggerConfigurationTest.class,
		EmailValidatorTest.class,
		HashingTest.class,
		ConverterTest.class,
		RequestValueVerifierTest.class,
		SecurityConstantValueTest.class
})
@SpringBootTest
@EnableAutoConfiguration
public class SecureWebApiApplicationTests
{

}
