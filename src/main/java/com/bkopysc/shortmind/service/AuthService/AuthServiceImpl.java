package com.bkopysc.shortmind.service.AuthService;

import com.bkopysc.shortmind.exceptions.ObjectExistedException;
import com.bkopysc.shortmind.exceptions.ObjectNotFoundException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bkopysc.shortmind.config.auth.JwtService;
import com.bkopysc.shortmind.dto.Auth.AuthRequestDTO;
import com.bkopysc.shortmind.dto.Auth.AuthResponseDTO;
import com.bkopysc.shortmind.model.ERole;
import com.bkopysc.shortmind.model.Role;
import com.bkopysc.shortmind.model.User;
import com.bkopysc.shortmind.repository.RoleRepository;
import com.bkopysc.shortmind.repository.UserRepository;
import com.bkopysc.shortmind.service.UserService.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService{

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO signup(AuthRequestDTO authRequestDTO) {

        var optionalUser = userRepository.getByUsername(authRequestDTO.getUsername());
        if(optionalUser.isPresent()) {
            throw new ObjectExistedException("User");
        }

        User user = new User();
        user.setUsername(authRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(authRequestDTO.getPassword()));
        Role userRole = roleRepository.findByName(ERole.USER).orElseThrow(() -> new ObjectNotFoundException("Role USER "));
        user.setRole(userRole);

        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(savedUser);
        
        return new AuthResponseDTO(jwtToken);
    }

    @Override
    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) {
        var user = userRepository.getByUsername(authRequestDTO.getUsername()).orElseThrow(() -> new ObjectNotFoundException("User"));
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );
        var jwtToken = jwtService.generateToken(user);

        return new AuthResponseDTO(jwtToken);
    }
    
}
