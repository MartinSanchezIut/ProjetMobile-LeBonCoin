package com.example.serveurannonce.Models;

import javax.persistence.*;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Annonceur_pro extends User{

    @Column(name = "NOM_SOCIETE")
    private String nom_societe;

    @Column(name = "MESANNONCES")
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Annonce> mesannonces;

}
