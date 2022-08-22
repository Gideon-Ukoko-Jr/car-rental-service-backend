package com.group.crservice.bootloader;

import com.group.crservice.domain.entities.RoleEntity;
import com.group.crservice.domain.entities.UserEntity;
import com.group.crservice.domain.entities.VehicleEntity;
import com.group.crservice.domain.enums.ERole;
import com.group.crservice.domain.enums.GenderEnum;
import com.group.crservice.exception.ResourceNotFoundException;
import com.group.crservice.repository.RoleRepository;
import com.group.crservice.repository.UserRepository;
import com.group.crservice.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.math.BigDecimal;
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

    private final VehicleRepository vehicleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() -> {
            createRoles();
            createDefaultUsers();
            createVehicles();
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
        if (!userRepository.existsByEmail("admin22@gmail.com")){
            Set<RoleEntity> roles = new HashSet<>();
            RoleEntity customerRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(customerRole);

            UserEntity userEntity = UserEntity.builder()
                    .firstName("Admin")
                    .lastname("User")
                    .email("admin22@gmail.com")
                    .password(passwordEncoder.encode("Admin123$"))
                    .dateOfBirth(LocalDate.of(2000, 8, 12))
                    .phoneNumber("09024569345")
                    .address("23 Downing Street, Lagos")
                    .genderEnum(GenderEnum.valueOf("MALE"))
                    .roles(roles)
                    .isEmployee(false)
                    .build();

            userRepository.save(userEntity);
        } else {
            log.info("Default Admin User init");
        }

        if (!userRepository.existsByEmail("john.employee@gmail.com")){
            Set<RoleEntity> roles = new HashSet<>();
            RoleEntity customerRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(customerRole);

            UserEntity userEntity2 = UserEntity.builder()
                    .firstName("John")
                    .lastname("Bull")
                    .email("john.employee@gmail.com")
                    .password(passwordEncoder.encode("Employee123$"))
                    .dateOfBirth(LocalDate.of(2001, 9, 15))
                    .phoneNumber("08025549342")
                    .address("21 Upwards Street, Lagos")
                    .genderEnum(GenderEnum.valueOf("MALE"))
                    .roles(roles)
                    .isEmployee(true)
                    .build();

            userRepository.save(userEntity2);
        } else {
            log.info("Default Email User init");
        }

        if (!userRepository.existsByEmail("musa.employee@gmail.com")){
            Set<RoleEntity> roles = new HashSet<>();
            RoleEntity customerRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(customerRole);

            UserEntity userEntity2 = UserEntity.builder()
                    .firstName("Musa")
                    .lastname("Ahmed")
                    .email("musa.employee@gmail.com")
                    .password(passwordEncoder.encode("Employee123$"))
                    .dateOfBirth(LocalDate.of(1989, 10, 16))
                    .phoneNumber("08045649342")
                    .address("23 Kerr Street, Lagos")
                    .genderEnum(GenderEnum.valueOf("MALE"))
                    .roles(roles)
                    .isEmployee(true)
                    .build();

            userRepository.save(userEntity2);
        } else {
            log.info("Default Email User init");
        }

        if (!userRepository.existsByEmail("chioma.employee@gmail.com")){
            Set<RoleEntity> roles = new HashSet<>();
            RoleEntity customerRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(customerRole);

            UserEntity userEntity2 = UserEntity.builder()
                    .firstName("Chioma")
                    .lastname("Eze")
                    .email("chioma.employee@gmail.com")
                    .password(passwordEncoder.encode("Employee123$"))
                    .dateOfBirth(LocalDate.of(2003, 5, 25))
                    .phoneNumber("08045659343")
                    .address("23 Law Street, Lagos")
                    .genderEnum(GenderEnum.valueOf("FEMALE"))
                    .roles(roles)
                    .isEmployee(true)
                    .build();

            userRepository.save(userEntity2);
        } else {
            log.info("Default Email User init");
        }

        if (!userRepository.existsByEmail("tolu.employee@gmail.com")){
            Set<RoleEntity> roles = new HashSet<>();
            RoleEntity customerRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(customerRole);

            UserEntity userEntity2 = UserEntity.builder()
                    .firstName("Tolu")
                    .lastname("Babatunde")
                    .email("tolu.employee@gmail.com")
                    .password(passwordEncoder.encode("Employee123$"))
                    .dateOfBirth(LocalDate.of(2002, 10, 23))
                    .phoneNumber("08045659341")
                    .address("21 Five Street, Lagos")
                    .genderEnum(GenderEnum.valueOf("FEMALE"))
                    .roles(roles)
                    .isEmployee(true)
                    .build();

            userRepository.save(userEntity2);
        } else {
            log.info("Default Email User init");
        }

        if (!userRepository.existsByEmail("bolu.employee@gmail.com")){
            Set<RoleEntity> roles = new HashSet<>();
            RoleEntity customerRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(customerRole);

            UserEntity userEntity2 = UserEntity.builder()
                    .firstName("Bolu")
                    .lastname("Adesanya")
                    .email("bolu.employee@gmail.com")
                    .password(passwordEncoder.encode("Employee123$"))
                    .dateOfBirth(LocalDate.of(2002, 10, 23))
                    .phoneNumber("08025650343")
                    .address("11 Sampha Street, Lagos")
                    .genderEnum(GenderEnum.valueOf("FEMALE"))
                    .roles(roles)
                    .isEmployee(true)
                    .build();

            userRepository.save(userEntity2);
        } else {
            log.info("Default Email User init");
        }

    }

    private void createVehicles(){

        if (!vehicleRepository.existsByPlateNumber("RRC123")){
            VehicleEntity vehicle = new VehicleEntity("RRC123", "Rolls-Royce Cullinan", "Silver", BigDecimal.valueOf(2100), "https://www.auto-data.net/en/rolls-royce-cullinan-generation-6123#image11", true);
            vehicleRepository.save(vehicle);
        }else {
            log.info("Default Vehicle Init");
        }

        if (!vehicleRepository.existsByPlateNumber("FRM123")){
            VehicleEntity vehicle = new VehicleEntity("FRM123", "Ferrari Monza", "Black", BigDecimal.valueOf(2100), "https://www.auto-data.net/en/ferrari-monza-sp-generation-6551#image18", true);
            vehicleRepository.save(vehicle);
        }else {
            log.info("Default Vehicle Init");
        }

        if (!vehicleRepository.existsByPlateNumber("LAS123")){
            VehicleEntity vehicle = new VehicleEntity("LAS123", "Lamborghini Sian", "Gold", BigDecimal.valueOf(2100), "https://www.auto-data.net/en/lamborghini-sian-fkp-37-generation-7281#image10", true);
            vehicleRepository.save(vehicle);
        }else {
            log.info("Default Vehicle Init");
        }

        if (!vehicleRepository.existsByPlateNumber("BGC123")){
            VehicleEntity vehicle = new VehicleEntity("BGC123", "Bugatti Chiron", "Blue", BigDecimal.valueOf(2100), "https://www.auto-data.net/en/bugatti-chiron-generation-4795#image12", true);
            vehicleRepository.save(vehicle);
        }else {
            log.info("Default Vehicle Init");
        }

        if (!vehicleRepository.existsByPlateNumber("FRS123")){
            VehicleEntity vehicle = new VehicleEntity("FRS123", "Ferrari SP48", "Red", BigDecimal.valueOf(2100), "https://www.auto-data.net/en/ferrari-sp48-unica-generation-8865#image1", true);
            vehicleRepository.save(vehicle);
        }

        if (!vehicleRepository.existsByPlateNumber("BMW123")){
            VehicleEntity vehicle = new VehicleEntity("BMW123", "BMW 4 Series Gran Coupe G26", "Red", BigDecimal.valueOf(2100), "https://www.auto-data.net/en/bmw-4-series-gran-coupe-g26-generation-8405#image1", true);
            vehicleRepository.save(vehicle);
        }else {
            log.info("Default Vehicle Init");
        }

        if (!vehicleRepository.existsByPlateNumber("LMM123")){
            VehicleEntity vehicle = new VehicleEntity("LMM123", "lamborghini Murcielago", "Orange", BigDecimal.valueOf(2100), "https://www.auto-data.net/en/lamborghini-murcielago-lp640-roadster-generation-7966#image1", true);
            vehicleRepository.save(vehicle);
        }else {
            log.info("Default Vehicle Init");
        }

        if (!vehicleRepository.existsByPlateNumber("BBR123")){
            VehicleEntity vehicle = new VehicleEntity("BBR123", "Bentley Brooklands", "Black", BigDecimal.valueOf(2100), "https://www.auto-data.net/en/bentley-brooklands-ii-generation-1547#image1", true);
            vehicleRepository.save(vehicle);
        }else {
            log.info("Default Vehicle Init");
        }

        if (!vehicleRepository.existsByPlateNumber("AMD123")){
            VehicleEntity vehicle = new VehicleEntity("AMD123", "Aston Martin DBS", "White", BigDecimal.valueOf(2100), "https://www.auto-data.net/en/aston-martin-dbs-superleggera-volante-generation-7080#image8", true);
            vehicleRepository.save(vehicle);
        }else {
            log.info("Default Vehicle Init");
        }

        if (!vehicleRepository.existsByPlateNumber("ASV123")){
            VehicleEntity vehicle = new VehicleEntity("ASV123", "Aston Martin Vanquish", "Cyan", BigDecimal.valueOf(2100), "https://www.auto-data.net/en/aston-martin-vanquish-vision-concept-generation-7016#image1", true);
            vehicleRepository.save(vehicle);
        }else {
            log.info("Default Vehicle Init");
        }


    }
}
