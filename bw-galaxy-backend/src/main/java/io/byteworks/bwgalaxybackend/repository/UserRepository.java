package io.byteworks.bwgalaxybackend.repository;

import io.byteworks.bwgalaxybackend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Yasholma on 26-Feb-20.
 */
@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    @Query("SELECT name, username, email FROM User WHERE Id = :userId")
    Optional<User> findUserInfoById(@Param("userId") Long userId);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}