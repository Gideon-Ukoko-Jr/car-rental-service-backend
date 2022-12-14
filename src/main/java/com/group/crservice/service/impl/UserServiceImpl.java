package com.group.crservice.service.impl;

import com.group.crservice.domain.entities.RoleEntity;
import com.group.crservice.domain.entities.UserEntity;
import com.group.crservice.domain.enums.ERole;
import com.group.crservice.domain.enums.GenderEnum;
import com.group.crservice.domain.enums.RecordStatusConstant;
import com.group.crservice.exception.BadRequestException;
import com.group.crservice.exception.ConflictException;
import com.group.crservice.exception.ResourceNotFoundException;
import com.group.crservice.repository.RoleRepository;
import com.group.crservice.repository.UserRepository;
import com.group.crservice.request.LoginRequest;
import com.group.crservice.request.SignUpRequest;
import com.group.crservice.response.LoginResponse;
import com.group.crservice.response.SignUpResponse;
import com.group.crservice.response.UserResponse;
import com.group.crservice.security.jwt.JwtUtils;
import com.group.crservice.service.UserService;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public SignUpResponse signUp(SignUpRequest request) {


        // Check if user email exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Error: Email is already in use!");
        }

        // Retrieving key variables from request object
        String firstName = request.getFirstName();
        String lastname = request.getLastName();
        String email = request.getEmail().toLowerCase();
        String password = request.getPassword();
        String phoneNumber = request.getPhoneNumber();
        String address = request.getAddress();
        String gender = request.getGender().toUpperCase();
        String birthday = request.getBirthday();

        // Converting userBirthday String to Date
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate dDate = LocalDate.parse(birthday, formatter);

        // Setting User Default Role to Customer
        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity customerRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
        roles.add(customerRole);

        // Creating and Saving UserEntity
        UserEntity userEntity = UserEntity.builder()
                .firstName(firstName)
                .lastname(lastname)
                .email(email)
                .password(passwordEncoder.encode(password))
                .dateOfBirth(dDate)
                .phoneNumber(phoneNumber)
                .address(address)
                .genderEnum(GenderEnum.valueOf(gender))
                .roles(roles)
                .isEmployee(false)
                .build();

        userRepository.save(userEntity);

        return SignUpResponse.builder()
                .message("Sign Up Successful. Proceed to Login Page")
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        String email = request.getEmail().toLowerCase();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("Invalid email"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password.");
        }

        else {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            if (user.getRecordStatus() != RecordStatusConstant.ACTIVE){
                jwt = "Your Access has been revoked";
            }

            return LoginResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastname())
                    .phoneNumber(user.getPhoneNumber())
                    .roles(Collections.singleton(user.getRoles().toString()))
                    .token(jwt)
                    .build();
        }

    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserEntity>  userList = userRepository.findAll();
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
