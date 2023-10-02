package scrumbledore.kinoproject.project.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import scrumbledore.kinoproject.project.user.entity.User;
import scrumbledore.kinoproject.project.user.repository.UserRepository;
import scrumbledore.kinoproject.security.entity.Role;
import scrumbledore.kinoproject.security.repository.UserWithRolesRepository;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    UserWithRolesRepository userWithRolesRepository;

    @Autowired
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    String passwordUsedByAll;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository, PasswordEncoder passwordEncoder) {
        this.userWithRolesRepository = userWithRolesRepository;
        this.passwordEncoder = passwordEncoder;
        passwordUsedByAll = "test12";
    }

    @Override
    public void run(ApplicationArguments args) {
        setupUserWithRoleUsers();
    }

     /*****************************************************************************************
     IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO

     If you see the lines below in log-outputs on Azure, forget whatever had your attention on, AND FIX THIS PROBLEM

     *****************************************************************************************/
    private void setupUserWithRoleUsers() {
        System.out.println("******************************************************************************");
        System.out.println("********** IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ************");
        System.out.println();
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println();
        System.out.println("******************************************************************************");

        User testuser = new User("testuser","test","test@test.dk","test","test","test","test","test");
        User testadmin = new User("testadmin","testadmin","testadmin@testadmin.dk","testadmin","testadmin","testadmin","testadmin","testadmin");
        User testspaghetti = new User("testspaghetti","testspaghetti","testspaghetti@testspaghetti.dk","testspaghetti","testspaghetti","testspaghetti","testspaghetti","testspaghetti");

        
        testuser.addRole(Role.USER);
        testadmin.addRole(Role.ADMIN);
        testspaghetti.addRole(Role.USER);

        userRepository.save(testuser);
        userRepository.save(testadmin);
    }
}
