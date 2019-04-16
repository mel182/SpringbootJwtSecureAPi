package com.melchior.vrolijk.secure_api.Secure.Web.API.database.initialData;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.UserRole;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.UserEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.repository.AuthenticatedUserRepository;
import com.melchior.vrolijk.secure_api.Secure.Web.API.utilities.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeUserTable implements ApplicationRunner
{
    private AuthenticatedUserRepository authenticatedUserRepository;
    private static final String ROOT_USER_PASSWORD = "5dcad19b31396b729a484bd84b39a0b268511ba11b9359d37051c5bd02a0db7f0dd96296165b63f73acb6f0146fc5cab1ac90f050c86085c0815a88cd103e2e0";
    private static final String ROOT_USER_NAME = "admin@application.com";
    private static final String ROOT_USER_FIRST_NAME = "Root";
    private static final String ROOT_USER_LAST_NAME = "User";
    private static final String ROOT_USER_OCCUPATION = "admin@application.com";

    @Autowired
    public InitializeUserTable(AuthenticatedUserRepository authenticatedUserRepository)
    {
        this.authenticatedUserRepository = authenticatedUserRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        if (authenticatedUserRepository.findAll().isEmpty())
        {
            UserEntity rootUser = new UserEntity();
            rootUser.setEmail(ROOT_USER_NAME);
            rootUser.setPassword(Hashing.hash(ROOT_USER_PASSWORD));
            rootUser.setFirstName(ROOT_USER_FIRST_NAME);
            rootUser.setLastName(ROOT_USER_LAST_NAME);
            rootUser.setOccupation(ROOT_USER_OCCUPATION);
            rootUser.setRole(UserRole.ROOT.toString());
            rootUser.setCreated(System.currentTimeMillis());
            rootUser.setUpdated(System.currentTimeMillis());
            rootUser.setAccountLocked(false);

            authenticatedUserRepository.save(rootUser);
        }
    }
}
