package com.group.crservice;

import com.group.crservice.domain.entities.RoleEntity;
import com.group.crservice.domain.enums.ERole;
import com.group.crservice.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.inject.Named;

@FieldDefaults(makeFinal = true)
@Slf4j
@Component
@AllArgsConstructor
@Named
public class Bootloader implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() -> {
            createRoles();
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
}
