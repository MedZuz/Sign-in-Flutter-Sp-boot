package com.exampleaziz.signinapp.Repository;

import com.exampleaziz.signinapp.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
        Optional<Role> findByName(String name);

}
