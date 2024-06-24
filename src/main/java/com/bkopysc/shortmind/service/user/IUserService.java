package com.bkopysc.shortmind.service.user;
import java.util.Optional;

import org.springframework.security.core.Authentication;

import com.bkopysc.shortmind.dto.user.UserGetDTO;
import com.bkopysc.shortmind.dto.user.UserSignDTO;
import com.bkopysc.shortmind.model.User;

public interface IUserService {

    public UserGetDTO getByUsername(String username);

    public UserGetDTO getById(Long id);

    public User getAuthenticatedUser(Authentication authentication);

    public boolean existsByUsername(String username);
}