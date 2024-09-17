package com.example.distantcare.Repository;

import com.example.distantcare.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   // User findByEmail(String email);
    User findUserById(int id);
    User findUserByUsername(String username);
}
