package com.group.crservice.service.impl;

import com.group.crservice.domain.entities.RoleEntity;
import com.group.crservice.domain.entities.UserEntity;
import com.group.crservice.domain.enums.ERole;
import com.group.crservice.domain.enums.GenderEnum;
import com.group.crservice.domain.enums.RecordStatusConstant;
import com.group.crservice.exception.ResourceNotFoundException;
import com.group.crservice.repository.RoleRepository;
import com.group.crservice.repository.UserRepository;
import com.group.crservice.request.AddEmployeeRequest;
import com.group.crservice.response.AddEmployeeResponse;
import com.group.crservice.response.UserResponse;
import com.group.crservice.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Named
@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AddEmployeeResponse createEmployee(AddEmployeeRequest request) {
        // Retrieving key variables from request object
        String firstName = request.getFirstName();
        String lastname = request.getLastName();
        String email = request.getEmail().toLowerCase();
        String phoneNumber = request.getPhoneNumber();
        String address = request.getAddress();
        String gender = request.getGender().toUpperCase();
        String birthday = request.getBirthday();


        String password = "";
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi" +"jklmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(7);
        for (int i = 0; i < 7; i++){
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
            password = sb.toString();
        }

        // Converting userBirthday String to Date
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate dDate = LocalDate.parse(birthday, formatter);

        // Setting User Role to Employee
        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
        roles.add(employeeRole);

        UserEntity employee = new UserEntity(firstName, lastname, email, phoneNumber, passwordEncoder.encode(password), address,
                GenderEnum.valueOf(gender), dDate, roles, true);

        userRepository.save(employee);

        return AddEmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastname())
                .phoneNumber(employee.getPhoneNumber())
                .email(employee.getEmail())
                .password(password)
                .date(employee.getDateCreated())
                .status(employee.getRecordStatus().name())
                .build();
    }

    @Override
    public String deactivateEmployee(long employeeId) {
        UserEntity employee = userRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Error: Employee is not found."));
        employee.setRecordStatus(RecordStatusConstant.INACTIVE);
        userRepository.save(employee);
        return "Employee : " + employee.getFirstName() + " " + employee.getLastname() + " has been deactivated";
    }

    @Override
    public List<UserResponse> getAllEmployees() {
        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
        roles.add(employeeRole);
        List<UserEntity> userList = userRepository.findAllEmployees();
        return userList.stream().map(this::fromEntityToResponse).collect(Collectors.toList());
    }

    private UserResponse fromEntityToResponse(UserEntity user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastname())
                .phoneNumber(user.getPhoneNumber())
                .date(user.getDateCreated())
                .roles(Collections.singleton(user.getRoles().toString()))
                .build();
    }
}
