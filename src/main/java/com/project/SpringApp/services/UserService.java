package com.project.SpringApp.services;

import com.project.SpringApp.entities.User;
import com.project.SpringApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public List<User> findTeachersWithAvailability() {
        return userRepository.findTeachersWithAvailability();
    }
    public Optional<User> findUserById(Long id){return userRepository.findById(id);}
}

