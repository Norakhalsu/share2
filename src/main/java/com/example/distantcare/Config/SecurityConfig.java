package com.example.distantcare.Config;


import com.example.distantcare.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MyUserDetailsService userDetailsService;


    // Authentication : check Log in  user in database
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }


    // Athurisation
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()

                // User
                .authenticationProvider(daoAuthenticationProvider()).authorizeRequests().
                requestMatchers("/api/v1/user/add").permitAll()


                // Hospital
                .requestMatchers("/api/v1/hospital/add").permitAll()
                .requestMatchers("/api/v1/hospital/get-all").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/hospital/delete/{hospitalId}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/hospital/update/{hospitalId}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/hospital/patient-count/{hospitalId}").hasAuthority("HOSPITAL")
                .requestMatchers("/api/v1/hospital/remove-doctor/{doctorId}").hasAuthority("HOSPITAL")
                .requestMatchers("/api/v1/hospital/hospital-requests").hasAuthority("ADMIN")

                // Patients
                .requestMatchers("/api/v1/patient/registration").permitAll()





                 // Appintment
              //  .requestMatchers("/api/v1/appointment/add-appointment/{patientId}").hasAuthority("DOCTOR")

                .and()
                .logout().logoutUrl("/api/v1/user/logout").
                deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();

    }
}
