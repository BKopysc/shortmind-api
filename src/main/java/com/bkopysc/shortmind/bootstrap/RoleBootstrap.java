package com.bkopysc.shortmind.bootstrap;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.bkopysc.shortmind.model.ERole;
import com.bkopysc.shortmind.model.Role;
import com.bkopysc.shortmind.repository.RoleRepository;

@Component
public class RoleBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private final RoleRepository roleRepository;

    public RoleBootstrap(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    private void loadRoles() {
        ERole[] roleNames = new ERole[] { ERole.USER, ERole.ADMIN };

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();
                roleToCreate.setName(roleName);
                roleRepository.save(roleToCreate);
            });
        });
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loadRoles();
    }

}
