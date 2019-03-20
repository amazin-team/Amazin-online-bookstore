package amazin.service;

import amazin.model.User;

public interface UserService {
    void save(User user);
    User findByEmail(String email);
}
