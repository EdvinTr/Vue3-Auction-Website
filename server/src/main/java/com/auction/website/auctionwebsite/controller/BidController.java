package com.auction.website.auctionwebsite.controller;

import com.auction.website.auctionwebsite.entity.AuctionItem;
import com.auction.website.auctionwebsite.entity.Bid;
import com.auction.website.auctionwebsite.entity.User;
import com.auction.website.auctionwebsite.exception.InvalidArgumentException;
import com.auction.website.auctionwebsite.exception.NotFoundException;
import com.auction.website.auctionwebsite.repository.AuctionItemRepository;
import com.auction.website.auctionwebsite.repository.BidRepository;
import com.auction.website.auctionwebsite.repository.CategoryRepository;
import com.auction.website.auctionwebsite.repository.UserRepository;
import com.auction.website.auctionwebsite.requests.BidAuctionItemRequest;
import com.auction.website.auctionwebsite.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class BidController {

    @Autowired
    private AuctionItemRepository auctionItemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private SendEmailService sendEmailService;

    @PostMapping("/auctions/{id}")
    public Bid addBid(@RequestBody BidAuctionItemRequest request, HttpServletResponse response, @PathVariable Long id) {

        AuctionItem auctionItem = auctionItemRepository.findById(id).orElse(null);
        if (auctionItem == null) {
            throw new NotFoundException("The AuctionItem with ID: " + id + " was not found.");
        }

        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            throw new NotFoundException("The user with ID: " + request.getUserId() + " was not found");
        }

        if (request.getAmount() <= auctionItem.getPrice()) {
            throw new InvalidArgumentException("The bid amount cannot be less or equal than the current price");
        }

        Bid bid = new Bid();

        bid.setAmount(request.getAmount());
        bid.setUser(user);

        auctionItem.setPrice(bid.getAmount());
        user.getBidList().add(bid);
        auctionItem.getBids().add(bid);

        sendEmailService.sendUserMailOnBidSuccess(user, auctionItem, bid);

        response.setStatus(HttpServletResponse.SC_CREATED);
        return bidRepository.save(bid);
    }
}
