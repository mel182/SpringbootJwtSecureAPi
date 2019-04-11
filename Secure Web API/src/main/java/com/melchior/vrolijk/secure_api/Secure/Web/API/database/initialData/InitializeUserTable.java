package com.melchior.vrolijk.secure_api.Secure.Web.API.database.initialData;

import com.melchior.vrolijk.secure_api.Secure.Web.API.database.dbEnum.UserRole;
import com.melchior.vrolijk.secure_api.Secure.Web.API.database.entity.UserEntity;
import com.melchior.vrolijk.secure_api.Secure.Web.API.repository.AuthenticatedUserRepository;
import com.melchior.vrolijk.secure_api.Secure.Web.API.services.authenticatedUserService.AuthenticatedUserService;
import com.melchior.vrolijk.secure_api.Secure.Web.API.utilities.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeUserTable implements ApplicationRunner
{

    private AuthenticatedUserRepository authenticatedUserRepository;

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
            rootUser.setEmail("admin@application.com");
            rootUser.setPassword(Hashing.hash("admin123"));
            rootUser.setFirstName("Root");
            rootUser.setLastName("User");
            rootUser.setOccupation("Admin");
            rootUser.setRole(UserRole.ROOT.toString());
            rootUser.setCreated(System.currentTimeMillis());
            rootUser.setUpdated(System.currentTimeMillis());
            rootUser.setAccountLocked(false);

            authenticatedUserRepository.save(rootUser);
        }
    }
}
