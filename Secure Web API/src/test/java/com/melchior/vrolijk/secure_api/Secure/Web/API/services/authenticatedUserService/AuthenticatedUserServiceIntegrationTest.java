package com.melchior.vrolijk.secure_api.Secure.Web.API.services.authenticatedUserService;

import com.melchior.vrolijk.secure_api.Secure.Web.API.customException.UserAlreadyExistException;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.AuthenticatedUser;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.NewUserAuthenticationRequest;
import com.melchior.vrolijk.secure_api.Secure.Web.API.model.ResponseUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.melchior.vrolijk.secure_api.Secure.Web.API.demoObject.DemoObject.createNewUpdatedAuthenticationUser;
import static com.melchior.vrolijk.secure_api.Secure.Web.API.demoObject.DemoObject.createNewUserAuthentication;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticatedUserServiceIntegrationTest
{
    @Autowired
    AuthenticatedUserService authenticatedUserService;

    private NewUserAuthenticationRequest newUserAuthenticationRequest;

    @Before
    public void Setup()
    {
        this.newUserAuthenticationRequest = createNewUserAuthentication();
        this.authenticatedUserService.clearAll();
    }

    @Test
    public void Test_Save_New_User() throws UserAlreadyExistException
    {
        AuthenticatedUser newUser = this.saveData(this.newUserAuthenticationRequest);
        assertThat(newUser.getEmail()).isEqualTo(this.newUserAuthenticationRequest.getEmail());
        assertThat(this.authenticatedUserService.removeUser(newUser.getId())).isNotEqualTo(null);
    }

    @Test
    public void Test_Updating_User() throws UserAlreadyExistException {
        AuthenticatedUser insertedUser = this.authenticatedUserService.save(newUserAuthenticationRequest);
        NewUserAuthenticationRequest updatedUserTestData = createNewUpdatedAuthenticationUser();
        updatedUserTestData.setId(insertedUser.getId());
        ResponseUser newUser = this.authenticatedUserService.updateUser(updatedUserTestData);
        assertThat(newUser.getFirstName()).isEqualTo(updatedUserTestData.getFirstName());
        assertThat(this.removeUser(newUser.getId())).isNotEqualTo(null);
    }

    @Test
    public void Test_GetAll_Users() throws UserAlreadyExistException {
        this.saveData(this.newUserAuthenticationRequest);
        assertThat(this.authenticatedUserService.getAll()).isNotEqualTo(null);
    }

    @Test
    public void Test_Removing_Demo_Users() throws UserAlreadyExistException {
        AuthenticatedUser insertedUser = this.saveData(newUserAuthenticationRequest);
        assertThat(this.removeUser(insertedUser.getId())).isNotEqualTo(null);
    }

    private AuthenticatedUser saveData(NewUserAuthenticationRequest newUserAuthenticationRequest) throws UserAlreadyExistException {

        return this.authenticatedUserService.save(newUserAuthenticationRequest);
    }

    private ResponseUser removeUser(long ID) throws UserAlreadyExistException
    {
        return this.authenticatedUserService.removeUser(ID);
    }

    @After
    public void Close()
    {
        this.newUserAuthenticationRequest = null;
    }
}