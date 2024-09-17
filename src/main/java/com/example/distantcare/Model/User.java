package com.example.distantcare.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class User implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //@Column(unique = true, nullable = false)
    private String username;
    //@Column(unique = true, nullable = false)
    private String password;
    //@Column(unique = true, nullable = false)
    private String fullName;
    //@Column(nullable = false)
    private LocalDate birthDate;
    //@Column(unique = true, nullable = false)
    private String phoneNumber;
   // @Column(nullable = false)
    private String emergencyPhoneNumber;
    //@Column(nullable = false)
    private int age;
    //@Column(unique = true, nullable = false)
    private String email;
    //@Column(nullable = false)
    private String gender;
    //@Column(nullable = false)
    private String address;
    //@Column(nullable = false)
    private String role;
  //  @Column(nullable = false)
    private String city;

    // ------------------ Relations -----------

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Patient patient;


    @OneToOne(cascade = CascadeType.ALL , mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Doctor doctor;

    @OneToOne( cascade = CascadeType.ALL,mappedBy = "user")
    private Hospital hospital;

}
