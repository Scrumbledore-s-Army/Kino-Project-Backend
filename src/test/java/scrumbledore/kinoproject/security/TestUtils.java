package scrumbledore.kinoproject.security;

import scrumbledore.kinoproject.security.entity.Role;
import scrumbledore.kinoproject.security.entity.UserWithRoles;
import scrumbledore.kinoproject.security.repository.UserWithRolesRepository;

public class TestUtils {

  public static void setupTestUsers( UserWithRolesRepository userWithRolesRepository){
    userWithRolesRepository.deleteAll();
    String passwordUsedByAll = "secret";
    UserWithRoles user1 = new UserWithRoles("u1", passwordUsedByAll, "u1@a.dk");
    UserWithRoles user2 = new UserWithRoles("u2", passwordUsedByAll, "u2@a.dk");
    UserWithRoles user3 = new UserWithRoles("u3", passwordUsedByAll, "u3@a.dk");
    UserWithRoles userNoRoles = new UserWithRoles("u4", passwordUsedByAll, "u4@a.dk");
    user1.addRole(Role.USER);
    user1.addRole(Role.ADMIN);
    user2.addRole(Role.USER);
    user3.addRole(Role.ADMIN);
    userWithRolesRepository.save(user1);
    userWithRolesRepository.save(user2);
    userWithRolesRepository.save(user3);
    userWithRolesRepository.save(userNoRoles);
  }
}
