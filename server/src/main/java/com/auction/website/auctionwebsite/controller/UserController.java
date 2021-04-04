package com.auction.website.auctionwebsite.controller;

import com.auction.website.auctionwebsite.UserRole;
import com.auction.website.auctionwebsite.entity.User;
import com.auction.website.auctionwebsite.requests.UserLoginRequest;
import com.auction.website.auctionwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public User getUserDetails(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/oauth2/login")
    public User loginOrRegisterOauth2(@RequestBody UserLoginRequest userRequest) {

        User foundUser = userService.findByEmail(userRequest.getEmail());
        if (foundUser == null) {
            User user = new User();

            user.setEmail(userRequest.getEmail());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setToken(userRequest.getToken());
            user.setProfileImageUrl(userRequest.getProfileImageUrl());
            if (userRequest.getEmail().equals("edvin.tronnberg@gmail.com")) {
                user.setRole(UserRole.ADMIN);
            }
            userService.save(user);
            return user;
        }
        return foundUser;
    }
}
