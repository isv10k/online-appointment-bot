package com.isv10k.onlineappointmentbot.services;

import com.isv10k.onlineappointmentbot.models.Role;
import com.isv10k.onlineappointmentbot.models.User;
import com.isv10k.onlineappointmentbot.repositories.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createIfNotExistAndGetUser(Long id, String name) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            User newUser = new User(id, name, Role.USER);
            userRepository.save(newUser);
            return newUser;
        }
    }
}
