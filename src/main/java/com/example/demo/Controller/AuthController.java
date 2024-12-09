package com.example.demo.Controller;

import com.example.demo.Dto.LoginRequest;
import com.example.demo.Dto.LoginResponse;
import com.example.demo.Dto.RegistrationRequest;
import com.example.demo.Model.MyAppUser;
import com.example.demo.Model.MyAppUserRepository;
import com.example.demo.utilities.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "user/")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private MyAppUserRepository myAppUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/register")
    public ResponseEntity<LoginResponse> registerUser(@RequestBody RegistrationRequest request){
        // Check if email or username already exists
        if (myAppUserRepository.findByEmail(request.getEmail()).isPresent() ||
                myAppUserRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        // Create new user and set all fields
        MyAppUser user = new MyAppUser();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save user to the database
        myAppUserRepository.save(user);

        // Generate tokens
        String accessToken = jwtTokenUtil.generateAccessToken(user);
        String refreshToken = jwtTokenUtil.generateRefreshToken(user);

        // Create response
        LoginResponse response = new LoginResponse(accessToken, refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        MyAppUser user = (MyAppUser) authentication.getPrincipal();
        String accessToken = jwtTokenUtil.generateAccessToken(user);
        String refreshToken = jwtTokenUtil.generateRefreshToken(user);

        LoginResponse response = new LoginResponse(accessToken, refreshToken);
        return ResponseEntity.ok(response);
    }
}
