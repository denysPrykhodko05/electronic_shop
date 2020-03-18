package com.epam.prykhodko.repository;

import com.epam.prykhodko.entity.User;
import java.util.List;

public interface UserRepository {

    User add(User user);

    List<User> get();

    boolean delete(User user);

    List<User> getAll();
}
