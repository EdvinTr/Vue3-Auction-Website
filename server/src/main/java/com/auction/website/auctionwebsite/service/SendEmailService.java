package com.auction.website.auctionwebsite.service;

import com.auction.website.auctionwebsite.entity.AuctionItem;
import com.auction.website.auctionwebsite.entity.Bid;
import com.auction.website.auctionwebsite.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    public void sendUserMailOnBidSuccess(User user, AuctionItem auctionItem, Bid bid) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Your bid of $" + bid.getAmount() + " was successful!");
        message.setText("Hey " + user.getFirstName() + "!" + "\n\nThis is an automatic message sent to inform you that your bid for: " +
                "'" + auctionItem.getName() + "'" + " was successful.\n" +
                "Auction item ID: " + auctionItem.getAuctionItemId() + "\n" +
                "Auction name: " + auctionItem.getName() + "\n" +
                "Your bid: $" + bid.getAmount() + "\n" +
                "Auction closes at: " + auctionItem.getEndDate() + "\n\n" +
                "Good luck!");
        javaMailSender.send(message);

    }

    public void sendUserMailOnAuctionCreate(User user, AuctionItem auctionItem) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Your auction is now available for bidding!");
        message.setText("Hey " + user.getFirstName() + "!" +
                "\n\nThis is an automatic message sent to inform you that your auction with the title of: " + "'" + auctionItem.getName() + "'" +
                " is now available for bidding!\n" +
                "Auction item ID: " + auctionItem.getAuctionItemId() + "\n" +
                "Auction title: " + auctionItem.getName() +
                "\nAuction ends at: " + auctionItem.getEndDate() +
                "\n\nGood luck!");

        javaMailSender.send(message);
    }

    public void sendUserMailOnAuctionWin(AuctionItem auctionItem) {
        User winningUser = auctionItem.getBids().get(0).getUser();
        // TODO
        if (winningUser != null) {
            SimpleMailMessage message = new SimpleMailMessage();

            //send message to buyer informing about the win
            int bidAmount = auctionItem.getBids().get(0).getAmount();
            String sellerFullName = auctionItem.getUser().getFirstName() + " " + auctionItem.getUser().getLastName();
            message.setTo(winningUser.getEmail());
            message.setSubject("Congratulations! You have won an auction!");
            message.setText("Hi " + winningUser.getFirstName() + "!" +
                    "\nYour bid of $" + bidAmount + " was the highest of all bids and thus you have won!\n" +
                    "\nYou are expected to pay $" + bidAmount + " to " + sellerFullName + " within 7 days.\n" +
                    "\nThank you for choosing Dream Auction!");
            javaMailSender.send(message);

        }
    }

    public void sendSellerMailOnAuctionSold(AuctionItem auctionItem) {
        SimpleMailMessage message = new SimpleMailMessage();
        User seller = auctionItem.getUser();
        int amount = auctionItem.getBids().get(0).getAmount();
        User winner = auctionItem.getBids().get(0).getUser();

        message.setTo(seller.getEmail());
        message.setSubject("Congratulations! Your auction has been sold!");
        message.setText("Hi " + seller.getFirstName() + "!" +
                "\nYour auction with the title: '" + auctionItem.getName() + "' has been sold for $" + amount +
                " to " + winner.getFirstName() + " " + winner.getLastName() +
                "\n\nThank you for doing business with Dream Auction!");

        javaMailSender.send(message);
    }

    public void sendSellerMailOnAuctionNotSold(AuctionItem auctionItem) {
        SimpleMailMessage message = new SimpleMailMessage();
        String userEmail = auctionItem.getUser().getEmail();
        String firstName = auctionItem.getUser().getFirstName();

        message.setTo(userEmail);
        message.setSubject("Unfortunately, your auction did not receive any bids");
        message.setText("Hi " + firstName + "!" +
                "\nYour auction with the title of: '" + auctionItem.getName() + "' is now closed and unfortunately the auction did not receive any bids" +
                "\nFeel free to put the auction up again for bidding though!" +
                "\n\nThank you for choosing Dream Auction!");

        javaMailSender.send(message);
    }
}
