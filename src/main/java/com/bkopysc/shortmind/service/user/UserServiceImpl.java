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
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public UserGetDTO getByUsername(String username) {
        Optional<User> user = userRepository.getByUsername(username);
        
        if(user.isPresent()) {
            UserGetDTO userGetDTO = modelMapper.map(user.get(), UserGetDTO.class);
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

    @Override
    public UserGetDTO getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        
        if(user.isPresent()) {
            UserGetDTO userGetDTO = modelMapper.map(user.get(), UserGetDTO.class);
            return userGetDTO;
        } else {
            throw new ObjectNotFoundException("User");
        }
    }

    


}
