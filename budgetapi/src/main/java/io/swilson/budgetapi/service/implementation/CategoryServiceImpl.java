package io.swilson.budgetapi.service.implementation;

import io.swilson.budgetapi.model.Category;
import io.swilson.budgetapi.repo.CategoryRepo;
import io.swilson.budgetapi.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    @Override
    public Category create(Category category) {
        log.info("Creating new category: {}", category.getName());
        return categoryRepo.save(category);
    }

    @Override
    public Collection<Category> list(int limit) {
        log.info("Retrieving categories...");
        return categoryRepo.findAll();
    }

    @Override
    public Category get(Long id) {
        log.info("Retrieving category with id: {}",id);
        return categoryRepo.findById(id).get();
    }

    @Override
    public Category update(Category category) {
        log.info("Updating category: {}", category.getName());
        return categoryRepo.save(category);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting category with id: {}", id);
        categoryRepo.deleteById(id);
        return TRUE;
    }
}
