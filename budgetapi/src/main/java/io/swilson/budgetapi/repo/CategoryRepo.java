package io.swilson.budgetapi.repo;

import io.swilson.budgetapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepo<Domain,PrimaryKey>
// WAY TO MANIPULATE THE MODEL
public interface CategoryRepo extends JpaRepository<Category, Long> {
    // This is basically our ORM.
    /*
        findBy tells JPA:
        select * from User where username == passed in username
     */
    Category findByName(String name);
}
