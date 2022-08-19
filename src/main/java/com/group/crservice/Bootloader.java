package com.group.crservice;

import com.group.crservice.domain.entities.RoleEntity;
import com.group.crservice.domain.entities.UserEntity;
import com.group.crservice.domain.enums.ERole;
import com.group.crservice.domain.enums.GenderEnum;
import com.group.crservice.exception.ResourceNotFoundException;
import com.group.crservice.repository.RoleRepository;
import com.group.crservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@FieldDefaults(makeFinal = true)
@Slf4j
@Component
@AllArgsConstructor
@Named
public class Bootloader implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() -> {
            createRoles();
            createDefaultUsers();
        }).start();
    }

    private void createRoles () {

        if (!roleRepository.existsByName(ERole.ROLE_CUSTOMER)){
            RoleEntity roleEntity1 = RoleEntity.builder().name(ERole.ROLE_CUSTOMER).build();
            roleRepository.save(roleEntity1);
        }

        if (!roleRepository.existsByName(ERole.ROLE_EMPLOYEE)){
            RoleEntity roleEntity2 = RoleEntity.builder().name(ERole.ROLE_EMPLOYEE).build();
            roleRepository.save(roleEntity2);
        }

        if (!roleRepository.existsByName(ERole.ROLE_ADMIN)){
            RoleEntity roleEntity3 = RoleEntity.builder().name(ERole.ROLE_ADMIN).build();
            roleRepository.save(roleEntity3);
        }
    }

    private void createDefaultUsers(){
        if (!userRepository.existsByEmail("admin@gmail.com")){
            Set<RoleEntity> roles = new HashSet<>();
            RoleEntity customerRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(customerRole);

            UserEntity userEntity = UserEntity.builder()
                    .firstName("Admin")
                    .lastname("User")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("Admin123$"))
                    .dateOfBirth(LocalDate.of(2000, 8, 12))
                    .phoneNumber("09024569345")
                    .address("23 Downing Street, Lagos")
                    .genderEnum(GenderEnum.valueOf("MALE"))
                    .roles(roles)
                    .build();

            userRepository.save(userEntity);
        } else {
            log.info("Default Admin User init");
        }

        if (!userRepository.existsByEmail("employee@gmail.com")){
            Set<RoleEntity> roles = new HashSet<>();
            RoleEntity customerRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(customerRole);

            UserEntity userEntity2 = UserEntity.builder()
                    .firstName("Employee")
                    .lastname("Staff")
                    .email("employee@gmail.com")
                    .password(passwordEncoder.encode("Employee123$"))
                    .dateOfBirth(LocalDate.of(2001, 9, 15))
                    .phoneNumber("08025549342")
                    .address("21 Upwards Street, Lagos")
                    .genderEnum(GenderEnum.valueOf("FEMALE"))
                    .roles(roles)
                    .build();

            userRepository.save(userEntity2);
        } else {
            log.info("Default Admin User init");
        }
    }
}
