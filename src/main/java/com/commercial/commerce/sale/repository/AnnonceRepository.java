package com.commercial.commerce.sale.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.commercial.commerce.sale.entity.AnnonceEntity;
import com.commercial.commerce.sale.utils.Statistique;

public interface AnnonceRepository extends MongoRepository<AnnonceEntity, String> {

    List<AnnonceEntity> findByFavorisAndState(Long user, int state);

    Page<AnnonceEntity> findByFavorisAndState(Long user, int state, Pageable pageable);

    Long countByFavoris(Long userId);

    Long countByVendeur_Idvendeur(Long idVendeur);

    Long countByModeleTypeId(String idtype);

    Long countByVendeur_IdvendeurAndState(Long idVendeur, int state);

    List<AnnonceEntity> findByVendeurIdvendeurAndState(Long idvendeur, int state);

    Page<AnnonceEntity> findByVendeurIdvendeurAndState(Long idvendeur, int state, Pageable pageable);

    Page<AnnonceEntity> findByModeleTypeIdAndState(String idtype, int state, Pageable pageable);

    List<AnnonceEntity> findByPrixBetween(double prixMin, double prixMax);

    List<AnnonceEntity> findByEtatBetween(double min, double max);

    List<AnnonceEntity> findByPrixGreaterThanEqual(double inf);

    List<AnnonceEntity> findByPrixLessThanEqual(double sup);

    List<AnnonceEntity> findByEtatGreaterThanEqual(double inf);

    List<AnnonceEntity> findByEtatLessThanEqual(double sup);

    List<AnnonceEntity> findAllByBrand_IdIn(String[] brand);

    List<AnnonceEntity> findAllByModele_Type_IdIn(String[] brand);

    List<AnnonceEntity> findAllByCouleur_IdIn(String[] couleur);

    List<AnnonceEntity> findAllByModele_IdIn(String[] modele);

    List<AnnonceEntity> findByDateBetween(LocalDateTime dateInf, LocalDateTime dateSup);

    List<AnnonceEntity> findByDateGreaterThanEqual(LocalDateTime dateInf);

    List<AnnonceEntity> findAllByState(int state);

    long countByState(int state);

    Page<AnnonceEntity> findAllByState(int state, Pageable page);

    List<AnnonceEntity> findByDateLessThanEqual(LocalDateTime dateSup);

    Page<AnnonceEntity> findAllByVendeur_IdvendeurAndState(Long idVendeur,
            int state, Pageable pageable);

    List<AnnonceEntity> findAllByVendeur_IdvendeurAndState(Long idVendeur, int state);

    @Query("{ 'state' : 2, 'modele.id' : ?0 }")
    List<AnnonceEntity> findSoldCarsByModelId(String modelId);

    @Query("{'state': 2}")
    List<AnnonceEntity> findSoldCars();

    @Query("{'state': 0}")
    Page<AnnonceEntity> findAllNo(Pageable page);

    @Query("{'state': 2, 'modele.id': ?0}")
    Long countSoldCarsByModelId(String modelId);

    @Query("[{'$group': {'_id': '$modele.id', 'count': {'$sum': 1}}}])")
    List<Statistique> countAllByModele();

    List<AnnonceEntity> findByDescriptionContainingIgnoreCase(String motCle);

    // List<AnnonceEntity> finBy(TextCriteria criteria);
}
