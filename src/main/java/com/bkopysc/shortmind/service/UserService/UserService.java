package com.bkopysc.shortmind.service.UserService;
import java.util.Optional;

import com.bkopysc.shortmind.dto.User.NewUserDTO;
import com.bkopysc.shortmind.dto.User.UserGetDTO;
import com.bkopysc.shortmind.model.User;

public interface UserService {

    public User addNewUser(NewUserDTO newUserDTO);

    public UserGetDTO getByUsername(String username);
}