package net.boddin.clouddemo;

import net.boddin.clouddemo.entity.Role;
import net.boddin.clouddemo.entity.User;
import net.boddin.clouddemo.repository.RoleRepository;
import net.boddin.clouddemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ExampleDataprovider implements ApplicationRunner {

    private final UserRepository repository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ExampleDataprovider(UserRepository repository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {
        Role userRole = new Role("USER");
        Role adminRole = new Role("ADMINISTRATOR");
        Role actRole = new Role("ACTUATOR");

        userRole = roleRepository.save(userRole);
        adminRole = roleRepository.save(adminRole);
        actRole = roleRepository.save(actRole);

        User user = new User("user", "user");
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.getRoles().add(userRole);
        repository.save(user);
        User admin = new User("admin", "admin");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.getRoles().add(userRole);
        admin.getRoles().add(adminRole);
        admin.getRoles().add(actRole);
        repository.save(admin);
    }
}
