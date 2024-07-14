package io.swilson.budgetapi.service.implementation;

import io.swilson.budgetapi.model.*;
import io.swilson.budgetapi.repo.PurchaseRepo;
import io.swilson.budgetapi.service.PurchaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepo purchaseRepo;
    private final PurchaseDTOMapper mapper;

    @Override
    public PurchaseDTO create(Purchase purchase) {
        log.info("Creating new purchase: {}", purchase.getDescription());
        purchaseRepo.save(purchase);
        Optional<PurchaseDTO> result = purchaseRepo.findById(purchase.getId()).map(mapper);
        if (result.isEmpty()) {
            throw new RuntimeException("Purchase not found");
        }
        return result.get();
    }

    @Override
    public Collection<PurchaseDTO> list(int limit) {
        log.info("Retrieving purchases...");
        return purchaseRepo.findAll().stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public PurchaseDTO get(Long id) {
        log.info("Retrieving purchase with id: {}", id);
        Optional<PurchaseDTO> result = purchaseRepo.findById(id).map(mapper);
        if (result.isEmpty()) {
            throw new RuntimeException("Purchase not found");
        }
        return result.get();
    }

    @Override
    public void update(Purchase purchase) {
        log.info("Updating purchase: {}",purchase);
        purchaseRepo.save(purchase);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting purchase with id: {}", id);
        purchaseRepo.deleteById(id);
        return TRUE;
    }
}
