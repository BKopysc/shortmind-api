package com.bkopysc.shortmind.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bkopysc.shortmind.model.ERole;
import com.bkopysc.shortmind.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
    Optional<Role> findByName(ERole name);
}
