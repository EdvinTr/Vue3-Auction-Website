package com.auction.website.auctionwebsite.controller;


import com.auction.website.auctionwebsite.entity.AuctionItem;
import com.auction.website.auctionwebsite.entity.Category;
import com.auction.website.auctionwebsite.entity.User;
import com.auction.website.auctionwebsite.exception.ForbiddenException;
import com.auction.website.auctionwebsite.exception.InvalidArgumentException;
import com.auction.website.auctionwebsite.exception.NotFoundException;
import com.auction.website.auctionwebsite.repository.AuctionItemRepository;
import com.auction.website.auctionwebsite.repository.CategoryRepository;
import com.auction.website.auctionwebsite.repository.UserRepository;
import com.auction.website.auctionwebsite.requests.PostAuctionItemRequest;
import com.auction.website.auctionwebsite.requests.UpdateAuctionRequest;
import com.auction.website.auctionwebsite.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class AuctionItemController {

    @Autowired
    private AuctionItemRepository auctionItemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SendEmailService sendEmailService;


    @GetMapping("/auctions")
    public Object getAuctions(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            @RequestParam Optional<String> sortBy
    ) {

        try {
            Pageable paging = PageRequest.of(page, size, Sort.Direction.ASC, sortBy.orElse("auctionItemId"));

            Page<AuctionItem> auctionItems;
            if (name == null && category == null) {
                auctionItems = auctionItemRepository.findAll(paging);
            } else if (name != null) {
                auctionItems = auctionItemRepository.findByNameContaining(name, paging);
            } else {
                auctionItems = auctionItemRepository.findByCategory_CategoryName(category, paging);
            }

            return auctionItems;
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/auctions/{id}")
    public AuctionItem getAuctionItemById(@PathVariable Long id, HttpServletResponse response) {
        AuctionItem auctionItem = auctionItemRepository.findById(id).orElse(null);
        if (auctionItem == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            throw new NotFoundException("The AuctionItem with ID: " + id + " does not exist.");
        }
        return auctionItemRepository.findById(id).orElse(null);
    }


    @PostMapping("/auctions/{id}/close")
    public AuctionItem closeAuction(@PathVariable Long id) {
        AuctionItem auctionItem = auctionItemRepository.findById(id).orElse(null);

        if (auctionItem == null) {
            throw new NotFoundException("The AuctionItem with ID: " + id + " does not exist.");
        }
        if (auctionItem.getBids().size() > 0) {
            sendEmailService.sendUserMailOnAuctionWin(auctionItem); //send mail to buyer
            sendEmailService.sendSellerMailOnAuctionSold(auctionItem); //send mail to seller
        } else {
            sendEmailService.sendSellerMailOnAuctionNotSold(auctionItem);

        }
        LocalDateTime newDate = LocalDateTime.of(1970, 1, 1, 12, 0, 0);
        auctionItem.setEndDate(newDate); // crap solution for invalidating auction, go make database table instead man
        auctionItemRepository.save(auctionItem);
        return auctionItem;
    }

    @PostMapping("/auctions")
    public AuctionItem postAuctionItem(@RequestBody PostAuctionItemRequest request, HttpServletResponse response) {

        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            throw new NotFoundException("The user with ID: " + request.getUserId() + " was not found");
        }

        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if (category == null) {
            throw new NotFoundException("The category with ID: " + request.getCategoryId() + " was not found");
        }

        AuctionItem auctionItem = new AuctionItem();

        auctionItem.setCategory(category);
        auctionItem.setUser(user);

        auctionItem.setName(request.getName());
        auctionItem.setPrice(request.getPrice());
        auctionItem.setImageUrl(request.getImageUrl());
        auctionItem.setEndDate(request.getEndDate());
        auctionItem.setDescription(request.getDescription());

        auctionItemRepository.save(auctionItem);

        sendEmailService.sendUserMailOnAuctionCreate(user, auctionItem); /* Notify user that auction item is now up for bidding */

        response.setStatus(HttpServletResponse.SC_CREATED);
        return auctionItem;
    }
    //update auction
    @PutMapping("/auctions/{id}")
    public AuctionItem updateAuction(@PathVariable Long id, @RequestBody UpdateAuctionRequest request) {

        AuctionItem auctionItem = auctionItemRepository.findById(id).orElse(null);

        if (auctionItem == null) {
            throw new NotFoundException("The AuctionItem with ID: " + id + " does not exist.");
        }

        if (!auctionItem.getUser().getUserId().equals(request.getUserId())) {
            throw new ForbiddenException("You lack permission to update this auction");
        }

        //update auction title
        if(request.getName().trim().length() > 0) {
            auctionItem.setName(request.getName());
        }
        //update category
        if(request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
            if(category != null) {
                auctionItem.setCategory(category);
            }
        }

        //update description
        if(request.getDescription().trim().length() > 0) {
            auctionItem.setDescription(
                    request.getDescription());
        }

        //update end date
        auctionItem.setEndDate(request.getEndDate());

        //update image
        if(request.getImageUrl().trim().length() > 0) {
            auctionItem.setImageUrl(request.getImageUrl());
        }

        //update price
        if(request.getPrice() < 1) {
            throw new InvalidArgumentException("The price cannot be zero or negative");
        }
        auctionItem.setPrice(request.getPrice());

       return auctionItemRepository.save(auctionItem);
    }

    @DeleteMapping("/auctions/{id}")
    public void deleteById(@PathVariable Long id) {
        AuctionItem auctionItem = auctionItemRepository.findById(id).orElse(null);

        if (auctionItem == null) {
            throw new NotFoundException("The AuctionItem with ID: " + id + " does not exist.");
        }
        auctionItemRepository.deleteById(id);
    }
}

