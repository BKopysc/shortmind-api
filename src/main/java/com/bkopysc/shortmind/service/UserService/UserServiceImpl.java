package com.bkopysc.shortmind.service.UserService;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bkopysc.shortmind.dto.User.UserSignDTO;
import com.bkopysc.shortmind.dto.User.UserGetDTO;
import com.bkopysc.shortmind.exceptions.ObjectExistedException;
import com.bkopysc.shortmind.exceptions.ObjectNotFoundException;
import com.bkopysc.shortmind.model.ERole;
import com.bkopysc.shortmind.model.Role;
import com.bkopysc.shortmind.model.User;
import com.bkopysc.shortmind.repository.RoleRepository;
import com.bkopysc.shortmind.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService{
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
            RoleRepository roleRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public UserGetDTO addNewUser(UserSignDTO newUserDTO) {
        Optional<Role> role = roleRepository.findByName(ERole.USER);
        if(role.isEmpty()) {
            throw new ObjectNotFoundException("Role");
        }

        Optional<User> user = userRepository.getByUsername(newUserDTO.getUsername());

        if(user.isPresent()) {
            throw new ObjectExistedException("User");
        }

        User newUser = new User();
        newUser.setUsername(newUserDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        newUser.setRole(role.get());

        User savedUser = userRepository.save(newUser);
        
        return this.modelMapper.map(savedUser, UserGetDTO.class);
    }

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

    


}
