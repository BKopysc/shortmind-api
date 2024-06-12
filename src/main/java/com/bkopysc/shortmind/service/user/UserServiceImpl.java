package com.bkopysc.shortmind.service.user;

import java.security.Principal;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bkopysc.shortmind.dto.user.UserGetDTO;
import com.bkopysc.shortmind.exceptions.ObjectNotFoundException;
import com.bkopysc.shortmind.model.User;
import com.bkopysc.shortmind.repository.RoleRepository;
import com.bkopysc.shortmind.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService{
    
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserGetDTO getByUsername(String username) {
        Optional<User> user = userRepository.getByUsername(username);
        
        if(user.isPresent()) {
            UserGetDTO userGetDTO = new UserGetDTO();
            userGetDTO.setUsername(user.get().getUsername());
            return userGetDTO;
        } else {
            throw new ObjectNotFoundException("User");
        }
    }

    @Override
    public User getAuthenticatedUser(Authentication authentication) {
        String username = authentication.getName();
        return userRepository.getByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User"));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.getByUsername(username).isPresent();
    }

    


}
