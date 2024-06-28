package io.swilson.budgetapi.repo;

import io.swilson.budgetapi.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase, Long> {
    // TODO: define helper methods with findBy
}
