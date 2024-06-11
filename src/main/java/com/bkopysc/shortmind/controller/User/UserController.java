package com.bkopysc.shortmind.controller.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkopysc.shortmind.dto.User.UserGetDTO;
import com.bkopysc.shortmind.dto.User.UserSignDTO;
import com.bkopysc.shortmind.service.UserService.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController implements IUserOperations{

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @Override
    public ResponseEntity<UserGetDTO> getByUsername(String username) {
        UserGetDTO user = this.userService.getByUsername(username);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<String> test() {
        // TODO Auto-generated method stub
        return ResponseEntity.ok("Test");
    }
    
}
