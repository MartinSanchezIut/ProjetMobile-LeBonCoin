package com.example.serveurannonce.Repository;

import com.example.serveurannonce.Models.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation,Long> {
}
