package com.example.serveurannonce.Repository;

import com.example.serveurannonce.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
