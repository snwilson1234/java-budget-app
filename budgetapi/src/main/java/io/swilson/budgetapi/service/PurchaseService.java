package io.swilson.budgetapi.service;

import io.swilson.budgetapi.model.Purchase;

import java.util.Collection;

public interface PurchaseService {
    Purchase create(Purchase purchase);

    Collection<Purchase> list(int limit);

    Purchase get(Long id);

    Purchase update(Purchase purchase);

    Boolean delete(Long id);
}
