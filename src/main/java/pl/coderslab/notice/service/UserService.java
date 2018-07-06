package pl.coderslab.notice.service;

import pl.coderslab.notice.entity.User;

public interface UserService {
    User findByUserName(String username);

    void saveUser(User user, String role);
}
