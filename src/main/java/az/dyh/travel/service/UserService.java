package az.dyh.travel.service;

import az.dyh.travel.entity.User;

public interface UserService {
    void createUser(User user);

    User findByUsername(String username);
}
