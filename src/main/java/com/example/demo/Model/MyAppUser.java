package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class MyAppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String email;
    private String password;

    // Constructors

    public MyAppUser() {
    }

    public MyAppUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public MyAppUser(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    // If you need to set the ID manually
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username; // This should return the username field
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // UserDetails Methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Return roles if you have them
    }

    @Override
    public String getPassword() {
        return password;
    }

    // Setters for password
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify based on your requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify based on your requirements
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify based on your requirements
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify based on your requirements
    }
}
