package io.byteworks.bwgalaxybackend;

import io.byteworks.bwgalaxybackend.exception.AppException;
import io.byteworks.bwgalaxybackend.model.Role;
import io.byteworks.bwgalaxybackend.model.RoleName;
import io.byteworks.bwgalaxybackend.model.User;
import io.byteworks.bwgalaxybackend.repository.RoleRepository;
import io.byteworks.bwgalaxybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class BwGalaxyBackendApplication {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BwGalaxyBackendApplication.class, args);
	}


	// Please, comment this method before running unit tests.
	@Bean
	public CommandLineRunner setup(RoleRepository roleRepository) {
		return args -> {
			try {
				roleRepository.save(new Role(RoleName.ROLE_ADMIN));
				roleRepository.save(new Role(RoleName.ROLE_USER));


				User user = new User("Super Admin", "superadmin", "super@admin", "admin@12");
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new AppException("User Role not set."));
				user.setRoles(Collections.singleton(userRole));
				userRepository.save(user);

			} catch (DataIntegrityViolationException sql) {
				System.out.println("Table Role Already exist in the database.");
			}
		};
	}

}
