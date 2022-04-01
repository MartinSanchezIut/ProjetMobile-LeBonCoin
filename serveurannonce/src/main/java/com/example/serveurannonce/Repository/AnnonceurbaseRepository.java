package com.example.serveurannonce.Repository;

import com.example.serveurannonce.Models.Annonceur;
import com.example.serveurannonce.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AnnonceurbaseRepository <T extends Annonceur>
        extends CrudRepository<T, Long> {
    //All methods in this repository will be available in the ARepository,
    //in the BRepository and in the CRepository.
    //...
}
