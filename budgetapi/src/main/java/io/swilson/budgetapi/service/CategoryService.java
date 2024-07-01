package io.swilson.budgetapi.service;

import io.swilson.budgetapi.model.Category;
import io.swilson.budgetapi.model.Purchase;

import java.util.Collection;

public interface CategoryService {
    Category create(Category category);
    Collection<Category> list(int limit);
    Category get(Long id);
    Category update(Category category);
    Boolean delete(Long id);

    Category addPurchase(Category category, Purchase purchase);
}
