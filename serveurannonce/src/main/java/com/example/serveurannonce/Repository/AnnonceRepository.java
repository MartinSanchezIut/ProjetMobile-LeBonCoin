package com.example.serveurannonce.Repository;

import com.example.serveurannonce.Models.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceRepository extends JpaRepository<Annonce,Long> {
}
