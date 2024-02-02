package com.commercial.commerce.sale.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.commercial.commerce.UserAuth.Repository.UserRepository;
import com.commercial.commerce.UserAuth.Service.AuthService;
import com.commercial.commerce.UserAuth.Service.RefreshTokenService;
import com.commercial.commerce.sale.entity.AnnonceEntity;
import com.commercial.commerce.sale.entity.PurchaseEntity;
import com.commercial.commerce.sale.entity.TransactionEntity;
import com.commercial.commerce.sale.repository.PurchaseRepository;
import com.commercial.commerce.sale.utils.Statistique;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnnonceService annonceService;
    @Autowired
    private AuthService authService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private RefreshTokenService refreshTokenService;

    public PurchaseEntity insert(PurchaseEntity annonce) {
        annonce.setId(purchaseRepository.insertCustom(annonce.getAnnonce(), annonce.getUser().getId(),
                annonce.getMontant()));
        annonce.setUser(userRepository.findById(annonce.getUser().getId()).get());
        return annonce;
    }

    public List<PurchaseEntity> getAllPurchase() {
        return purchaseRepository.findAllActive();
    }

    public List<PurchaseEntity> getAllPurchaseValid(Long id, int offset, int limit) {

        return purchaseRepository.findAllActiveValid(id, PageRequest.of(offset, limit)).getContent();
    }

    public List<PurchaseEntity> getAllPurchaseValidTrans(Long id, int offset, int limit) {

        return purchaseRepository.findAlltransValid(id, PageRequest.of(offset, limit)).getContent();
    }

    public List<PurchaseEntity> getAllSent(Long id, int offset, int limit) {

        return purchaseRepository.findAllSent(id, PageRequest.of(offset, limit)).getContent();
    }

    public List<PurchaseEntity> selectWithPagination(int offset, int limit) {
        return purchaseRepository.selectWithPagination(PageRequest.of(offset, limit)).getContent();

    }

    public List<PurchaseEntity> selectPurchase(Long id, int offset, int limit) {
        List<AnnonceEntity> annonces = annonceService.getAnnoncesByVendeur(id);
        System.out.print(annonces.size());
        String[] idannonce = new String[annonces.size()];
        for (int i = 0; i < annonces.size(); i++) {
            idannonce[i] = annonces.get(i).getId();
        }
        return purchaseRepository.findAllValid(idannonce, PageRequest.of(offset, limit)).getContent();
    }

    public Long page(Long id, int state, int limit) {
        long number = purchaseRepository.countAllValid(id, state);
        return (number + limit - 1) / limit;

    }

    public Optional<PurchaseEntity> getById(String id) {
        return purchaseRepository.findById(id);
    }

    public void updateState(PurchaseEntity purchase, int state) {
        purchaseRepository.updatePurchaseState(purchase.getId(), state);
    }

    public TransactionEntity achat(PurchaseEntity purchase, Long user) throws Exception {
        TransactionEntity transaction = new TransactionEntity();
        purchase = purchaseRepository.findById(purchase.getId()).get();
        System.out.println("PurchaseService.achat()");
        AnnonceEntity annonceEntity = annonceService.getById(purchase.getAnnonce());
        transaction.setPurchase(purchase);
        transaction.setReceiver(userRepository.findById(user).get());
        System.out.println("PurchaseService.achat()");
        purchase.setUser(userRepository.findById(purchase.getUser().getId()).get());
        if (purchase.getUser().getCompte() != null
                && purchase.getUser().getCompte() >= annonceEntity.getPrix()) {
            authService.recharge(purchase.getUser().getId(),
                    -annonceEntity.getPrix());
            authService.recharge(userRepository.findById(user).get().getId(),
                    annonceEntity.getPrix() - ((annonceEntity.getCommission() * 100) / annonceEntity.getPrix()));
            transaction.setSender(purchase.getUser());
            transaction.setDate(new Date(System.currentTimeMillis()));
            transaction = transactionService.insert(transaction);
            return transaction;
        }
        throw new Exception("solde non valide");

    }

    public List<Statistique> statPurchase() {
        List<Object[]> obj = purchaseRepository.getStatsPerMonth();
        List<Statistique> stat = new ArrayList<>();
        for (Object[] iterable_element : obj) {
            Statistique stats = new Statistique();
            stats.setLabel((String) iterable_element[0]);
            stats.setCount((Long) iterable_element[1]);
            stat.add(stats);
        }
        return stat;
    }
}
