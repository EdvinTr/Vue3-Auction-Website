package com.auction.website.auctionwebsite.service;

import com.auction.website.auctionwebsite.entity.User;
import com.auction.website.auctionwebsite.exception.UserNotFoundException;
import com.auction.website.auctionwebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        if (userExists(id)) {
            return userRepository.findById(id).get();
        }
        return null;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteById(Long id) {
        if (userExists(id)) {
            userRepository.deleteById(id);
        }
    }

    public User save(User user) {

        return userRepository.save(user);
    }

    public boolean userExists(Long id) {
        boolean userExists = userRepository.existsById(id);
        if (!userExists) {
            throw new UserNotFoundException("The user with ID: " + id + " does not exist.");
        } else {
            return true;
        }
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
