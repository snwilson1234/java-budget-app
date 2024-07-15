package io.swilson.budgetapi.service;

import io.swilson.budgetapi.model.Purchase;
import io.swilson.budgetapi.model.PurchaseDTO;

import java.util.Collection;

public interface PurchaseService {
    PurchaseDTO create(Purchase purchase);

    Collection<PurchaseDTO> list(int limit);

    PurchaseDTO get(Long id);

    void update(Purchase purchase);

    Boolean delete(Long id);
}
