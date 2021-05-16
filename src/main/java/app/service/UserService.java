package app.service;

import app.entity.User;
import app.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        validateUser(user);
        userRepository.save(user);
    }

    public boolean isRegistered(User user) {
        validateUser(user);
        return userRepository.existsByNameAndPassword(user.getNev(), user.getJelszo());
    }

    public String validateUser(User user) {
        if (user == null) {
           throw new RuntimeException("User cannot be null!");
        }

        if (user.getNev().isBlank() || user.getJelszo().isBlank()) {
            throw new RuntimeException("Username or password cannot be null!");
        }

        if (user.getJelszo().length() < 4) {
            throw new RuntimeException("Password must be longer than 3 chars!");
        }
        return user.getJelszo();
    }

}
