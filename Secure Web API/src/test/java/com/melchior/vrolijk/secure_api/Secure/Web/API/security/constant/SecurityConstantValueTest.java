package com.melchior.vrolijk.secure_api.Secure.Web.API.security.constant;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SecurityConstantValueTest
{
    @Test
    public void TestRetrievingAuthorizationHeaderKey()
    {
        assertThat(SecurityConstantValue.AUTHORIZATION_HEADER_KEY).isEqualTo("Authorization");
    }

    @Test
    public void TestRetrievingAuthorizationPrefix()
    {
        assertThat(SecurityConstantValue.AUTHORIZATION_PREFIX).isEqualTo("Bearer");
    }

    @Test
    public void TestRetrievingUserRoleJWT_Claim_Key()
    {
        assertThat(SecurityConstantValue.USER_ROLE).isEqualTo("user_role");
    }

    @Test
    public void TestRetrievingUserIDJWT_Claim_Key()
    {
        assertThat(SecurityConstantValue.USER_ID).isEqualTo("user_id");
    }

    @Test
    public void TestRetrievingRootAuthorizationRequiredDescriptionText()
    {
        assertThat(SecurityConstantValue.ROOT_AUTHORIZATION_REQUIRED).isEqualTo("Root user authentication token 'Example: Bearer + token'");
    }

    @Test
    public void TestRetrievingAdminAuthorizationRequiredDescriptionText()
    {
        assertThat(SecurityConstantValue.ADMIN_AUTHORIZATION_REQUIRED).isEqualTo("Admin user authentication token 'Example: Bearer + token'");
    }

    @Test
    public void TestRetrievingAuthorizationRequiredDescriptionText()
    {
        assertThat(SecurityConstantValue.AUTHORIZATION_REQUIRED).isEqualTo("Authentication token 'Example: Bearer + token'");
    }

    @Test
    public void TestRetrievingSwaggerUI_API_Only_Used_By_Root_User_AuthorizationDescriptionText()
    {
        assertThat(SecurityConstantValue.ONLY_USED_BY_ROOT_USER).isEqualTo("This API can ONLY be used by the root user");
    }

    @Test
    public void TestRetrievingSwaggerUI_API_Only_Used_By_Admin_User_AuthorizationDescriptionText()
    {
        assertThat(SecurityConstantValue.ONLY_USED_BY_ADMIN).isEqualTo("This API can ONLY be used by admin");
    }
}