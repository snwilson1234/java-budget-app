package io.swilson.budgetapi.service;

import io.swilson.budgetapi.model.User;

import java.util.Collection;

// Laying down the functionality we want
// Can create an implementation of UserService using repo
// And implement features
public interface UserService {
    User create(User user);
    Collection<User> list(int limit);
    User get(Long id);
    User update(User user);
    Boolean delete(Long id);
}
