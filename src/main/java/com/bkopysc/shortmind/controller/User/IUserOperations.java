package com.bkopysc.shortmind.controller.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkopysc.shortmind.dto.User.UserGetDTO;
import com.bkopysc.shortmind.dto.User.UserSignDTO;

@RequestMapping("/default")
public interface IUserOperations {

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDTO> getByUsername(String username);

    @GetMapping("/")
    public ResponseEntity<String> test();
}
