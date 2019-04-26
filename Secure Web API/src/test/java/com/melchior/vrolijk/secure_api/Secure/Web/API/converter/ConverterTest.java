package com.melchior.vrolijk.secure_api.Secure.Web.API.converter;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.UserEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.demoObject.DemoObject;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.NewUserAuthenticationRequest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This is the converter test class
 * @author Melchior Vrolijk
 */
public class ConverterTest
{
    @Test
    public void convertToUserEntity()
    {
        NewUserAuthenticationRequest newUserAuthenticationRequest = DemoObject.createNewAuthenticationRequestUserTestObject();
        UserEntity userEntity = Converter.convertToUserEntity(newUserAuthenticationRequest);
        assertThat(userEntity).isNotEqualTo(null);
    }

    @Test
    public void convertToAuthenticatedUser()
    {
        assertThat(DemoObject.createUserEntity()).isNotEqualTo(null);
    }
}