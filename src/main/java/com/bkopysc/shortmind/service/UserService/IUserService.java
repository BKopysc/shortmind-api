package com.bkopysc.shortmind.service.UserService;
import java.util.Optional;

import com.bkopysc.shortmind.dto.User.UserSignDTO;
import com.bkopysc.shortmind.dto.User.UserGetDTO;
import com.bkopysc.shortmind.model.User;

public interface IUserService {

    public User addNewUser(UserSignDTO newUserDTO);

    public UserGetDTO getByUsername(String username);
}