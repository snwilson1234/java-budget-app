package io.swilson.budgetapi.service;

import io.swilson.budgetapi.model.Category;
import io.swilson.budgetapi.model.CategoryDTO;
import io.swilson.budgetapi.model.Purchase;

import java.util.Collection;
import java.util.List;

public interface CategoryService {
    CategoryDTO create(Category category);
    List<CategoryDTO> list(int limit);
    CategoryDTO get(Long id);
    void update(Category category);
    void delete(Long id);

    Boolean addPurchase(Category category, Purchase purchase);

    Collection<Long> getOverages(Long id);
}
