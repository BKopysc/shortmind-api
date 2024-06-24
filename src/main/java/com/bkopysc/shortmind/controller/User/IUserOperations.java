package com.bkopysc.shortmind.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkopysc.shortmind.dto.user.UserGetDTO;
import com.bkopysc.shortmind.dto.user.UserSignDTO;

@RequestMapping("/default")
public interface IUserOperations {

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDTO> getById(@PathVariable Long id);

    @GetMapping("/is-username-taken/{username}")
    public ResponseEntity<Boolean> isUsernameTaken(@PathVariable String username);
}
