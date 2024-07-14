package io.swilson.budgetapi.service.implementation;

import io.swilson.budgetapi.model.Category;
import io.swilson.budgetapi.model.CategoryDTO;
import io.swilson.budgetapi.model.CategoryDTOMapper;
import io.swilson.budgetapi.model.Purchase;
import io.swilson.budgetapi.repo.CategoryRepo;
import io.swilson.budgetapi.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final CategoryDTOMapper mapper;

    @Override
    public CategoryDTO create(Category category) {
        log.info("Creating new category: {}", category.getName());
        categoryRepo.save(category);
        Optional<CategoryDTO> result = categoryRepo.findById(category.getId()).map(mapper);
        if (result.isEmpty()) {
            throw new RuntimeException("Category not found");
        }
        return result.get();
    }

    @Override
    public List<CategoryDTO> list(int limit) {
        log.info("Retrieving categories...");
        return categoryRepo.findAll().stream().map(mapper).toList();
    }

    @Override
    public CategoryDTO get(Long id) {
        log.info("Retrieving category with id: {}",id);
        Optional<CategoryDTO> result = categoryRepo.findById(id).map(mapper);
        if (result.isEmpty()) {
            throw new RuntimeException("Category not found");
        }
        return result.get();
    }

    @Override
    public void update(Category category) {
        log.info("Updating category: {}", category.getName());
        categoryRepo.save(category);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting category with id: {}", id);
        categoryRepo.deleteById(id);
    }

    @Override
    public Boolean addPurchase(Category category, Purchase purchase) {
        log.info("Adding purchase: {} to category: {}", purchase, category);
        List<Purchase> currPurchases = categoryRepo.findById(category.getId()).get().getPurchases();
        currPurchases.add(purchase);
        categoryRepo.findById(category.getId()).get().setPurchases(currPurchases);
        categoryRepo.save(category);
        return TRUE;
    }

     @Override
     public Collection<Long> getOverages(Long id) {
         log.info("Getting overages for category: {}", id);
         List<Purchase> purchases = categoryRepo.findById(id).get().getPurchases();
         Category category = categoryRepo.getReferenceById(id);
         purchases.sort(new PurchaseComparator());

         Integer categoryBudget = category.getBudget();
         Integer budget = categoryBudget;

         List<String> months = new ArrayList<String>();
         String prevYear = purchases.get(0).getDate().substring(0,4);
         String prevMonth = purchases.get(0).getDate().substring(5,7);
         String currYear = "";
         String currMonth = "";

         List<Long> overPurs = new ArrayList<Long>();

         for (Purchase purchase : purchases) {
             currYear = purchase.getDate().substring(0, 4);
             currMonth = purchase.getDate().substring(5, 7);
             if (currYear.equals(prevYear) && currMonth.equals(prevMonth)) {
                 if (budget < purchase.getAmount()) {
                     overPurs.add(purchase.getId());
                 }
                 budget -= purchase.getAmount();
             }
             else {
                 budget = categoryBudget;
                 if (budget < purchase.getAmount()) {
                     overPurs.add(purchase.getId());
                 }
                 budget -= purchase.getAmount();
                 prevYear = currYear;
                 prevMonth = currMonth;
             }
         }

         return overPurs;
     }
}

class PurchaseComparator implements java.util.Comparator<Purchase> {
    @Override
    public int compare(Purchase a, Purchase b) {
        return a.getDate().compareTo(b.getDate());
    }
}
