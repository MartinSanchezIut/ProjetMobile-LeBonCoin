package com.example.serveurannonce.Repository;

import com.example.serveurannonce.Models.Annonce;
import com.example.serveurannonce.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
