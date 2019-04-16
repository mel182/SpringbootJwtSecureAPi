package com.melchior.vrolijk.secure_api.Secure.Web.API.demoObject;

import com.melchior.vrolijk.secure_api.Secure.Web.API.converter.Converter;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.UserEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.AuthenticationRequest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.NewUserAuthenticationRequest;

public class DemoObject
{
    private static final String TEST_EMAIL = "test@test.com";
    private static final String TEST_PASSWORD = "passwordTest";
    public static long DEFAUL_USER_ID = -1;

    public static NewUserAuthenticationRequest createNewUserAuthentication()
    {
        NewUserAuthenticationRequest newUserAuthenticationRequest = new NewUserAuthenticationRequest();
        newUserAuthenticationRequest.setId(1L);
        newUserAuthenticationRequest.setFirstName("TestUser");
        newUserAuthenticationRequest.setLastName("LTestUser");
        newUserAuthenticationRequest.setOccupation("TestOccupation");
        newUserAuthenticationRequest.setEmail(TEST_EMAIL);
        newUserAuthenticationRequest.setPassword(TEST_PASSWORD);
        newUserAuthenticationRequest.setRole("USER");

        return newUserAuthenticationRequest;
    }

    public static NewUserAuthenticationRequest createNewUpdatedAuthenticationUser()
    {
        AuthenticationRequest authenticationRequest = createAuthenticationRequestTest();

        NewUserAuthenticationRequest newUserAuthenticationRequest = new NewUserAuthenticationRequest();
        newUserAuthenticationRequest.setId(1L);
        newUserAuthenticationRequest.setFirstName("TestUser1");
        newUserAuthenticationRequest.setLastName("LTestUser1");
        newUserAuthenticationRequest.setOccupation("TestOccupation1");
        newUserAuthenticationRequest.setEmail(authenticationRequest.getEmail());
        newUserAuthenticationRequest.setPassword(authenticationRequest.getPassword());
        newUserAuthenticationRequest.setRole("USER");

        return newUserAuthenticationRequest;
    }

    public static NewUserAuthenticationRequest createNewAuthenticationRequestUserTestObject()
    {
        AuthenticationRequest authenticationRequest = createAuthenticationRequestTest();

        NewUserAuthenticationRequest newUserAuthenticationRequest = new NewUserAuthenticationRequest();
        newUserAuthenticationRequest.setId(1L);
        newUserAuthenticationRequest.setFirstName("TestUser");
        newUserAuthenticationRequest.setLastName("LTestUser");
        newUserAuthenticationRequest.setOccupation("TestOccupation");
        newUserAuthenticationRequest.setEmail(authenticationRequest.getEmail());
        newUserAuthenticationRequest.setPassword(authenticationRequest.getPassword());
        newUserAuthenticationRequest.setRole("USER");

        return newUserAuthenticationRequest;
    }

    public static AuthenticationRequest createAuthenticationRequestTest()
    {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail(TEST_EMAIL);
        authenticationRequest.setPassword(TEST_PASSWORD);

        return authenticationRequest;
    }

    public static UserEntity createUserEntity()
    {
        return Converter.convertToUserEntity(createNewAuthenticationRequestUserTestObject());
    }
}
