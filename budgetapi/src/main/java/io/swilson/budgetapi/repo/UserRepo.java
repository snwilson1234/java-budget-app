package io.swilson.budgetapi.repo;
import io.swilson.budgetapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
    // This is our ORM.
    /*
        findBy tells JPA:
        select * from User where username == passed in username
     */
    User findByUsername(String username);
}
