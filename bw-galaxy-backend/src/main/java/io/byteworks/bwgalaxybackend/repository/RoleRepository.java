package io.byteworks.bwgalaxybackend.repository;

import io.byteworks.bwgalaxybackend.model.Role;
import io.byteworks.bwgalaxybackend.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Yasholma on 26-Feb-20.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
