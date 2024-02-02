package com.commercial.commerce.sale.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.commercial.commerce.sale.utils.Caracteristic;
import com.commercial.commerce.sale.utils.Maintenance;
import com.commercial.commerce.sale.utils.Vendeur;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "annonce")
public class AnnonceEntity {

    @Id
    private String id;
    @TextIndexed
    private MakeEntity brand;
    @TextIndexed
    private ModelEntity modele;
    private List<Caracteristic> caracteristic;
    private double prix;
    private String year;
    @TextIndexed
    private CouleurEntity couleur;
    private double kilometre;

    private double consommation;
    @TextIndexed
    private List<MaintainEntity> maintenance;

    private CountryEntity localisation;

    private Vendeur vendeur;

    private int commission;

    private List<String> pictures;
    @TextIndexed
    private MotorEntity motor;

    private double etat;
    private double validity;

    private int state;
    @TextIndexed
    private List<Long> favoris;

    private LocalDateTime date;
    @TextIndexed
    private String description;
}
