package com.example.serveurannonce.Repository;

import com.example.serveurannonce.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;


public interface UserRepository extends UserbaseRepository<User> {

}
