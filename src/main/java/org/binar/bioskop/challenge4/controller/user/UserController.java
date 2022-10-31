package org.binar.bioskop.challenge4.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.binar.bioskop.challenge4.entity.Role;
import org.binar.bioskop.challenge4.entity.UserEntity;
import org.binar.bioskop.challenge4.repository.RoleRepository;
import org.binar.bioskop.challenge4.repository.UserRepository;
import org.binar.bioskop.challenge4.request.LoginRequest;
import org.binar.bioskop.challenge4.request.RegisterRequest;
import org.binar.bioskop.challenge4.respon.ERole;
import org.binar.bioskop.challenge4.respon.JwtResponse;
import org.binar.bioskop.challenge4.respon.MessageResponse;
import org.binar.bioskop.challenge4.respon.Respon;
import org.binar.bioskop.challenge4.security.JwtUtils;
import org.binar.bioskop.challenge4.service.UserService;
import org.binar.bioskop.challenge4.service.implement.usersecurity.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

//path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private final UserService userService;


//    mvn -B package --file pom.xml -Dmaven.test.skip=true


    @Operation(summary = "this is to create user from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "create user into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @PostMapping("/create")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity create(@RequestBody UserEntity userEntity){
       Respon ress = new Respon();
       ress.setResponCode("200");
       ress.setMessage("succes");
        ress.setData(userService.create(userEntity));
        return ResponseEntity.ok().body(ress);
    }

    @Operation(summary = "this is to update user from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "update user into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UserEntity userEntity){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(userService.update(id, userEntity));
        return ResponseEntity.ok().body(ress);
    }

    @Operation(summary = "this is to Find All user from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find All user into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity findAll(){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(userService.findAll());
        return ResponseEntity.ok().body(ress);
    }

    @Operation(summary = "this is to delete user from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "delete user into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')" )
    public ResponseEntity delete(@PathVariable Long id){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(userService.delete(id));
        return ResponseEntity.ok().body(ress);
    }

    @Operation(summary = "this is to Login from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Login into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
        return ResponseEntity.ok().body(ress);

    }

    @Operation(summary = "this is to Register from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Register into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        UserEntity customer = new UserEntity(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if ("admin".equals(role)) {
                    log.info("masuk ke admin");
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    log.info("masuk ke customer");
                    Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        customer.setRoles(roles);
        userRepository.save(customer);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
