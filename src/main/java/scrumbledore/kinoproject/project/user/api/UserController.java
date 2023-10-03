package scrumbledore.kinoproject.project.user.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import scrumbledore.kinoproject.project.user.dto.UserRequest;
import scrumbledore.kinoproject.project.user.dto.UserResponse;
import scrumbledore.kinoproject.project.user.service.UserService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/users")
class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //security --> Admin
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    List<UserResponse> getUsers() {
        return userService.getUsers();
    }


    //Admin
    @GetMapping(path = "/{username}")
    UserResponse getUserById(@PathVariable String username) throws Exception {
        return userService.findById(username);
    }

    //Security --> Anonymous

    @PreAuthorize("permitAll()")
    @PostMapping()
    UserResponse addUser(@RequestBody UserRequest body) {
        return userService.addUser(body);
    }

    //Security --> Admin
    @PutMapping("/{username}")
    void editUser(@RequestBody UserRequest body, @PathVariable String username) {
        userService.editUser(body, username);
    }

    // Security --> User
    @DeleteMapping("/{username}")
    void deleteUserByUsername(@PathVariable String username) {
        userService.deleteById(username);
    }
}