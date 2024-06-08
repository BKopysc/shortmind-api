package com.bkopysc.shortmind.controller.User;

import org.springframework.http.ResponseEntity;

import com.bkopysc.shortmind.dto.User.UserGetDTO;
import com.bkopysc.shortmind.dto.User.UserSignDTO;

public class UserController implements IUserOperations{

    @Override
    public ResponseEntity<UserGetDTO> signup(UserSignDTO newUserDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signup'");
    }

    @Override
    public ResponseEntity<UserGetDTO> login(UserSignDTO userDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public ResponseEntity<UserGetDTO> getUserById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }
    
}
