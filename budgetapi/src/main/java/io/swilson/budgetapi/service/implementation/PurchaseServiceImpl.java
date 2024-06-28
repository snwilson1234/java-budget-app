package io.swilson.budgetapi.service.implementation;

import io.swilson.budgetapi.model.Purchase;
import io.swilson.budgetapi.repo.PurchaseRepo;
import io.swilson.budgetapi.service.PurchaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepo purchaseRepo;

    @Override
    public Purchase create(Purchase purchase) {
        log.info("Creating new purchase: {}", purchase);
        return purchaseRepo.save(purchase);
    }

    @Override
    public Collection<Purchase> list(int limit) {
        log.info("Retrieving purchases...");
        return purchaseRepo.findAll();
    }

    @Override
    public Purchase get(Long id) {
        log.info("Retrieving purchase with id: {}", id);
        return purchaseRepo.findById(id).get();
    }

    @Override
    public Purchase update(Purchase purchase) {
        log.info("Updating purchase: {}",purchase);
        return purchaseRepo.save(purchase);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting purchase with id: {}", id);
        purchaseRepo.deleteById(id);
        return TRUE;
    }
}
