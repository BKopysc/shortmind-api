package com.bkopysc.shortmind.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkopysc.shortmind.dto.user.UserGetDTO;
import com.bkopysc.shortmind.dto.user.UserSignDTO;
import com.bkopysc.shortmind.service.user.IUserService;
import com.bkopysc.shortmind.service.user.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController implements IUserOperations{

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }
    @Override
    public ResponseEntity<UserGetDTO> getById(Long id) {
        UserGetDTO user = this.userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Boolean> isUsernameTaken(String username) {
        return ResponseEntity.ok(this.userService.existsByUsername(username));
    }


    
}
